/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import cern.molr.server.response.MissionRunResponse;

public class SuccMissionRunResponseImpl implements MissionRunResponse {

    String missionExecId;
    
    public SuccMissionRunResponseImpl(String executionId) {
        this.missionExecId = executionId;
    }
    
    @Override
    public boolean isSuccesful() {
        return true;
    }

    @Override
    public String getError() {
        return null;
    }

    @Override
    public String getMissionExecutionId() {
        return missionExecId;
    }

}
