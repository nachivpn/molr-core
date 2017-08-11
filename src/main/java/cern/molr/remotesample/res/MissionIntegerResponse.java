/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.res;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import cern.molr.remotesample.res.ser.MissionIntegerResponseDeserializer;

@JsonDeserialize(using = MissionIntegerResponseDeserializer.class)
public interface MissionIntegerResponse extends MissionXResponse<Integer> {
}
