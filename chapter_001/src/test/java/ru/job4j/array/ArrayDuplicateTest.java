package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author  Eugeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] input =  {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String [] result = duplicate.remove(input);
        String[] expect = {"Привет", "Мир", "Супер"};
        assertThat(result, is(expect));
        //напишите здесь тест, проверяющий удаление дубликатов строк из массива строк.
    }
}