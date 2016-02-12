package com.prasanna.jmockcookbook;

/**
 * Created by gopalp on 11/02/2016.
 */
public class Publisher {

   private Subscriber subscriber;

   public void add(Subscriber subscriber) {
      this.subscriber = subscriber;
   }
   public void publish(String message)   {
      subscriber.getReady();
      subscriber.receive(message);
      int messageReceivedCount = subscriber.messageReceivedCount();
   }
}
