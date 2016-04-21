/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osproject.roundrobinsimulation;

import java.util.Queue;

/**
 *
 * @author yasas
 */
public class ReadyQueue {

    private Queue queue;
    private final ReadyQueue readyQueue;

    private ReadyQueue() {
        readyQueue = new ReadyQueue();
    }
    
    public ReadyQueue getReadyQueue(){
        return readyQueue;
    }
}
