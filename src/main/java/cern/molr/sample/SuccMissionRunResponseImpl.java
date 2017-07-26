/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import java.util.concurrent.Future;

import cern.molr.server.response.MissionRunResponse;

public class SuccMissionRunResponseImpl<T> implements MissionRunResponse<T> {

    String missionExecId;
    Future<T> result;
    
    public SuccMissionRunResponseImpl(String executionId, Future<T> result) {
        this.missionExecId = executionId;
        this.result = result;
    }
    
    @Override
    public boolean isSuccessful() {
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

    @Override
    public Future<T> getMissionResult() {
        return result;
    }

}
