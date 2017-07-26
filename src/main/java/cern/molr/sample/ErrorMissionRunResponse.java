/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import java.util.concurrent.Future;

import cern.molr.server.response.MissionRunResponse;

public class ErrorMissionRunResponse<T> implements MissionRunResponse<T> {

    String erroMessage;
    
    public ErrorMissionRunResponse(String error) {
        this.erroMessage = error;
    }
    
    @Override
    public boolean isSuccessful() {
        return false;
    }

    @Override
    public String getError() {
        return erroMessage;
    }

    @Override
    public String getMissionExecutionId() {
        return null;
    }

    @Override
    public Future<T> getMissionResult() {
        return null;
    }

}
