/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.res;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import cern.molr.type.trye.Success;

@JsonDeserialize(as = MissionIntegerResponseSuccess.class)
public class MissionIntegerResponseSuccess extends MissionXResponseSuccess<Integer> implements MissionIntegerResponse{

}
