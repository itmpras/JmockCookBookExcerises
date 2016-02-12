package com.prasanna.jmockcookbook;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

/**
 * Created by gopalp on 12/02/2016.
 */
public class JmockNewMatchersTest {
   Mockery context = new Mockery();

   // TODO To read about Matchers
   @Test
   public void mockTestingNewMatchers() throws Exception {

      final Subscriber mock = context.mock(Subscriber.class);

      context.checking(new Expectations() {
         {
            oneOf(mock).receive(with(StringPrefixMatcher.aStringStartingWith("Test")));
         }
      });

      mock.receive("TestHello");
   }
}
