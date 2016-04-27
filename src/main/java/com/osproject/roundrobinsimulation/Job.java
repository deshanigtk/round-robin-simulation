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

    private final int processID;
    private final String processName;
    private final int totalBurstTime;
    private int currentBurstTime;
    private boolean isCompleted;
    private int startedTime;
    private int finishedTime;

    public Job(String processName, int processID, int burstTime) {
        isCompleted = false;
        this.processID = processID;
        this.totalBurstTime = burstTime;
        this.processName = processName;
    }

    public void burst(int time) {
        currentBurstTime++;
        if (currentBurstTime == totalBurstTime) {
            //code for process completion
            isCompleted = true;
            finishedTime = time+1;
        }
    }
    
    public void activate(int time){
        startedTime = time;
        currentBurstTime = 0;
    }
    public Integer getProgress() {
        return  Float.valueOf(currentBurstTime / Float.valueOf(totalBurstTime) * 100).intValue();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getPID() {
        return processID;
    }
    
    public String getName(){
        return processName;
    }

    public int getStartTime() {
        return startedTime;
    }

    public int getBurstTime() {
        return currentBurstTime;
    }

    public int getWaitingTime(){
        if(isCompleted){
            return (finishedTime-startedTime-totalBurstTime);
        }else{
            return (Simulator.getSimulator().getProcessorCount()-startedTime-currentBurstTime);
        }
    }
    
    public int getRemainingBT(){
        return totalBurstTime-currentBurstTime;
    }
}
