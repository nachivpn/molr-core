/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.reqres;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import cern.molr.type.trye.Failure;

@JsonDeserialize(as = MissionExecutionResponseFailure.class)
public class MissionExecutionResponseFailure extends Failure<String> implements MissionExecutionResponse{

    /**
     */
    public MissionExecutionResponseFailure() {
        super(null);
    }
    /**
     * @param l
     */
    public MissionExecutionResponseFailure(Exception l) {
        super(l);
    }

    @Override
    public String getMissionExecutionId() {
        return null;
    }

    @Override
    public void setMissionExecutionId(String missionExecutionId) {
        return;
    }
    @Override
    public Exception getException() {
        return this.l;
    }
    @Override
    public void setException(Exception e) {
        this.l = e;
    }
    @Override
    public String getResult() {
        return null;
    }
    @Override
    public void setResult(String r) {
        return;
    }


}
