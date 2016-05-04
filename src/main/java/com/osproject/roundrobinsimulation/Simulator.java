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
public class Simulator {

    //user requirements
    private final ArrayList<Job> jobs;
    private final ArrayList<int[]> schedule = new ArrayList<>();

    private Scheduler scheduler;

    //properties for simulation
    private int sentJobCount;
    private boolean completed;

    //singleton
    private static Simulator simulator;

    private Simulator() {
        jobs = new ArrayList<>();
        sentJobCount = 0;
        completed = false;
    }

    public static Simulator getSimulator() {
        if (simulator == null) {
            simulator = new Simulator();
        }
        return simulator;
    }

    public static void refreshSimulator() {
        simulator = null;
    }

    public void setSimulator(int speed, int timeQuantum) {
        scheduler = new Scheduler(speed, timeQuantum);
    }

    public int getProcessorCount() {
        return scheduler.getProcessorCount();
    }

    public Job getJob(int index) {
        return jobs.get(index);
    }

    public int getJobCount() {
        return jobs.size();
    }

    public String[] getQueueData() {
        return scheduler.getQueueData();
    }

    public int getExecutionTime() {
        return scheduler.getExecutionTime();
    }

    public float getAverageTurnaroundTime() {
        return ((float) scheduler.getProcessorCount()) / jobs.size();
    }

    //setup simulation
    public void updateSchedule(int id, int arrivalTime) {
        schedule.add(new int[]{id, arrivalTime});
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public float getAverageWaitingTime() {
        int totalWaitingTime = 0;
        for (int i = 0; i < jobs.size(); i++) {
            totalWaitingTime += jobs.get(i).getWaitingTime();
        }
        return ((float) totalWaitingTime) / jobs.size();
    }

    public void reportCount(int count) {
        if (!completed) {
            for (int[] i : schedule) {
                if (count == i[1]) {
                    for (Job j : jobs) {
                        if (j.getID() == i[0]) {
                            scheduler.addJob(j);
                            sentJobCount++;
                            break;
                        }
                    }
                }
            }
            if (jobs.size() == sentJobCount) {
                if (scheduler.isCompleted()) {
                    completed = true;
                    stop();
                }
            }
        }
        scheduler.reportCount(count);
    }

    public boolean isCompleted() {
        return completed;
    }

    //change simulation properties
    public void changeSpeed(int speed) {
        scheduler.changeSpeed(speed);
    }

    public void start() {
        scheduler.start();
    }

    public void stop() {
        scheduler.stop();
    }
}
