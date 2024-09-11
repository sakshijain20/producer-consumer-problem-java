package core.java.producer_consumer.consumer;

import java.util.concurrent.atomic.AtomicInteger;

import core.java.producer_consumer.messageQueue.Message;
import core.java.producer_consumer.messageQueue.MessageQueue;

public class Consumer implements Runnable{

	private final MessageQueue messageQueue;
    private final AtomicInteger success, failure;

    public Consumer(MessageQueue messageQueue, AtomicInteger success, AtomicInteger failure) {
        this.messageQueue = messageQueue;
        this.success = success;
        this.failure = failure;
    }

    @Override
    public void run() {
        while (true) {
        	
            try {
                Message data = messageQueue.consume();
                
                if (data.getData().equals("stop")) {
                    break;
                }
                
                processMessage(data);
                success.incrementAndGet();
            } 
            catch (Exception e) {
                failure.incrementAndGet();
            }
        }
    }

    private void processMessage(Message data) {
        boolean isInValid = Boolean.parseBoolean(data.getData());

        if (!isInValid) {
            throw new RuntimeException("Failed to process the message: " + data);
        }

        System.out.println("The message is processed successfully: " + data);
    }
   
}
