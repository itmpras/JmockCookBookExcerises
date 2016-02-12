package com.prasanna.jmockcookbook;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.auto.Mock;
import org.junit.Test;

/**
 * Created by gopalp on 11/02/2016.
 */
public class JmockSequenceTest {
   Mockery context = new Mockery();
   Sequence operationSequence = context.sequence("operationSequence");

   @Test
   public void mockShouldTestSequence() throws Exception {
      final Calculator mock = context.mock(Calculator.class);

      context.checking(new Expectations() {
         {
            oneOf(mock).add(2, 3);
            inSequence(operationSequence);
            oneOf(mock).getOperationId();
            inSequence(operationSequence);
         }
      });

      mock.add(2, 3);
      mock.getOperationId();
   }
}

