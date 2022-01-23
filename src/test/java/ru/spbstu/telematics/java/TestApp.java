package ru.spbstu.telematics.java;

import org.junit.Test;

public class TestApp {

    @Test
    public void Test(){
        long t = System.currentTimeMillis();
        Solver1D slvr = new Solver1D(1);
        slvr.solve();
        System.out.println("Execution time by " + slvr.getThreads() + " thread: " + (System.currentTimeMillis() - t) + " mlsec");

        t = System.currentTimeMillis();
        Solver1D slvr2 = new Solver1D(2);
        slvr2.solve();
        System.out.println("Execution time by " + slvr2.getThreads() + " threads: " + (System.currentTimeMillis() - t) + " mlsec");

        t = System.currentTimeMillis();
        Solver1D slvr3 = new Solver1D(3);
        slvr3.solve();
        System.out.println("Execution time by " + slvr3.getThreads() + " threads: " + (System.currentTimeMillis() - t) + " mlsec");

        t = System.currentTimeMillis();
        Solver1D slvr4 = new Solver1D(4);
        slvr4.solve();
        System.out.println("Execution time by " + slvr4.getThreads() + " threads: " + (System.currentTimeMillis() - t) + " mlsec");

        t = System.currentTimeMillis();
        Solver1D slvr5 = new Solver1D(5);
        slvr5.solve();
        System.out.println("Execution time by " + slvr5.getThreads() + " threads: " + (System.currentTimeMillis() - t) + " mlsec");

        t = System.currentTimeMillis();
        Solver1D slvr6 = new Solver1D(6);
        slvr6.solve();
        System.out.println("Execution time by " + slvr6.getThreads() + " threads: " + (System.currentTimeMillis() - t) + " mlsec");

        t = System.currentTimeMillis();
        Solver1D slvr7 = new Solver1D(7);
        slvr7.solve();
        System.out.println("Execution time by " + slvr7.getThreads() + " threads: " + (System.currentTimeMillis() - t) + " mlsec");

        t = System.currentTimeMillis();
        Solver1D slvr8 = new Solver1D(8);
        slvr8.solve();
        System.out.println("Execution time by " + slvr8.getThreads() + " threads: " + (System.currentTimeMillis() - t) + " mlsec");
    }
}
