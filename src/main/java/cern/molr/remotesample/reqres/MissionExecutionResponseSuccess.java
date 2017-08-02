/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.reqres;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import cern.molr.type.trye.Success;

@JsonDeserialize(as = MissionExecutionResponseSuccess.class)
public class MissionExecutionResponseSuccess extends Success<String> implements MissionExecutionResponse{

    public MissionExecutionResponseSuccess() {
        super(null);
    }
    
    /**
     * @param r
     */
    public MissionExecutionResponseSuccess(String r) {
        super(r);
    }

    @Override
    public String getMissionExecutionId() {
        return this.r;
    }

    @Override
    public void setMissionExecutionId(String missionExecutionId) {
        this.r = missionExecutionId;
    }

    @Override
    public Exception getException() {
        return null;
    }

    @Override
    public void setException(Exception e) {
        return;
    }

    @Override
    public String getResult() {
        return getMissionExecutionId();
    }

    @Override
    public void setResult(String r) {
        setMissionExecutionId(r);
    }

}
