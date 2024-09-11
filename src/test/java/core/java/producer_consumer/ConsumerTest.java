package core.java.producer_consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import core.java.producer_consumer.consumer.Consumer;
import core.java.producer_consumer.messageQueue.Message;
import core.java.producer_consumer.messageQueue.MessageQueue;

public class ConsumerTest {
	
	 MessageQueue messageQueue;
	 AtomicInteger success = new AtomicInteger();
     AtomicInteger failures = new AtomicInteger();
     
     //if we get stop as data, the application would stop
     @Test
     void testConsumerTermination() throws Exception {
         messageQueue = new MessageQueue(3);

         messageQueue.produce(new Message("stop"));

         Consumer consumer = new Consumer(messageQueue, success, failures);
         Thread consumerThread = new Thread(consumer);
         consumerThread.start();
         consumerThread.join(1000);

         assertEquals(0, success.get());
         assertEquals(0, failures.get());
     }

     //test to check if the messages are successfully executed
	@Test
    void testConsumerSuccess() throws Exception {
		messageQueue = new MessageQueue(4);

        messageQueue.produce(new Message("true"));
        messageQueue.produce(new Message("true"));
        messageQueue.produce(new Message("true"));
        messageQueue.produce(new Message("stop"));

        Consumer consumer = new Consumer(messageQueue, success, failures);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        consumerThread.join(1000);

        assertEquals(3, success.get());
        assertEquals(0, failures.get());
    }

	  //test to check if the messages are failures
    @Test
    void testConsumerFailure() throws Exception {
       messageQueue = new MessageQueue(4);
       
        messageQueue.produce(new Message("false"));
        messageQueue.produce(new Message("false"));
        messageQueue.produce(new Message("false"));
        messageQueue.produce(new Message("stop"));

        Consumer consumer = new Consumer(messageQueue, success, failures);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        consumerThread.join(1000);

        assertEquals(0, success.get());
        assertEquals(3, failures.get());
    }

   

}