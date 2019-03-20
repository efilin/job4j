package ru.job4j.srp;

import java.util.Map;
import java.util.function.BiFunction;

public class Calculator {
    private double result;
    private final Map<String, BiFunction<Double, Double, Double>> functionMap;

    public Calculator(Map<String, BiFunction<Double, Double, Double>> functionMap) {
        this.functionMap = functionMap;
    }

    public void init() {
        this.functionMap.put("+", (x, y) -> {
            setResult(x + y);
            return result;
        });
        this.functionMap.put("-", (x, y) -> {
            setResult(x - y);
            return result;
        });
        this.functionMap.put("*", (x, y) -> {
            setResult(x * y);
            return result;
        });
        this.functionMap.put("/", (x, y) -> {
            setResult(x / y);
            return result;
        });
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Map<String, BiFunction<Double, Double, Double>> getFunctionMap() {
        return functionMap;
    }

    public double getResult() {
        return result;
    }
}

