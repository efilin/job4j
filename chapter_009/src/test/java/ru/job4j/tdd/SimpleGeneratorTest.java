package ru.job4j.tdd;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleGeneratorTest {


    @Test
    public void whenTakeTextWithDataShouldReplaceParamsToData() {
        //assign
        Template template = new SimpleGenerator();
        String text = "Hello, ${name}.";
        Map<String, String> data = new HashMap<>();
        data.put("name", "Petr");
        String checked = "Hello, Petr.";

        //act
        String result = template.generate(text, data);

        //action
        assertThat(result, is(checked));
    }

    @Test
    public void whenTakeTextWithMoreDataShouldReplaceParamsToData() {
        //assign
        Template template = new SimpleGenerator();
        String text = "I am a ${name}, Who are ${subject}?";
        Map<String, String> data = new HashMap<>();
        data.put("name", "Petr");
        data.put("subject", "you");
        String checked = "I am a Petr, Who are you?";

        //act
        String result = template.generate(text, data);

        //action
        assertThat(result, is(checked));
    }

    @Test
    public void whenTakeTextWithTripleDataShouldReplaceParamsToData() {
        //assign
        Template template = new SimpleGenerator();
        String text = " Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> data = new HashMap<>();
        data.put("sos", "Aaa");
        String checked = " Help, Aaa, Aaa, Aaa";

        //act
        String result = template.generate(text, data);

        //action
        assertThat(result, is(checked));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenTakeTextWithIncorrectKeysThenThrowsException() {
        Template template = new SimpleGenerator();
        String text = "Hello, ${subject}.";
        Map<String, String> data = new HashMap<>();
        data.put("name", "Petr");
        String checked = "Hello, Petr.";

        String result = template.generate(text, data);
    }

    @Test(expected = RedundantKeyException.class)
    public void whenTakeDataWithRedundantKeysThenThrowsException() {
        Template template = new SimpleGenerator();
        String text = "Hello, ${name}.";
        Map<String, String> data = new HashMap<>();
        data.put("name", "Petr");
        data.put("subject", "you");
        String checked = "Hello, Petr.";

        String result = template.generate(text, data);
    }

}