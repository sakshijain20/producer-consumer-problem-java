package core.java.producer_consumer;

import java.util.concurrent.atomic.AtomicInteger;

import core.java.producer_consumer.messageQueue.Message;
import core.java.producer_consumer.messageQueue.MessageQueue;
import core.java.producer_consumer.producer.Producer;
import core.java.producer_consumer.consumer.Consumer;

import java.util.List;

public class App {
	
    public static void main(String[] args) {
    	//created message queue of size 5
    	MessageQueue messageQueue = new MessageQueue(5);
        AtomicInteger success = new AtomicInteger();
        AtomicInteger failures = new AtomicInteger();

        //created thread for producer
        Thread producerThread = new Thread(new Producer(messageQueue,
        												List.of(new Message("true"),
        														new Message("false"),
        														new Message("true"),
        														new Message("true"),
        														new Message("false"),
        														new Message("true"),
        														new Message("false"),
        														new Message("stop")
        												)));
        //created thread for consumer
        Thread consumerThread = new Thread(new Consumer(messageQueue, success, failures));

       //started producer and consumer thread respectively 
        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total messages processed successfully: " + success.get());
        System.out.println("Total failures encountered: " + failures.get());
    }
}
