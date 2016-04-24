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
    private final int totalBurstTime;
    private int currentBurstTime;
    private boolean status;
    private boolean isCompleted;
    private boolean isRunning;

    public SimProcess(int processID, int burstTime) {
        this.processID = processID;
        this.totalBurstTime = burstTime;
    }

    public void burst() {
        currentBurstTime++;
        if (currentBurstTime == totalBurstTime) {
            //code for process completion
        }
    }

    public void activate() {
        if (!isCompleted) {
            status = true;

        }
    }

    public void deactivate() {
        isRunning = false;
    }

    public int getPID() {
        return processID;
    }
}
