package ru.spbstu.telematics.java;

import static java.lang.Math.abs;

public class SR implements Runnable {
    private final Solver1D sl;
    private final int numberOfThread; // номер потока
    private final int threadsCounter; // общее число потоков
    private int iteration;
    private static int ready; // число потоков, закончивших i-ую итерацию и ожидающих другие потоки
    private static final Object lock = new Object();

    SR(Solver1D s, int a, int b) {
        sl = s;
        numberOfThread = a;
        threadsCounter = b;
        iteration = 0;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted() && iteration < (sl.getnTimes() - 1)) {
            if (numberOfThread == 1)
                sl.getCalcArray()[0] = sl.getArray()[0];
            if (numberOfThread != threadsCounter) {
                for (int i = 1 + abs((sl.getN() - 2) / threadsCounter) * (numberOfThread - 1); i <= abs((sl.getN() - 2) / threadsCounter * (numberOfThread)); i++) {
                    sl.getCalcArray()[i] = sl.getA() * sl.getArray()[i]
                            + sl.getB() * (sl.getArray()[i - 1] + sl.getArray()[i + 1]);
                }
                iteration++;
            }
            else {
                sl.getCalcArray()[sl.getN() - 1] = sl.getArray()[sl.getN() - 1];
                for (int i = 1 + abs((sl.getN() - 2) / threadsCounter) * (numberOfThread - 1); i < (sl.getN() - 1); i++) {
                    sl.getCalcArray()[i] = sl.getA() * sl.getArray()[i]
                            + sl.getB() * (sl.getArray()[i - 1] + sl.getArray()[i + 1]);
                }
                iteration++;
            }

            synchronized (lock){
                if(ready == threadsCounter - 1) {
                    ready = 0;
                    sl.setArrayOfTemperatures(sl.getCalcArray());
                    lock.notifyAll();
                }
                else{
                    try {
                        ready++;
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
