package com.prasanna.jmockcookbook;

import org.hamcrest.Description;
import org.jmock.api.Action;
import org.jmock.api.Invocation;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by prasniths on 13/02/16.
 */
public class AddElementAction<T> implements Action {
    private final Collection<T> elements;

    public AddElementAction(Collection<T> elements) {
        this.elements = elements;
    }

    public Object invoke(Invocation invocation) throws Throwable {
        ((Collection<T>) (invocation.getParameter(0))).addAll(elements);
        return null;
    }

    public void describeTo(Description description) {
        description.appendText("Adds").appendValueList("", "", "", elements);
    }

    public static <T> Action addElements(T... newelements) {
        return new AddElementAction<T>(Arrays.asList(newelements));
    }
}
