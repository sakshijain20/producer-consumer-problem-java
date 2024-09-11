package core.java.producer_consumer.messageQueue;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
	private final Queue<Message> queue;
    private final int queueCapacity;

	public MessageQueue(int queueSize) {
		queue = new LinkedList<>();
		this.queueCapacity = queueSize;
	}
	
	public synchronized void produce(Message message) throws Exception {
		
		//checking if the queue is full
        while (queue.size() == queueCapacity) {
            System.out.println("Queue is full, Producer is waiting for Consumer to consume messages");
            wait(); //waiting for the consumer to consume message
        }
        
        queue.add(message);
        //notify the consumer that the messages are produces in the queue
        notify();
        
        System.out.println("Produced message:" + message);
    }
	
	 public synchronized Message consume() throws Exception {
		//checking if the queue is empty
	        while (queue.isEmpty()) {
	            System.out.println("Queue is empty, Consumer is waiting for Produce produce messages");
	            wait(); //waiting for the producer to produce message
	        }
	        
	        Message message = queue.poll();
	        System.out.println("Consumed message:" + message);
	        
	        notify(); //notigy the producer that the message is consumed
	        
	        return message;
	    }

}
