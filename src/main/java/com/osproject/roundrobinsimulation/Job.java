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
    private final int totalBurstTime;
    private int currentBurstTime;
    private boolean isCompleted;
    private int startedTime;

    public Job(int processID, int burstTime) {
        isCompleted = false;
        this.processID = processID;
        this.totalBurstTime = burstTime;
    }

    public void burst() {
        currentBurstTime++;
        if (currentBurstTime == totalBurstTime) {
            //code for process completion
            isCompleted = true;
        }
        System.out.println("Process ID "+processID+" : progress is "+(currentBurstTime/Float.valueOf(totalBurstTime))*100+"%");
    }
    
    public boolean isCompleted(){
        return isCompleted;
    }

    public int getPID() {
        return processID;
    }
    public void setStartTime(int time){
        startedTime = time;
    }
}
