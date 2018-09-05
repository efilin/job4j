package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class CheckContainTest {
    @Test
    public void whenContainThenTrue() {
        CheckContain word = new CheckContain();
        String origin = "HelloWorld";
        String sub = "ello";
        boolean result = word.contains(origin, sub);
        assertThat(result, is(true));
    }

    @Test
    public void whenNotContainThenFalse() {
        CheckContain word = new CheckContain();
        boolean result = word.contains("Hello", "Hi");
        assertThat(result, is(false));

    }
}


