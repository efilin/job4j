package ru.job4j.srp;

import java.util.Map;
import java.util.function.BiFunction;

public class EngiCalculator extends Calculator {
    private double result;

    private final Map<String, BiFunction<Double, Double, Double>> functionMap;

    public EngiCalculator(Map<String, BiFunction<Double, Double, Double>> functionMap) {
        super(functionMap);
        this.functionMap = functionMap;
    }

    @Override
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
        this.functionMap.put("sin", (x, y) -> {
            setResult(x * Math.sin(y));
            return result;
        });
        this.functionMap.put("cos", (x, y) -> {
            setResult(x * Math.cos(y));
            return result;
        });
        this.functionMap.put("tan", (x, y) -> {
            setResult(x * Math.tan(y));
            return result;
        });
        this.functionMap.put("ctg", (x, y) -> {
            setResult(x * (1 / Math.tan(y)));
            return result;
        });
    }

    public double getResult() {
        return this.result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
