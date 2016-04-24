/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osproject.roundrobinsimulation;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yasas
 */
public class Scheduler {

    //for process execution
    private final Processor processor;
    private final ReadyQueue readyQueue;

    //data for process execution
    private int timeQuantum;
    private int count = 0;
    private boolean finished;
    private int speed;

    //new processes yet to be executed
    private final ArrayList<SimProcess> newProcesses = new ArrayList<>();

    public Scheduler(int speed, int timeQuantum) {
        processor = new Processor(speed);
        readyQueue = new ReadyQueue();
        this.speed = speed;
        this.timeQuantum = timeQuantum;
    }

    //adds a new process for execution
    public void addProcess(SimProcess process) {
        newProcesses.add(process);
    }

    private void addNewProcesses() {
        for (SimProcess process : (ArrayList<SimProcess>) newProcesses.clone()) {
            if (!readyQueue.isFull()) {
                System.out.println("process added to ready queue "+readyQueue.addProcess(process));
                newProcesses.remove(process);
            }
        }
    }

    public void changeSpeed(int speed) {
        processor.changeSpeed(speed);
        this.speed = speed;
    }

    public synchronized void pause() {
        processor.pause();
        finished = true;
    }

    public synchronized void start() {
        processor.start();
        finished = false;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (!finished) {
                    if (count % timeQuantum == 0) {
                        System.out.println("Time Quantum Passed!!!!!!!!!!!!");
                        addNewProcesses();
                        if (!readyQueue.isEmpty()) {
                            readyQueue.addProcess(processor.executeProcess(readyQueue.getProcess()));
                        }
                        addNewProcesses();
                    }
                    count++;
                    System.out.println("Schedular Thread!!!");
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
