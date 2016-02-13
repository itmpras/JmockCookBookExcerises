package com.prasanna.jmockcookbook;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Action;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import static com.prasanna.jmockcookbook.AddElementAction.addElements;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by prasniths on 13/02/16.
 */
public class JmockCustomActionTest {
    Mockery context = new Mockery();

    @Test
    public void mockFruitTreeUsingCutomAction() throws Exception {
        final FruitTree mock = context.mock(FruitTree.class);
        final Fruit mango = context.mock(Fruit.class, "mongo");
        final Fruit apple = context.mock(Fruit.class, "apple");


        context.checking(new Expectations() {
            {
                oneOf(mock).pickFruit(with(any(Collection.class)));
                will(addElements(mango, apple));
            }
        });

        LinkedList<Fruit> collection = new LinkedList<Fruit>();
        mock.pickFruit(collection);
        assertThat(collection.size(), is(2));

    }

    // TODO To explore custom scripting

}
