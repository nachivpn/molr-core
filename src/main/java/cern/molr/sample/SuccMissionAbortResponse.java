/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import cern.molr.server.response.MissionAbortResponse;

public class SuccMissionAbortResponse implements MissionAbortResponse {

    @Override
    public boolean isSuccessful() {
        return true;
    }

    @Override
    public String getError() {
        return null;
    }

}
