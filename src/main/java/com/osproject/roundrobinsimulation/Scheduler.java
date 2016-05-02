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

    //components for execution
    private final Processor processor;
    private final ReadyQueue readyQueue;

    //data for execution
    private final int timeQuantum;

    //jobs yet to be executed
    private final ArrayList<Job> jobs = new ArrayList<>();

    public Scheduler(int speed, int timeQuantum) {
        processor = new Processor(speed);
        readyQueue = new ReadyQueue();
        this.timeQuantum = timeQuantum;
    }

    //adds a job for execution
    public void addJob(Job job) {
        jobs.add(job);
        job.activate(getProcessorCount());
    }

    private void addJobsToReadyQueue() {
        for (Job process : (ArrayList<Job>) jobs.clone()) {
            if (!readyQueue.isFull()) {
                readyQueue.addJob(process);
                jobs.remove(process);
            } else {
                break;
            }
        }
    }

    public void reportCount(int count) {
        if (count % timeQuantum == 0) {
            addJobsToReadyQueue();
            if (!readyQueue.isEmpty()) {
                readyQueue.addJob(processor.executeJob(readyQueue.getJob()));
            }
        }
    }

    public int getProcessorCount() {
        return processor.getCount();
    }

    public int getExecutionTime() {
        return getProcessorCount() - processor.getWastedCount();
    }

    public String[] getQueueData() {
        return readyQueue.getQueueData();
    }

    public boolean isCompleted() {
        return jobs.isEmpty() && readyQueue.isEmpty() && processor.isIdle();
    }

    //change simulation properties 
    public void changeSpeed(int speed) {
        processor.changeSpeed(speed);
    }

    public void start() {
        processor.start();
    }

    public void stop() {
        processor.stop();
    }
}
