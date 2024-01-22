package project;

public class QueueData {
	 private String queueID;
     private int numberOfPeople;

     public QueueData(String queueID, int numberOfPeople) {
         this.queueID = queueID;
         this.numberOfPeople = numberOfPeople;
     }

     public String getQueueID() {
         return queueID;
     }

     public int getNumberOfPeople() {
         return numberOfPeople;
     }
}
