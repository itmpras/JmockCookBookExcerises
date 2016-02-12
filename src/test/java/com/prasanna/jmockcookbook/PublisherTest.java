package com.prasanna.jmockcookbook;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gopalp on 11/02/2016.
 */

public class PublisherTest {
   Mockery context;

   Subscriber subscriber;
   Sequence methodSequence;

   @Before
   public void setUp() throws Exception {
      context = new JUnit4Mockery();
      subscriber = context.mock(Subscriber.class);
      methodSequence = context.sequence("StartSequence");
   }
   @Test
   public void testOneSubscriberReceivesAMessage() throws Exception {
      Publisher publisher = new Publisher();
      publisher.add(subscriber);
      final String message = "newMessage";

      context.checking(new Expectations() {
         {

            oneOf(subscriber).getReady();
            inSequence(methodSequence);
            oneOf(subscriber).receive(message);
            inSequence(methodSequence);
         }
      });

      publisher.publish(message);
      context.assertIsSatisfied();
   }

   @Test
   public void testTotalMessageCountReceived() throws Exception {

      Publisher publisher = new Publisher();
      publisher.add(subscriber);
      final String message = "newMessage";

      context.checking(new Expectations() {
                          {
                             allowing(subscriber).getReady();
                             allowing(subscriber).receive(message);
                             atLeast(1).of(subscriber).messageReceivedCount();
                             will(onConsecutiveCalls(
                                returnValue(10),
                                returnValue(20),
                                returnValue(30)
                             ));
                          }
                       }
      );

      publisher.publish(message);
      context.assertIsSatisfied();
   }
}
