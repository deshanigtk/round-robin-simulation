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

    private final Job[] queue = new Job[5];

    public boolean isEmpty() {
        for (int i = 0; i < 5; i++) {
            if (queue[i] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isFull() {
        for (int i = 0; i < 5; i++) {
            if (queue[i] == null) {
                return false;
            }
        }
        return true;
    }

    public Job getProcess() {
        Job nextProcess = queue[4];
        queue[4] = queue[3];
        queue[3] = queue[2];
        queue[2] = queue[1];
        queue[1] = queue[0];
        queue[0] = null;
        return nextProcess;
    }

    public boolean addProcess(Job process) {
        if (isEmpty()){
            queue[4] = process;
            return true;
        }
        if (queue[0] != null) {
            return false;
        }
        for (int i = 1; i < 5; i++) {
            if (queue[i] != null) {
                queue[i - 1] = process;
                return true;
            }
        }
        return false;
    }
}
