package ru.job4j.calculator;
/**
 * Корвертер валюты.
 */
public class Converter {
       /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        int result = value/70;
        return result;
    }
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллары
     */
    public int rubleToDollar(int value) {
        int result = value/60;
        return result;
    }
     /** Конвертируем  евро в рубли.
            * @param value Евро.
            * @return рубли.
     */
    public int EuroToRuble(int value) {
        int result = value*70;
        return result;
    }
    /**
     * Конвертируем доллары в рубли.
     * @param value доллары.
     * @return рубли
     */
    public int DollarToRuble(int value) {
        int result = value*60;
        return result;
    }
    }
