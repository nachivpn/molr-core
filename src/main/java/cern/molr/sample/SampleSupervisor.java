/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import cern.molr.commons.mission.Mission;
import cern.molr.commons.mission.MissionStatus;
import cern.molr.supervisor.MoleSupervisor;
import cern.molr.type.ACK;
import cern.molr.type.Either;

/**
 * Sample implementation and usage of the supervisor's interfaces to demonstrate communication
 * 
 * @author nachivpn
 */
public class SampleSupervisor {

    public static class SampleMoleSupervisor implements MoleSupervisor{

        @Override
        public <I> Either<Exception, ACK> startMission(Mission m, I args, String missionExecutionId) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Either<Exception, ACK> continueCurrentMission() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Either<Exception, ACK> abortCurrentMission() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Either<Exception, ACK> stepCurrentMission() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public MissionStatus getCurrentMissionStatus() {
            // TODO Auto-generated method stub
            return null;
        }
        
    }

    public static MoleSupervisor getNewMoleSupervisor() {
        return new SampleMoleSupervisor();
    }
   
}
