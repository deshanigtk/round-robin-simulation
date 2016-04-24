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

    private final SimProcess[] queue = new SimProcess[5];
    private final ReadyQueue readyQueue;

    private ReadyQueue() {
        readyQueue = new ReadyQueue();
    }
    
    public ReadyQueue getReadyQueue(){
        return readyQueue;
    }
    
    public SimProcess getProcess(){
        SimProcess nextProcess = queue[4];
        queue[4] = queue[3];
        queue[3] = queue[2];
        queue[2] = queue[1];
        queue[1] = queue[0];
        queue[0] = null;
        return nextProcess;
    }
    
    public void addProcess(SimProcess process){
        for(int i =0; i<5;i++){
            if(queue[i]!=null){
                
            }
        }
    }
}
