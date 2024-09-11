package core.java.producer_consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import core.java.producer_consumer.messageQueue.Message;
import core.java.producer_consumer.messageQueue.MessageQueue;

public class MessageQueueTest {

	MessageQueue messageQueue;
	
	//test to check if the queue is empty
	@Test
    void emptyQueueTest() throws Exception {
        messageQueue = new MessageQueue(2);

        Thread consumerThread = new Thread(() -> {
            try {
                messageQueue.consume();
            } catch (Exception e) {
                fail("Consumer should wait instead of throwing an exception");
            }
        });
        consumerThread.start();
        Thread.sleep(1000);
        assertTrue(consumerThread.isAlive());

        messageQueue.produce(new Message("false"));
        consumerThread.join();
    }
	
	//test to check if the queue is full
    @Test
    void fullQueueTest() throws Exception {
    	messageQueue = new MessageQueue(2);
        messageQueue.produce(new Message("true"));
        messageQueue.produce(new Message("true"));

        Thread producerThread = new Thread(() -> {
            try {
                messageQueue.produce(new Message("false"));
            } catch (Exception e) {
                fail("Producer should wait instead of throwing an exception");
            }
        });
        producerThread.start();
        Thread.sleep(1000);
        assertTrue(producerThread.isAlive());

        messageQueue.consume();
        producerThread.join();
    }
    
	//test to check if the queue can produce and consume message successfully
    @Test
    void produceAndConsumeTest() throws Exception {
        messageQueue = new MessageQueue(3);
        Message message = new Message("true");
        messageQueue.produce(message);

        Message consumedMessage = messageQueue.consume();
        assertEquals(message, consumedMessage);
    }

}