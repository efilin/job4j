package ru.job4j.lambda;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SummatorTest {

    @Test
    public void whenFilterCalcAndSumAndReturnIsFiftySix() {
        var summator = new Summator();
        int[] array = {1, 2, 3, 4, 5, 6};
        var result = summator.filterCalcAndSum(array);
        assertThat(result, is(56));
    }
}