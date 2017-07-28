/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.client;

import java.util.concurrent.CompletableFuture;

import cern.molr.type.Ack;
import cern.molr.type.Either;

public interface StepMissionController<T> {
    
    CompletableFuture<Ack> abort();

    CompletableFuture<T> runToCompletion();

    CompletableFuture<Either<StepResult, T>> step();
}
