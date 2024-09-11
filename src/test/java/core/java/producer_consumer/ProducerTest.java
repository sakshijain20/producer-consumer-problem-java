package core.java.producer_consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import core.java.producer_consumer.messageQueue.Message;
import core.java.producer_consumer.messageQueue.MessageQueue;
import core.java.producer_consumer.producer.Producer;

public class ProducerTest {

	//test to check if the producer produces correct messages
	@Test
    void testProducer() throws Exception {
        MessageQueue messageQueue = new MessageQueue(1);
        List<Message> messages = List.of(new Message("true"),
        								 new Message("false"),
        								 new Message("true"));

        Producer producer = new Producer(messageQueue, messages);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        producerThread.join();

        assertEquals("true", messageQueue.consume().getData());
        assertEquals("false", messageQueue.consume().getData());
        assertEquals("true", messageQueue.consume().getData());
    }

}
