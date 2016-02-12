package com.prasanna.jmockcookbook;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by gopalp on 12/02/2016.
 */
public class StringPrefixMatcher extends TypeSafeMatcher<String> {

   String prefix;

   public StringPrefixMatcher(String prefix) {
      this.prefix = prefix;
   }
   @Override
   protected boolean matchesSafely(String s) {
      return s.startsWith(prefix);
   }
   public void describeTo(Description description) {
      description.appendText("a string starting with ").appendValue(prefix);
   }

   public static Matcher<String> aStringStartingWith(String prefix) {
      return new StringPrefixMatcher(prefix);
   }
}
