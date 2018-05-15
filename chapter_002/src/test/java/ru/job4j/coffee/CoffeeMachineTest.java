package ru.job4j.coffee;


/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {

    @Test
    public void whenPriceTwentyThreeValueOneHundred() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int result[] = coffeeMachine.changes(100, 23);
        int expect[] = {10, 10, 10, 10, 10, 10, 10, 5, 2};
        assertThat(result, is(expect));
    }
}
