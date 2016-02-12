package com.prasanna.jmockcookbook;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.States;
import org.junit.Test;

/**
 * Created by gopalp on 12/02/2016.
 */
public class JmockStateTest {
   Mockery context = new Mockery();
   States calcualtorStatess = context.states("InitialState").startsAs("InitialState");

   @Test
   public void mockShouldTestStateTransitions() throws Exception {

      final Calculator mock = context.mock(Calculator.class);

      context.checking(new Expectations() {
         {
            oneOf(mock).add(1, 2);
            when(calcualtorStatess.is("InitialState"));
            then(calcualtorStatess.is("computing"));

            oneOf(mock).subtract(1, 2);
            when(calcualtorStatess.is("computing"));

            oneOf(mock).getResults();
            then(calcualtorStatess.is("computed"));
         }
      });

      mock.add(1, 2);
      mock.subtract(1, 2);
      mock.getResults();
   }
}
