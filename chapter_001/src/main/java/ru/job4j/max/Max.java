package ru.job4j.max;

/**
 * @author Evgeniy Filin (2727fas@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Max {

    public int max(int first, int second) {
         return first >= second ? first : second;
    }
    public int max(int first, int second, int third){
        int temp1 = this.max(first, second);
        int temp2 = this.max(second, third);
        int temp = this.max(temp1,temp2);
        return temp;
    }
}
