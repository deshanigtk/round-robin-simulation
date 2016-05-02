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
public class Job {

    //job details
    private final int id;
    private final String name;

    //job properties
    private final int burstTime;
    private int currentBurstTime;
    private boolean completed;

    //job records
    private int startedCount;
    private int finishedCount;

    //constructor
    public Job(String name, int id, int burstTime) {
        completed = false;
        this.id = id;
        this.burstTime = burstTime;
        this.name = name;
    }

    public void burst(int count) {
        if (!completed) {
            currentBurstTime++;
            //checks for process completion
            if (currentBurstTime == burstTime) {
                completed = true;
                finishedCount = count + 1;
            }
        }
    }

    public void activate(int count) {
        startedCount = count;
        currentBurstTime = 0;
    }

    //get details
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    //get properties
    public int getProgress() {
        return Float.valueOf(currentBurstTime / Float.valueOf(burstTime) * 100).intValue();
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getStartedCount() {
        return startedCount;
    }

    public int getCurrentBurstTime() {
        return currentBurstTime;
    }

    public int getWaitingTime() {
        if (completed) {
            return (finishedCount - startedCount - burstTime);
        } else {
            return (Simulator.getSimulator().getProcessorCount() - startedCount - currentBurstTime);
        }
    }

    public int getRemainingBT() {
        return burstTime - currentBurstTime;
    }
}
