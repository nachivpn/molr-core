/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.res.ser;

import cern.molr.remotesample.res.MissionIntegerResponse;
import cern.molr.remotesample.res.MissionIntegerResponseFailure;
import cern.molr.remotesample.res.MissionIntegerResponseSuccess;

public class MissionIntegerResponseDeserializer extends TryResponseDeserializer<MissionIntegerResponse>{

    @Override
    public Class<? extends MissionIntegerResponse> getSuccessDeserializer() {
        return MissionIntegerResponseSuccess.class;
    }

    @Override
    public Class<? extends MissionIntegerResponse> getFailureDeserializer() {
        return MissionIntegerResponseFailure.class;
    }
    
}
