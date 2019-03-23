package ru.job4j.gc;


public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        info();
        System.out.println("Start");
        freeMemory();
        User user = new User("FAS");
        freeMemory();

        for (int i = 0; i < 10000; i++) {
            new User(String.valueOf(i));
        }
        info();

        System.out.println("Finish");
    }

    public static void freeMemory() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Free memory = " + (runtime.freeMemory()));

    }

    public static void info() {
        Runtime runtime = Runtime.getRuntime();
        int kb = 1024;
        System.out.println("Used memory = " + (runtime.totalMemory() - runtime.freeMemory()) / kb);
        System.out.println("Free memory = " + (runtime.freeMemory()) / kb);
        System.out.println("Total memory = " + (runtime.totalMemory()) / kb);
        System.out.println("Max memory = " + (runtime.maxMemory()) / kb);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("FINALIZE");
    }
}
