package core.java.producer_consumer.producer;

import java.util.List;

import core.java.producer_consumer.messageQueue.Message;
import core.java.producer_consumer.messageQueue.MessageQueue;

public class Producer implements Runnable{

	private final MessageQueue messageQueue;
    private final List<Message> messages;
    
	public Producer(MessageQueue messageQueue, List<Message> messages) {
        this.messageQueue = messageQueue;
        this.messages = messages;
	}
	
	@Override
    public void run() {
        for(Message message : messages) {
            try {
                messageQueue.produce(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
