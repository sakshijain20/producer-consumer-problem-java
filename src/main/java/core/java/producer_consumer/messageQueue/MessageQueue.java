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
		
        while (queue.size() == queueCapacity) {
            System.out.println("Queue is full, Producer is waiting for Consumer to consume messages");
            wait();
        }
        
        queue.add(message);
        notify();
        
        System.out.println("Produced message:" + message);
    }
	
	 public synchronized Message consume() throws Exception {
		 
	        while (queue.isEmpty()) {
	            System.out.println("Queue is empty, Consumer is waiting for Produce produce messages");
	            wait();
	        }
	        
	        Message message = queue.poll();
	        System.out.println("Consumed message:" + message);
	        
	        notify();
	        
	        return message;
	    }

}
