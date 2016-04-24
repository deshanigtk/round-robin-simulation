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
public class Scheduler {

    private int timeQuantum;
    private int count;

    public void run() {
        count = 0;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if(count%timeQuantum == 0){
                    
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        new Thread(r).start();
    }
}
