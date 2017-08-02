/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.reqres;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import cern.molr.type.trye.Try;

@JsonDeserialize(using = MissionIntegerResponseDeserializer.class)
public interface MissionIntegerResponse extends TryResponse<Integer> {
}
