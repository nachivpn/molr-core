/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.server.response;

import java.util.concurrent.Future;

public interface MissionRunResponse<T> extends MissionResponse{

    String getMissionExecutionId();
    
    Future<T> getMissionResult();
    
}
