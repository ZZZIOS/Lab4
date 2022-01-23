package ru.spbstu.telematics.java;

public class App {
    public static void main(String[] args) {
        long t = System.currentTimeMillis();
        Solver1D slvr = new Solver1D(2);
        System.out.print("Исходный массив: ");
        slvr.out();
        slvr.solve();
        System.out.print("Результат: ");
        slvr.out();
    }
}
