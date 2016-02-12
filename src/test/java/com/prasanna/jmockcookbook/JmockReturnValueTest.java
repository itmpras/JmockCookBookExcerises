package com.prasanna.jmockcookbook;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

/**
 * Created by gopalp on 11/02/2016.
 */
public class JmockReturnValueTest {
   Mockery context = new Mockery();

   @Test
   public void mockReturningValuesOnInvocation() throws Exception {
      final Calculator calculator = context.mock(Calculator.class);

      context.checking(new Expectations() {
         {
            oneOf(calculator).add(2, 2);
            will(returnValue(4));
         }
      });

      int add = calculator.add(2, 2);
      assertThat(add, is(4));
   }

   @Test
   public void mockReturningIteratorOnInvoation() throws Exception {

      final Calculator calculator = context.mock(Calculator.class);
      context.checking(new Expectations() {
         {
            oneOf(calculator).getResults();
            will(returnIterator(1, 2, 3));
         }
      });

      Iterator<Integer> results = calculator.getResults();
      assertThat(results.next(), is(1));
   }

   @Test
   public void mockReturningValuesOnConsuctiveCalls() throws Exception {
      final Calculator calculator = context.mock(Calculator.class);
      context.checking(new Expectations() {
         {
            atLeast(1).of(calculator).getOperationId();
            will(onConsecutiveCalls(returnValue(1), returnValue(2), returnValue(3)));
         }
      });

      int operationIdOne = calculator.getOperationId();
      int operationIdTwo = calculator.getOperationId();

      assertThat(operationIdTwo > operationIdOne, is(true));
   }

   @Test(expected = RuntimeException.class)
   public void mockThrowingException() throws Exception {

      final Calculator calculator = context.mock(Calculator.class);
      context.checking(new Expectations() {
         {
            allowing(calculator).getOperationId();
            will(throwException(new RuntimeException("Invalid Operation")));
         }
      });

      calculator.getOperationId();
   }

   @Test
   public void mockMatchingExceptions() throws Exception {

      final Calculator calculator = context.mock(Calculator.class);
      context.checking(new Expectations() {
         {
            oneOf(calculator).sqrt(with(lessThan(0)));

            will(throwException(new RuntimeException("Invalid value")));
         }
      });

      calculator.sqrt(-1);
   }
}
