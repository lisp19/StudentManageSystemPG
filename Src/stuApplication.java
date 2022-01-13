//Class used to store the applications of students



public class stuApplication{
	//Attributes
	private int ID;
	private String stat;
	private String end;
	private int duration;
	private String status;
	private String initiator;
	private String processor;
	private String reason;
	private int week;
	//Default constructor
	public stuApplication(){
		ID = 0;
		stat = new Timemark(1,1,0).toString();
		end = new Timemark(1,1,1).toString();
		duration = 1;
		status = "pending";
		initiator = "2019011404";
		processor = "2019011404";
		reason = "Nothing";
		week = new Timemark(stat).getweek();
	}
	//Constructor with attributes
	public stuApplication(int i, String st, String ed, String s, String in, String pr, String re){
		ID = i;
		stat = new Timemark(st).toString();
		end = new Timemark(ed).toString();
		duration = new Timemark(end).getDifference(new Timemark(stat));
		status = s;
		initiator = in;
		processor = pr;
		reason = re;
		week=new Timemark(stat).getweek();
	}
	//Baisc getters and setters
	public int getID(){
		return ID;
	}
	public void setID(int i){
		ID = i;
	}
	public String getStatus(){
		return status;
	}
	public void setstatus(String s){
		status = s;
	}
	public String getInitiator(){
		return initiator;
	}
	public void setinitiator(String i){
		initiator = i;
	}
	public String getProcessor(){
		return processor;
	}
	public void setprocessor(String p){
		processor = p;
	}
	public String getStat(){
		return stat;
	}
	public String getEnd(){
		return end;
	}
	public int getDuration(){
		return duration;
	}
	public String getReason(){
		return reason;
	}
	public void setreason(String r){
		reason = r;
	}
	public int getweek(){
		return week;
	}

}