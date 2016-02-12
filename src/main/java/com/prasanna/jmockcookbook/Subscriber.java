package com.prasanna.jmockcookbook;

public interface Subscriber {
   void receive(String messgae);

   void getReady();

   int messageReceivedCount();
}
