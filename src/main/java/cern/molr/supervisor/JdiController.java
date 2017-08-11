/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.supervisor;

import cern.molr.client.StepResult;
import cern.molr.type.either.Either;

public interface JdiController {
    
    <T> Either<StepResult,T> stepForward();

    <T> T resume();

    void terminate();
}