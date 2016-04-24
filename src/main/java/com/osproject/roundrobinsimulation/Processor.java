/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osproject.roundrobinsimulation;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yasas
 */
public class Processor {

    private ReadyQueue readyQueue;
    private final boolean finished = false;
    private SimProcess runningProcess;
    private SimProcess prevProcess;

    public void run() {
        Runnable r = new Runnable() {
            @Override
            public void run() {

                while (!finished) {
                    runningProcess.burst();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        };
        new Thread(r).start();
    }
    
    public SimProcess executeProcess(SimProcess process){
        prevProcess = runningProcess;
        runningProcess = process;
        return prevProcess;
    }
}
