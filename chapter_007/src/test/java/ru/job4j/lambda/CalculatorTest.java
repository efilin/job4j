package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void whenLinearDiapasonFromOneToFour() {
        Calculator calc = new Calculator();
        List<Double> linearFunction = calc.diapason(
                1, 4,
                (index) -> index * 10);
        assertThat(linearFunction, is(Arrays.asList(10D, 20D, 30D, 40D)));
    }

    @Test
    public void whenQuadraticDiapasonFromOneToFour() {
        Calculator calc = new Calculator();
        List<Double> linearFunction = calc.diapason(
                1, 4,
                (index) -> index * index);
        assertThat(linearFunction, is(Arrays.asList(1D, 4D, 9D, 16D)));
    }

    @Test
    public void whenLogarithmicDiapasonFromOneToFour() {
        Calculator calc = new Calculator();
        List<Double> linearFunction = calc.diapason(
                1, 4,
                Math::log);
        assertThat(linearFunction, is(Arrays.asList(0.0, 0.6931471805599453, 1.0986122886681098, 1.3862943611198906)));
    }
}