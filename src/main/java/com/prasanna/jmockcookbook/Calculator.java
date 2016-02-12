package com.prasanna.jmockcookbook;

import java.util.Iterator;
import java.util.List;

/**
 * Created by gopalp on 11/02/2016.
 */
public interface Calculator {
   int add(int x, int y);

   Iterator<Integer> getResults();

   int getOperationId();

   int sqrt(int number);
}
