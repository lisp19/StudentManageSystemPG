//Time class used to process time and calculate duration
//No actual instance of Timemark will be loaded to the program


public class Timemark{
	//Basic variable definition
	private int month;
	private int day;
	private int hour;
	private int week;
	private int[] monthcheck = {31,28,31,30,31,30,31,31,30,31,30,31};

	//Default constructor
	public Timemark(){
		month = 1;
		day = 1;
		hour = 0;
		week = 1;
	}
	//Constructor with integers representing details of date
	public Timemark(int m, int d, int h){
		setmonth(m);
		setday(d);
		sethour(h);
		week = calweek(m, d);
	}
	//Constructor with a string s representing a standard timestamp 
	//Use this to finish the transformation between stamps and timemarks
	//When calculation is needed, this method will be used as middleware
	public Timemark(String s){
		String[] temp = s.split("-");
		month = Integer.parseInt(temp[1]);
		day = Integer.parseInt(temp[2].split(" ")[0]);
		hour = Integer.parseInt(temp[2].split(" ")[1].split(":")[0]);
		week = calweek(month,day);
	}
	//Override of toString for changing the timemark back to stamp
	public String toString(){
		return "2021-" + Integer.toString(month) + '-' + Integer.toString(day) + ' ' 
		+ Integer.toString(hour) + ":00:00";
	}
	//Basic getters and setters
	public int getmonth(){
		return month;
	}
	public int getday(){
		return day;
	}
	public int gethour(){
		return hour;
	}
	public void setmonth(int m){
		if(m>=1 && m <=12){
			month = m;
		}
	}
	public void setday(int d){
		if(d>=1 && d<=monthcheck[month-1]){
			day = d;
		}
	}
	public void sethour(int h){
		if(h >=0 && h <=23){
			hour = h;
		}
	}
	//Method to calculate the week num of a given date
	//Attention: the calculation standard is not the same as the calender
	private int calweek(int m, int d){
		int temp = 4;
		for(int i = 1; i < m; i ++){
			temp += monthcheck[i - 1];
		}
		temp += d;
		int weeknum;
		if(temp % 7 == 0){
			weeknum = temp / 7;
		}
		else{
			weeknum = temp / 7 + 1;
		}
		return weeknum;
	}
	//Method to calculate the difference between two dates
	//Can return negative ints 
	public int getDifference(Timemark t){
		return (day - t.getday())*24 + hour - t.gethour();
	}
	//Get the week number of the timemark
	public int getweek(){
		return week;
	}
	//Method to judge whether two dates overlap
	public boolean overlap(String s1, String e1, String s2, String e2){
		Timemark stat1 = new Timemark(s1);
		Timemark stat2 = new Timemark(s2);
		Timemark end1 = new Timemark(e1);
		Timemark end2 = new Timemark(e2);
		if(stat1.getDifference(stat2) >=0 && stat1.getDifference(end2) <=0){
			return true;
		}
		if(end1.getDifference(stat2) >=0 && end1.getDifference(end2) <=0){
			return true;
		}
		return false;
	}
}