/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.res.ser;

import cern.molr.remotesample.res.MissionExecutionResponse;
import cern.molr.remotesample.res.MissionExecutionResponseFailure;
import cern.molr.remotesample.res.MissionExecutionResponseSuccess;

public class MissionExecutionResponseDeserializer  extends TryResponseDeserializer<MissionExecutionResponse>{

    @Override
    public Class<? extends MissionExecutionResponse> getSuccessDeserializer() {
        return MissionExecutionResponseSuccess.class;
    }

    @Override
    public Class<? extends MissionExecutionResponse> getFailureDeserializer() {
        return MissionExecutionResponseFailure.class;
    }
    
    
}