/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.server;

import cern.molr.server.request.MissionAbortRequest;
import cern.molr.server.request.MissionContinueRequest;
import cern.molr.server.request.MissionRunRequest;
import cern.molr.server.request.MissionStepRequest;
import cern.molr.server.response.MissionAbortResponse;
import cern.molr.server.response.MissionContinueResponse;
import cern.molr.server.response.MissionRunResponse;
import cern.molr.server.response.MissionStepResponse;

/**
 * {@link MisisonExecutionService} is the operator - server interaction interface
 * 
 * @author nachivpn
 */
public interface MissionExecutionService {

    <I,O> MissionRunResponse<O> run(MissionRunRequest<I> request);
    
    MissionAbortResponse abort(MissionAbortRequest request);

    MissionContinueResponse continue_(MissionContinueRequest request);
    
    MissionStepResponse step(MissionStepRequest request);
    
}
