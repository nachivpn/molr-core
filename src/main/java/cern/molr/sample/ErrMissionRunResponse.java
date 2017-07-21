/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import java.util.concurrent.Future;

import cern.molr.server.response.MissionRunResponse;

public class ErrMissionRunResponse<T> implements MissionRunResponse<T> {

    String erroMessage;
    
    public ErrMissionRunResponse(String error) {
        this.erroMessage = error;
    }
    
    @Override
    public boolean isSuccesful() {
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
