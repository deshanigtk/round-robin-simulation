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
public class ReadyQueue {

    private final Job[] queue = new Job[4];

    public boolean addJob(Job job) {
        if (isEmpty()) {
            queue[3] = job;
            return true;
        }
        if (queue[0] != null) {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            if (queue[i] != null) {
                queue[i - 1] = job;
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        for (int i = 0; i < 4; i++) {
            if (queue[i] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isFull() {
        for (int i = 0; i < 4; i++) {
            if (queue[i] == null) {
                return false;
            }
        }
        return true;
    }

    public Job getJob() {
        Job nextJob = queue[3];
        queue[3] = queue[2];
        queue[2] = queue[1];
        queue[1] = queue[0];
        queue[0] = null;
        return nextJob;
    }

    //get content as String array
    public String[] getQueueData() {
        String[] queueData = new String[4];
        for (int i = 0; i < 4; i++) {
            queueData[i] = (String.valueOf(queue[i] != null ? queue[i].getID() : "-"));
        }
        return queueData;
    }
}
