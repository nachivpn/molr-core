/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.client;

import java.util.concurrent.CompletableFuture;

import cern.molr.type.Ack;

public interface RunMissionController<T> {

     CompletableFuture<T> getResult();
    
     //return type Ack may change later
     CompletableFuture<Ack> abort();
}
