package ru.job4j.srp;

import java.util.Map;
import java.util.function.BiFunction;

public class EngiCalculator extends Calculator {

    private final Map<String, BiFunction<Double, Double, Double>> functionMap;

    public EngiCalculator(Map<String, BiFunction<Double, Double, Double>> functionMap) {
        super(functionMap);
        this.functionMap = functionMap;
    }

    @Override
    public void init() {
        this.functionMap.put("+", (x, y) -> x + y);
        this.functionMap.put("-", (x, y) -> x - y);
        this.functionMap.put("*", (x, y) -> x * y);
        this.functionMap.put("/", (x, y) -> x / y);
        this.functionMap.put("sin", (x, y) -> x * Math.sin(y));
        this.functionMap.put("cos", (x, y) -> x * Math.cos(y));
        this.functionMap.put("tan", (x, y) -> x * Math.tan(y));
        this.functionMap.put("ctg", (x, y) -> x * (1 / Math.tan(y)));
    }
}
