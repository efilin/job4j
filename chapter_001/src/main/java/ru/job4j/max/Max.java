package ru.job4j.max;

/**
 * @author Evgeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

        /*можно упростить.
        сделать проверку двух чисел и результата.
        и записать один вызовом.
        return max(.. max(..));
        */

public class Max {

    public int max(int first, int second) {
         return first >= second ? first : second;
    }
    public int max(int first, int second, int third) {
        int temp1 = this.max(first, second);
        int temp2 = this.max(second, third);
        return this.max(temp1, temp2);
    }
}
