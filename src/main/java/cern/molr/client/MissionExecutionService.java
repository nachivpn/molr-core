/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.client;

import java.util.concurrent.CompletableFuture;

/**
 * {@link MisisonExecutionService} is the operator - server interaction interface
 * 
 * @author nachivpn
 */
public interface MissionExecutionService {

    <I,O> CompletableFuture<RunMissionController<O>> runToCompletion(String missionDefnClassName, I args);
    
    <I,O> CompletableFuture<StepMissionController<O>> step(String missionDefnClassName, I args);
    
}
