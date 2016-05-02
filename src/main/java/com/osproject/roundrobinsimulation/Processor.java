/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osproject.roundrobinsimulation;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yasas
 */
public class Processor {

    //status
    private boolean running;
    private int count;

    //properties
    private Job job;
    private int wastedCount;

    //simulation properties
    private int speed;

    public Processor(int speed) {
        this.speed = speed;
        count = 0;
        wastedCount = 0;
    }

    public Job executeJob(Job job) {
        count++;
        wastedCount++;
        Job previousJob = this.job;
        this.job = job;
        Simulator.getSimulator().reportCount(count);
        return previousJob;
    }

    public int getCount() {
        return count;
    }

    public int getWastedCount() {
        return wastedCount;
    }

    public boolean isIdle() {
        return job == null;
    }

    //change simulation properties
    public void changeSpeed(int speed) {
        this.speed = speed;
    }

    public void stop() {
        running = false;
    }

    //processor thread
    public void start() {
        running = true;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (running) {
                    Simulator.getSimulator().reportCount(count);
                    if (job != null) {
                        job.burst(count);
                        if (job.isCompleted()) {
                            job = null;
                        }
                    }
                    count++;
                    try {
                        Thread.sleep(15000 / speed);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        new Thread(r).start();
    }

}
