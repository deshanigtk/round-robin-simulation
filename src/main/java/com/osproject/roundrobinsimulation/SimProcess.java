/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osproject.roundrobinsimulation;

/**
 *
 * @author yasas
 */
public class SimProcess {

    private final int processID;
    private final int burstTime;
    private final int priority;
    private boolean status;
    private boolean isCompleted;
    private boolean executionTime;
    private boolean isRunning;

    public SimProcess(int processID, int burstTime, int priority) {
        this.processID = processID;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    public void activate() {
        if (!isCompleted) {
            status = true;

        }
    }

    public void deactivate() {
        isRunning = false;
    }

    private void finishExecution() {

    }

    private void terminate() {

    }
    
    public int getPID(){
        return processID;
    }
}
