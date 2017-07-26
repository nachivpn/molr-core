/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import cern.molr.server.response.MissionAbortResponse;

public class ErrorMissionAbortResponse implements MissionAbortResponse {

    String erroMessage;
    
    public ErrorMissionAbortResponse(String error) {
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

}
