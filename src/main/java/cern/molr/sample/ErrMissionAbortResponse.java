/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import cern.molr.server.response.MissionAbortResponse;

public class ErrMissionAbortResponse implements MissionAbortResponse {

    String erroMessage;
    
    public ErrMissionAbortResponse(String error) {
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

}
