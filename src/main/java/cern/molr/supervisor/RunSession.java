/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.supervisor;

import java.util.concurrent.CompletableFuture;

import cern.molr.commons.mission.Mission;

/**
 * A generic {@link RunSession}, encapsulates the information of a currently running {@link Mission}
 *
 * @author nachivpn
 */
public interface RunSession<O>{

    CompletableFuture<O> getResult();
    
}
