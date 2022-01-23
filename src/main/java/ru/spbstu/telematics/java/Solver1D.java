package ru.spbstu.telematics.java;

import java.util.LinkedList;
import java.util.List;

public class Solver1D {
    private double[] arrayOfTemperatures;
    private double[] arrayOfCalculatedTemperatures;
    private double L; // длина стержня
    private double tau;

    private int N; //длина массива температур
    private int nTimes;

    private double a;
    private double c;
    private double rho;

    private double T0;

    private double TLeft;
    private double TRight;

    private int threads;

    Solver1D(int thrs) {
        L = 10;
        tau = 60;
        N = 100;
        nTimes = 10;
        a = 50;
        c = 500;
        rho = 5000;
        T0 = 80;
        TLeft = 0;
        TRight = 200;
        threads = thrs;
        arrayOfTemperatures = new double[N];
        arrayOfCalculatedTemperatures = new double[N];
        for (int i = 1; i < N - 1; i++) {
            arrayOfTemperatures[i] = T0;
        }
        arrayOfTemperatures[0] = TLeft;
        arrayOfTemperatures[N - 1] = TRight;
    }


    public void solve() {
        List<Thread> list = new LinkedList<>();
        List<SR> objects = new LinkedList<>();
        for (int i = 0; i < threads; i++) {
            SR obj = new SR(this, i + 1, threads);
            objects.add(obj);
            Thread t = new Thread(obj);
            list.add(t);
        }
        for (Thread t : list)
            t.start();

        for (Thread t : list) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    double getA(){
        return (1 - (a * tau * 2) / (rho * c * (L / N) * (L / N)));
    }

    double getB(){
        return (a * tau) / ((L / N) * (L / N) * (rho * c));
    }

    double[] getArray(){
        return arrayOfTemperatures;
    }
    double[] getCalcArray(){
        return arrayOfCalculatedTemperatures;
    }

    void setArrayOfTemperatures(double[] arr){
        arrayOfTemperatures = arr;
    }

    int getN(){
        return N;
    }

    int getnTimes(){
        return nTimes;
    }

    int getThreads(){
        return threads;
    }

    void out(){
        System.out.print("[ ");
        for (int j = 0; j < N; j++) {
            System.out.print(arrayOfTemperatures[j]);
            if (j != N - 1)
                System.out.print(", ");
        }
        System.out.print(" ]");
        System.out.println("");
    }
}
