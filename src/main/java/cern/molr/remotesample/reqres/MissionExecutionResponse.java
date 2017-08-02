/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.reqres;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = MissionExecutionResponseDeserializer.class)
public interface MissionExecutionResponse extends TryResponse<String>{

    public String getMissionExecutionId();

    public void setMissionExecutionId(String missionExecutionId);

}
