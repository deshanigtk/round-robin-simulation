/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osproject.roundrobinsimulation;

import java.util.ArrayList;

/**
 *
 * @author yasas
 */
public class Scheduler {

    //for process execution
    private final Processor processor;
    private final ReadyQueue readyQueue;

    //data for process execution
    private final int timeQuantum;
    private static int count = 0;

    //new processes yet to be executed
    private final ArrayList<Job> newProcesses = new ArrayList<>();

    public Scheduler(int speed, int timeQuantum) {
        processor = new Processor(speed);
        readyQueue = new ReadyQueue();
        this.timeQuantum = timeQuantum;
    }

    //adds a new process for execution
    public void addProcess(Job process) {
        newProcesses.add(process);
        process.activate(processor.getCount());
    }

    private void addNewProcesses() {
        for (Job process : (ArrayList<Job>) newProcesses.clone()) {
            if (!readyQueue.isFull()) {
                readyQueue.addProcess(process);
                newProcesses.remove(process);
            }
        }
    }

    public int getProcessorCount() {
        return processor.getCount();
    }

    public ReadyQueue getReadyQueue() {
        return readyQueue;
    }

    public void changeSpeed(int speed) {
        processor.changeSpeed(speed);
    }

    public synchronized void pause() {
        processor.pause();
    }

    public void updateCount() {
        if (count % timeQuantum == 0) {
            addNewProcesses();
            if (!readyQueue.isEmpty()) {
                readyQueue.addProcess(processor.executeProcess(readyQueue.getProcess()));
            }
        }
        count++;
    }
    
    public int getExeCount(){
        return count-processor.getWaitingCount();
    }
    
    

    public synchronized void start() {
        processor.start();
    }
    
    public boolean isCompleted(){
        return newProcesses.isEmpty() && readyQueue.isEmpty() && processor.isIdle();
    }

}
