/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import cern.molr.commons.mission.MissionMode;
import cern.molr.server.request.MissionRunRequest;

public class SampleMissionRunRequestImpl<T> implements MissionRunRequest<T> {

    String missionClassName;
    T args;
    MissionMode mode;
    
    public SampleMissionRunRequestImpl(String missionClassName, T args, MissionMode mode) {
        this.missionClassName = missionClassName;
        this.args = args;
        this.mode = mode;
    }
    
    @Override
    public String getMissionDefnClassName() {
        return missionClassName;
    }

    @Override
    public T getArgs() {
        return args;
    }

    @Override
    public MissionMode getMissionMode() {
        return mode;
    }

}
