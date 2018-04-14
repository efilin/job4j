package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class PointTest {
    @Test
    public void distanceAtoB() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 4);
        double result = a.distanceTo(b);
        assertThat(result, closeTo(4D, 0.1));
    }
}

