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
public class Simulator {

    private ArrayList<SimProcess> processes;
    private final ArrayList<ArrayList<Integer>> timeTable = new ArrayList<>();
    private static Simulator simulator;
    private final Processor processor;
    private final boolean finished =false;
    private int count=0;

    private Simulator() {
        processor = new Processor();
    }

    public static Simulator getSimulator() {
        if (simulator == null) {
            simulator = new Simulator();
        }
        return simulator;
    }

    public void updateTimeTable(Integer processID, Integer arrivalTime) {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(processID);
        temp.add(arrivalTime);
        timeTable.add(temp);
    }

    public void addProcess(SimProcess process) {
        processes.add(process);
    }

    private void activateProcess(SimProcess process) {
        process.activate();
    }

    private void simulate() {
        processor.run();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (!finished) {
                    count++;
                    for (ArrayList<Integer> i: timeTable){
                        boolean isFound = false;
                        if(count==i.get(1)){
                            for(SimProcess j: processes){
                                if(j.getPID() == count){
                                    j.activate();
                                    isFound = true;
                                    break;
                                }
                            }
                            if(isFound){
                                break;
                            }
                        }
                    }
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
}
