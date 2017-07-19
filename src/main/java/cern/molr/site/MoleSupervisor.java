/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.site;

import cern.molr.commons.mission.Mission;
import cern.molr.commons.mission.MissionStatus;
import cern.molr.type.ACK;
import cern.molr.type.Either;
/**
 * The {@link MoleSupervisor} allows interaction with the mole executing a specific mission.
 * This is the entry point for the server to control a mission execution.
 * 
 * @author nachivpn
 */
public interface MoleSupervisor {

    <I> Either<Exception, ACK> startMission(Mission m, I args);

    Either<Exception, ACK> continueCurrentMission();
    
    Either<Exception, ACK> abortCurrentMission();
    
    Either<Exception, ACK> stepCurrentMission();
    
    MissionStatus getCurrentMissionStatus();
    
}
