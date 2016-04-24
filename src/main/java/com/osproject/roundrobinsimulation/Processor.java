/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osproject.roundrobinsimulation;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yasas
 */
public class Processor {

    private ReadyQueue readyQueue;
    private boolean finished;
    private SimProcess runningProcess;
    private SimProcess prevProcess;
    private int speed;

    public Processor(int Speed) {
        this.speed = speed;
    }

    public SimProcess executeProcess(SimProcess process) {
        prevProcess = runningProcess;
        runningProcess = process;
        return prevProcess;
    }

    public void changeSpeed(int speed) {
        this.speed = speed;
    }

    public synchronized void pause() {
        finished = true;
    }

    public synchronized void start() {
        finished = false;
        Runnable r = new Runnable() {
            @Override
            public void run() {

                while (!finished) {
                    if (runningProcess != null) {
                        runningProcess.burst();
                        if (runningProcess.isCompleted()) {
                            runningProcess = null;
                        }
                    }
                    System.out.println("Processor Thread!!!");
                    try {
                        Thread.sleep(700 - speed * 4);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        };
        new Thread(r).start();
    }

}
