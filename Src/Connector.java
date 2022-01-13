//Class used to get access to the database
//Use JDBC to get contact with postgres Database
//The name of database is proDb
//Methods here represents the actual functions


import java.sql.*;
import java.util.ArrayList;
public class Connector{
	//Local configurations
	private String port;
	private String USER;
	private String password;
	//Default constructor for generating a connector with default values
	public Connector(){
		//Default values of local configurations
		port = "5433";
		USER = "postgres";
		password = "0000";
	}
	//Constructor to generate a connector with given local configuration
	public Connector(String p, String u, String pw){
		port = p;
		USER = u;
		password = pw;
	}
	//Method to get a JDBC connection with local configuration
	public Connection getConnector() throws Exception{
		//Set PGSQL driver
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection
		("jdbc:postgresql://localhost:"+port+"/prodb",USER,password);
		return conn;
	}
	//Method to register a new user to the database
	//Use INSERT INTO to insert a new user to the table
	//If operation is successfully done, 1 will be returned
	//Else, 0 or other number will be returned
	//If the information is not under standard, the insert sql won't succeed
	public int register(String type, String id, int dp,String pw) throws Exception{
		try{
			Connection conn = getConnector();
		    Statement stmt = conn.createStatement();
		    String sql = "INSERT INTO " + type + " VALUES('" + id +"'," + Integer.toString(dp) +",'" + pw +"')";
		    System.out.println(sql);
		    int temp = stmt.executeUpdate(sql);
            stmt.close();
	        conn.close();
	        return temp;
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	//Method to reset the password of a given account
	//UPDATE the tuple in table with given information
	public int reset(String type, String id, String pw){
		try{
			Connection conn = getConnector();
		    Statement stmt = conn.createStatement();
		    String sql = "UPDATE " + type + " SET password = '" + pw + "' WHERE id = '" + id +"';" ;
		    int temp = stmt.executeUpdate(sql);
            stmt.close();
	        conn.close();
	        return temp;
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	//Method to login with type ID and password
	//Use select to check whether the user exists and the password is matched
	public boolean login(String type, String id, String pw){
		try{
			Connection conn = getConnector();
		    Statement stmt = conn.createStatement();
		    String sql = "SELECT ID FROM " + type + " WHERE ID = '"+ id +"' AND password = '" + pw + "';" ;
		    ResultSet temp = stmt.executeQuery(sql);
		    String tempid = "2019011404";
		    while(temp.next()){
		    	tempid = temp.getString(1);
		    }
	        
	        stmt.close();
	        conn.close();
	        if(tempid.equals(id)){
	        	return true;
	        }
	        else{
	        	return false;
	        }
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	//Method to get the applications belonging to a teacher's department
	//Return an arraylist of application
	public ArrayList<stuApplication> teacherView(String id){
		try{
			Connection conn = getConnector();
			Statement stmt = conn.createStatement();
			//Get the department of a given teacher
			String sql = "SELECT department FROM Teacher WHERE ID = '" + id +"';";
			ResultSet rs = stmt.executeQuery(sql);
			int department = -1;
			while(rs.next()){
				department=rs.getInt("department");
			}
			//Get the applications belonging to the department
			String view = "SELECT * FROM application a, student t WHERE t.department = " +
			 Integer.toString(department) + " AND a.initiator = t.id;";
			ResultSet rs2 = stmt.executeQuery(view);
			ArrayList<stuApplication> result = new ArrayList<stuApplication>();
			while(rs2.next()){
				result.add(new stuApplication(rs2.getInt("ID"),rs2.getString("stat"),rs2.getString("endtime"),
					rs2.getString("status"),rs2.getString("initiator"),
					rs2.getString("processor"),rs2.getString("apply_reason")));
			} 
			stmt.close();
			conn.close();
			return result;
		} catch(Exception e){
			e.printStackTrace();
		}
			return null;
	}
	//Method to enable the teacher to make comment to a given application
	//With parameters: pro-teacher id, id-application id, content-content
	public int makeComment(String pro, int id, String content){
		try{
			Connection conn = getConnector();
			Statement stmt = conn.createStatement();
			//Get the number of existing comments
			//Use number + 1 as the new ID to avoid problem
			String sql = "SELECT COUNT(ID) FROM comment;";
			ResultSet rs = stmt.executeQuery(sql);
			int temp = 0;
			while(rs.next()){
				temp = rs.getInt("count");
			}
			//Insert the new comment to the table
			String sql2 = "INSERT INTO comment VALUES(" + Integer.toString(temp + 1)
			 + ", '" + pro + "', " + id + ", '" + content + "');";
			int result = stmt.executeUpdate(sql2);
			stmt.close();
			conn.close();
			return result;
		} catch(Exception e){
			e.printStackTrace();
		}
		//Return -1 for abnormal cases
		return -1;
	}
	//Method to verify the students' application for teachers
	//With parameters: application id, chosen status, processor id
	public int verify(int id, String status, String processor){
		try{
			Connection conn = getConnector();
			Statement stmt = conn.createStatement();
			//Check the status of a given application
			//Only pending application can be processed
			String check = "SELECT status FROM application WHERE ID = " + Integer.toString(id) +";";
			ResultSet rs = stmt.executeQuery(check);
			while(rs.next()){
				if(!rs.getString("status").trim().equals("pending")){
					stmt.close();
			        conn.close();
					return -2;
				}
			}
			//UPDATE the status and proccessor of an application
			String sql = "UPDATE application SET status = '" + status 
			+"', processor = '" + processor +"' WHERE id = " + Integer.toString(id)
			+";";
			int temp = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
			return temp;
		} catch(Exception e){
			e.printStackTrace();
		}
		//Return -1 for abnormal cases
		return -1;
	}
	//Method for teacher to view applications under a certain status
	//params: teacher id, status
	//Return the arraylist containing the applications
	public ArrayList<stuApplication> selectiveTeacherView(String id, String status){
		try{
			Connection conn = getConnector();
			Statement stmt = conn.createStatement();
			//Get the department of teacher 
			String sql = "SELECT department FROM Teacher WHERE ID = '" + id +"';";
			ResultSet rs = stmt.executeQuery(sql);
			int department = -1;
			while(rs.next()){
				department=rs.getInt("department");
			}
			//Get the application list of a given department and status
			String view = "SELECT * FROM application a, student t WHERE t.department = " +
			 Integer.toString(department) + " AND a.initiator = t.id AND status = '" + status +"';";
			ResultSet rs2 = stmt.executeQuery(view);
			ArrayList<stuApplication> result = new ArrayList<stuApplication>();
			while(rs2.next()){
				result.add(new stuApplication(rs2.getInt("ID"),rs2.getString("stat"),rs2.getString("endtime"),
					rs2.getString("status"),rs2.getString("initiator"),
					rs2.getString("processor"),rs2.getString("apply_reason")));
			} 
			stmt.close();
			conn.close();
			return result;
		} catch(Exception e){
			e.printStackTrace();
		}
		//Return null for abnormal cases
		return null;
	}
	//Method to get the applications belonging to a student
	//Return an arraylist of application
	public ArrayList<stuApplication> studentView(String id){
		try{
			Connection conn = getConnector();
			Statement stmt = conn.createStatement();
			//Get the applications of the student
			String sql = "SELECT * FROM application WHERE initiator = '" 
			+ id + "';";
			ResultSet rs2 = stmt.executeQuery(sql);
			ArrayList<stuApplication> result = new ArrayList<stuApplication>();
			while(rs2.next()){
				result.add(new stuApplication(rs2.getInt("ID"),rs2.getString("stat"),rs2.getString("endtime"),
					rs2.getString("status"),rs2.getString("initiator"),
					rs2.getString("processor"),rs2.getString("apply_reason")));
			}
			stmt.close();
			conn.close();
			return result;
		} catch(Exception e){
			e.printStackTrace();
		}
		//return null for abnormal cases
		return null;
	}
	//Method for students to view applications under a certain status
	//params: student, status
	//Return the arraylist containing the applications
	public ArrayList<stuApplication> selectiveStudentView(String id, String status){
		try{
			Connection conn = getConnector();
			Statement stmt = conn.createStatement();
			//Get the applications belonging to a student under the status
			String sql = "SELECT * FROM application WHERE initiator = '" 
			+ id +"' AND status = '" + status + "';";
			ResultSet rs2 = stmt.executeQuery(sql);
			ArrayList<stuApplication> result = new ArrayList<stuApplication>();
			while(rs2.next()){
				result.add(new stuApplication(rs2.getInt("ID"),rs2.getString("stat"),rs2.getString("endtime"),
					rs2.getString("status"),rs2.getString("initiator"),
					rs2.getString("processor"),rs2.getString("apply_reason")));
			}
			stmt.close();
			conn.close();
			return result;
		} catch(Exception e){
			e.printStackTrace();
		}
		//Return null for abnormal cases
		return null;
	}
	//Method for students to cancel an application
	//Return 1 when OK, -1 for error
	public int cancelApp(int id){
		try{
			Connection conn = getConnector();
			Statement stmt = conn.createStatement();
			//UPDATE the application to be cancelled
			String sql = "UPDATE application SET status = 'cancelled' WHERE ID = " 
			+ Integer.toString(id) + ";";
			int temp = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
			return temp;

		} catch(Exception e){
			e.printStackTrace();
		}
		//Return -1 for error
		return -1;
	}
	//Method for students to apply for a leave
	//Use params student id, stat,end,reason
	//stat and end are standard timestamp
	public int apply(String id, String stat, String end, String reason){
		try{
			Connection conn = getConnector();
			Statement stmt = conn.createStatement();
			//Get the number of applications 
			//New ID is number + 1
			String sql = "SELECT COUNT(ID) FROM application;";
			ResultSet rs = stmt.executeQuery(sql);
			int check = 0;
			while(rs.next()){
				check = rs.getInt("count");
			}
			check = check + 1;
			Timemark t1 = new Timemark(stat);
			Timemark t2 = new Timemark(end);
			String statstamp = t1.toString();
			String endstamp = t2.toString();
			int duration = t2.getDifference(t1);
			int week = t1.getweek();
			int num = 0;
			//Check whether the application is between 0 and 48 hours
			if(duration >= 48 || duration <= 0){
				stmt.close();
			    conn.close();
			    //Return -3 for longer than 48 or less than 0
				return -3;
			}
			//Check the number of applications in a week
			String sql2 = "SELECT COUNT(ID) FROM application WHERE ((initiator = '" + id 
			+"' AND week = " + Integer.toString(week) + ") AND (status = 'passed' OR status = 'pending'));";
			ResultSet rs2 = stmt.executeQuery(sql2);
			while(rs2.next()){
				num = rs2.getInt("count");
			}
			//3 leaves a week
			if(num >= 3){
				stmt.close();
			    conn.close();
			    //Return -1 for more than 3 per week
				return -1;
			}
			//Check the overlap between applications
			String sql3 = "SELECT stat,endtime FROM application WHERE (initiator = '" + id
			+ "' AND status = 'passed' OR status = 'pending');";
			ResultSet rs3 = stmt.executeQuery(sql3);
			while(rs3.next()){
				if(t1.overlap(statstamp,endstamp,rs3.getString("stat"),
					rs3.getString("endtime"))){
					stmt.close();
			        conn.close();
			        //Return -2 for time overlap
				    return -2;
				}
			}
			//Insert the new application to the table
			String sql4 = "INSERT INTO application VALUES(" + Integer.toString(check) + 
			", '" + statstamp + "', '" + endstamp + "', " + Integer.toString(duration) + ", 'pending', '" 
			+ reason + "', '" + id + "', null, " + Integer.toString(week) + ");"; 
			int temp = stmt.executeUpdate(sql4);
			stmt.close();
			conn.close();
			//Return temp which = 1
			return temp;

		} catch(Exception e){
			e.printStackTrace();
		}
		//Return -4 for error
		return -4;
	}
	//Method to revise an application
	//Attention: revising an application needs to fill in all the information again
	public int revise(int id, String stat, String end, String reason){
		try{
			Connection conn = getConnector();
			Statement stmt = conn.createStatement();
			//Get the corresponding application
			String sql = "SELECT status FROM application WHERE ID = " 
			+ Integer.toString(id) + ";";
			ResultSet rs = stmt.executeQuery(sql);
			String temp = null;
			while(rs.next()){
				temp = rs.getString("status");
			}
			//Only processed application can be revised
			if(temp.trim().equals("pending") || temp.trim().equals("cancelled")){
				stmt.close();
			    conn.close();
			    return -1;
			}
			int duration = new Timemark(end).getDifference(new Timemark(stat));
			int week = new Timemark(stat).getweek();
			//Update the application \
			//Attention: with the change of stat and end, duration and week also changes
			String sql2 = "UPDATE application SET stat = '" + stat + "', endtime = '"
			+ end + "', duration = "+ Integer.toString(duration) +", apply_reason = '" 
			+ reason + "', week = " +Integer.toString(week) + ", status = 'pending' "+" WHERE ID = " + Integer.toString(id) + ";";
			int num = stmt.executeUpdate(sql2);
			return num;
		} catch(Exception e){
			e.printStackTrace();
		}
		//Return 0 for error
		return 0;
	}
	//Method for students to check the comment of an application
	//Return a String containg all the comments of an application
	public String checkComment(int id){
		try{
			Connection conn = getConnector();
			Statement stmt = conn.createStatement();
			//Get the given comments
			String sql = "SELECT tea_id,content FROM comment WHERE app_id = " + Integer.toString(id) +";";
			ResultSet rs = stmt.executeQuery(sql);
			String result = "";
			while(rs.next()){
				result = result + rs.getString("tea_id") + " : " + rs.getString("content") + "\r\n";
			}
			stmt.close();
			conn.close();
			return result;
		} catch(Exception e){
			e.printStackTrace();
		}
		//Return null for abnormal cases
		return null;
	}
}