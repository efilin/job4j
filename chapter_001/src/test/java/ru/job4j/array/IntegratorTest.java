package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class IntegratorTest {
    @Test
    public void twoArraysIntoOne() {
        Integrator integrator = new Integrator();
        int [] first = {2, 4, 5, 8, 9};
        int [] second = {1, 4, 6, 10, 12};
        int [] result = integrator.sort(first, second);
        int [] expect = {1, 2, 4, 4, 5, 6, 8, 9, 10, 12};
        assertThat(result, is(expect));
    }
}
