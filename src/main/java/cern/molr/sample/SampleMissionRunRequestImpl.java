/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import cern.molr.server.request.MissionRunRequest;

public class SampleMissionRunRequestImpl<T> implements MissionRunRequest<T> {

    String missionClassName;
    T args;
    
    public SampleMissionRunRequestImpl(String missionClassName, T args) {
        this.missionClassName = missionClassName;
        this.args = args;
    }
    
    @Override
    public String getMissionDefnClassName() {
        return missionClassName;
    }

    @Override
    public T getArgs() {
        return args;
    }

}
