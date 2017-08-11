/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.res.ser;

import cern.molr.remotesample.res.MissionCancelResponse;
import cern.molr.remotesample.res.MissionCancelResponseFailure;
import cern.molr.remotesample.res.MissionCancelResponseSuccess;

public class MissionCancelResponseDeserializer extends TryResponseDeserializer<MissionCancelResponse>{

    @Override
    public Class<? extends MissionCancelResponse> getSuccessDeserializer() {
        return MissionCancelResponseSuccess.class;
    }

    @Override
    public Class<? extends MissionCancelResponse> getFailureDeserializer() {
        return MissionCancelResponseFailure.class;
    }

}
