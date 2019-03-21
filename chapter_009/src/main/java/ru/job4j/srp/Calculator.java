package ru.job4j.srp;

import java.util.Map;
import java.util.function.BiFunction;

public class Calculator {
    private final Map<String, BiFunction<Double, Double, Double>> functionMap;

    public Calculator(Map<String, BiFunction<Double, Double, Double>> functionMap) {
        this.functionMap = functionMap;
    }

    public void init() {
        this.functionMap.put("+", (x, y) -> x + y);
        this.functionMap.put("-", (x, y) -> x - y);
        this.functionMap.put("*", (x, y) -> x * y);
        this.functionMap.put("/", (x, y) -> x / y);
    }

    public Map<String, BiFunction<Double, Double, Double>> getFunctionMap() {
        return functionMap;
    }

}

