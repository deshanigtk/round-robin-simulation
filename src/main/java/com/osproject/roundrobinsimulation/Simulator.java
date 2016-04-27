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

    private final ArrayList<Job> processes;
    private final ArrayList<ArrayList<Integer>> timeTable = new ArrayList<>();
    private Scheduler scheduler;
    private int completedProcessCount;

    private boolean finished = false;
    private boolean isCompletedSimulation;
    private int count;
    private int speed;

    //for singleton
    private static Simulator simulator;

    //private constructor
    private Simulator() {
        processes = new ArrayList<>();
        count = 0;
        completedProcessCount = 0;
        isCompletedSimulation = false;
    }

    public static Simulator getSimulator() {
        if (simulator == null) {
            simulator = new Simulator();
        }
        return simulator;
    }

    public void setSimulator(int speed, int timeQuantum) {
        scheduler = new Scheduler(speed, timeQuantum);
        this.speed = speed;
    }

    public static void refreshSimulator() {
        simulator = null;
    }

    public ReadyQueue getReadyQueue() {
        return scheduler.getReadyQueue();
    }

    public Integer[] getProgress() {
        Integer[] progressReport = new Integer[processes.size()];
        for (int i = 0; i < processes.size(); i++) {
            progressReport[i] = processes.get(i).getProgress();
        }
        return progressReport;
    }

    public Job getProcess(int index) {
        return processes.get(index);
    }

    public int getNoOfProcess() {
        return processes.size();
    }
    
    public int getProcessorCount(){
        return scheduler.getProcessorCount();
    }

    public int getCount() {
        return count;
    }
    
    public int getExecutionTime(){
        return scheduler.getExeCount();
    }
    
    public float getAvgTurnaround(){
        return ((float)scheduler.getProcessorCount())/processes.size();
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    //methods for Simulation GUI
    public void changeSpeed(int speed) {
        scheduler.changeSpeed(speed);
        this.speed = speed;
    }

    public synchronized void pause() {
        scheduler.pause();
        finished = true;
    }
    
    public boolean isCompleted(){
        return isCompletedSimulation;
    }

    //methods for SetupSimulation GUI
    public void updateTimeTable(Integer processID, Integer arrivalTime) {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(processID);
        temp.add(arrivalTime);
        timeTable.add(temp);
    }

    public void addProcess(Job process) {
        processes.add(process);
    }
    
    public float getAvgWaitingTime(){
        int totalWT=0;
        for(int i=0;i<processes.size();i++){
            totalWT+=processes.get(i).getWaitingTime();
        }
        return ((float)totalWT)/processes.size();
    }

    //start simulation
    public synchronized void start() {
        scheduler.start();
        finished = false;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (!finished) {

                    for (ArrayList<Integer> i : timeTable) {
                        boolean isFound = false;
                        if (count == i.get(1)) {
                            for (Job j : processes) {
                                if (j.getPID() == i.get(0)) {
                                    scheduler.addProcess(j);
                                    isFound = true;
                                    completedProcessCount++;
                                    break;
                                }
                            }
                            if (isFound) {
                                break;
                            }
                        }
                    }
                    count++;
                    if (processes.size() == completedProcessCount) {
                        if (scheduler.isCompleted()) {
                            isCompletedSimulation = true;
                            pause();
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
