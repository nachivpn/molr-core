/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.server;

import cern.molr.server.request.MissionAbortRequest;
import cern.molr.server.request.MissionCommandRequest;
import cern.molr.server.request.MissionRunRequest;
import cern.molr.server.response.MissionAbortResponse;
import cern.molr.server.response.MissionCommandResponse;
import cern.molr.server.response.MissionRunResponse;

public interface ClientMissionAPI {

    MissionRunResponse run(MissionRunRequest request);
    
    MissionAbortResponse abort(MissionAbortRequest request);

    MissionCommandResponse command(MissionCommandRequest request);
    
}
