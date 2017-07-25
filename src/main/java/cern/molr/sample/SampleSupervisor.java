/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import java.util.Optional;

import cern.molr.commons.mission.Mission;
import cern.molr.commons.mission.MissionMode;
import cern.molr.commons.mission.MissionStatus;
import cern.molr.commons.mole.Mole;
import cern.molr.exception.ModeMismatchException;
import cern.molr.supervisor.MoleSpawner;
import cern.molr.supervisor.MoleSupervisor;
import cern.molr.supervisor.Session;
import cern.molr.type.ACK;
import cern.molr.type.Either;

/**
 * Sample implementation and usage of the supervisor's interfaces to demonstrate communication
 * 
 * @author nachivpn
 */
public class SampleSupervisor {

    private static final ACK ack = new ACK();
    
    public static class SampleMoleSupervisor implements MoleSupervisor{

        private Optional<Session> optionalSession = Optional.of(null);
        
        @Override
        public <I,O> Either<Exception, ACK> startMission(Mission mission, I args, 
                MissionMode mode, String missionExecutionId) {
            try {
                if (mode == MissionMode.RUN) {
                    new RunMoleSpawner().<I,O>spawnMoleRunner(mission, args);
                } else if (mode == MissionMode.STEP) {
                    Session s = new StepMoleSpawner().<I,O>spawnMoleRunner(mission, args);
                    setSession(s);
                }
                return result(ack);
            } catch (Exception e) {
                return exception(e);
            }
        }

        @Override
        public Either<Exception, ACK> abortCurrentMission() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Either<Exception, ACK> stepCurrentMission() {
            try {
                Session s = optionalSession.orElseThrow(
                        () -> new ModeMismatchException("Cannot step through running mission"));
                s.getController().stepForward();
                return result(ack);
            } catch (Exception e) {
                return exception(e);
            }
        }

        @Override
        public MissionStatus getCurrentMissionStatus() {
            return null;
        }
        
        @Override
        public Either<Exception, ACK> continueCurrentMission() {
            try {
                Session s = optionalSession.orElseThrow(
                        () -> new ModeMismatchException("Cannot continue running mission"));
                s.getController().resume();
                return result(ack);
            } catch (Exception e) {
                return exception(e);
            }
        }

        public void setSession(Session session) {
            this.optionalSession = Optional.of(session);
        }

        private static <R> Either<Exception,R> result(R result){
            return new Either.Right<>(result);
        }
        
        private static <R> Either<Exception,R> exception(Exception e){
            return new Either.Left<>(e);
        }
        
    }

    public static class RunMoleSpawner implements MoleSpawner<Void>{

        @Override
        public <I,O> Void spawnMoleRunner(Mission m, I args) throws Exception {
            String moleClassName = m.getMoleClassName();
            @SuppressWarnings("unchecked")
            Class<Mole<I,O>> moleClass = (Class<Mole<I, O>>) Class.forName(moleClassName);
            Mole<I,O> mole = moleClass.getConstructor().newInstance();
            mole.run(m, args);
            return null;
        }

    }

    public static class StepMoleSpawner implements MoleSpawner<Session>{

        @Override
        public <I,O> Session spawnMoleRunner(Mission m, I args) throws Exception {
            return null;
        }

    }

    

    public static MoleSupervisor getNewMoleSupervisor() {
        return new SampleMoleSupervisor();
    }
    
}
