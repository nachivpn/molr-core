/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.supervisor;

import cern.molr.commons.mission.Mission;
import cern.molr.commons.mission.MissionMode;
import cern.molr.type.Ack;
import cern.molr.type.Either;
/**
 * The {@link MoleSupervisor} allows interaction with the mole executing a specific mission.
 * This is the entry point for the server to control a mission execution.
 * 
 * @author nachivpn
 */
public interface MoleSupervisor {

    /**
     * Commands the mole supervisor to start a mission 
     * ... and that's it! Result is reported to server when available. No correspondence of result returned by a start
     * @param m
     * @param args
     * @param missionExecutionId
     * @return
     */
    <I,O> Either<Exception, Ack> startMission(Mission m, I args, MissionMode mode, String missionExecutionId);

    Either<Exception, Ack> continueCurrentMission();
    
    Either<Exception, Ack> abortCurrentMission();
    
    Either<Exception, Ack> stepCurrentMission();
    
}
