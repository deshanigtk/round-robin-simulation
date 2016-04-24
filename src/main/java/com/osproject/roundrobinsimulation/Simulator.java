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
public class Simulator {

    private ArrayList<SimProcess> processes;
    private final ArrayList<ArrayList<Integer>> timeTable = new ArrayList<>();
    private final Scheduler scheduler;

    private boolean finished = false;
    private int count = 0;
    private int speed;

    //for singleton
    private static Simulator simulator;

    //private constructor
    private Simulator(int speed, int timeQuantum) {
        this.speed = speed;
        scheduler = new Scheduler(speed, timeQuantum);
        processes = new ArrayList<>();
    }

    public static Simulator getSimulator(int speed, int timeQuantum) {
        if (simulator == null) {
            simulator = new Simulator(speed, timeQuantum);
        }
        return simulator;
    }

    //methods for Simulation GUI
    public void changeSpeed(int speed) {
        scheduler.changeSpeed(speed);
        this.speed = speed;
    }
    
    public synchronized void pause(){
        scheduler.pause();
        finished = true;
    }
    
    //methods for SetupSimulation GUI
    public void updateTimeTable(Integer processID, Integer arrivalTime) {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(processID);
        temp.add(arrivalTime);
        timeTable.add(temp);
    }

    public void addProcess(SimProcess process) {
        processes.add(process);
    }

    //start simulation
    public synchronized void start() {
        scheduler.start();
        finished=false;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (!finished) {
                    count++;
                    for (ArrayList<Integer> i : timeTable) {
                        boolean isFound = false;
                        if (count == i.get(1)) {
                            for (SimProcess j : processes) {
                                if (j.getPID() == i.get(0)) {
                                    scheduler.addProcess(j);
                                    isFound = true;
                                    break;
                                }
                            }
                            if (isFound) {
                                break;
                            }
                        }
                    }
                    System.out.println("Simulator Thread!!!");
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
