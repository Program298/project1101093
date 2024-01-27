package project;

public class QueueData {
	 public String queueID;
	 public String name;
     private int numberOfPeople;
     public  String status;
     public int remaining;

     public QueueData(String queueID,int numberOfPeople,String name) {
         this.queueID = queueID;
         this.name = name;
         this.numberOfPeople = numberOfPeople;
         this.status = "Waiting";
         
     }
     public String getname() {
    	 return name ; 
     }
     public String getQueueID() {
         return queueID;
     }

     public int getNumberOfPeople() {
         return numberOfPeople;
     }
     public String getstatus(){
    	 return status;
     }
     public int getremaining() {
    	 return remaining;
     }
}
