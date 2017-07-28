/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.supervisor;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import cern.molr.commons.mission.Mission;
import cern.molr.commons.mission.MissionStatus;

/**
 * A generic {@link RunSession}, encapsulates the information of a currently running {@link Mission}
 *
 * @author nachivpn
 */
public interface RunSession<O> extends Session{

    /**
     * Get the link to thread running the mission
     * @return
     */
    Thread getRunnerThread();
    
    /**
     * get exception, maybe?
     * @return
     */
    Optional<Exception> getException();
    
    /**
     * get status of a running mission
     * @return
     */
    MissionStatus getMissionStatus();

    
    CompletableFuture<O> getResult();
    
}
