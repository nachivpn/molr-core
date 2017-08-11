/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.res;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import cern.molr.remotesample.res.bean.MissionExecutionResponseBean;
import cern.molr.remotesample.res.ser.MissionCancelResponseDeserializer;
import cern.molr.remotesample.res.ser.MissionExecutionResponseDeserializer;
import cern.molr.type.Ack;

@JsonDeserialize(using = MissionCancelResponseDeserializer.class)
public interface MissionCancelResponse extends TryResponse<Ack>{

}
