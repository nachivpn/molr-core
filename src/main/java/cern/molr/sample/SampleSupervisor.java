/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import java.util.Date;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import cern.molr.commons.mission.Mission;
import cern.molr.commons.mission.MissionMode;
import cern.molr.commons.mission.MissionStatus;
import cern.molr.commons.mole.Mole;
import cern.molr.exception.MissionExecutionException;
import cern.molr.exception.ModeMismatchException;
import cern.molr.supervisor.MoleSpawner;
import cern.molr.supervisor.MoleSupervisor;
import cern.molr.supervisor.RunSession;
import cern.molr.supervisor.StepSession;
import cern.molr.type.Ack;
import cern.molr.type.Either;

/**
 * Sample implementation and usage of the supervisor's interfaces to demonstrate communication
 * 
 * @author nachivpn
 */
public class SampleSupervisor {

    private static final Ack ACK = new Ack();
    
    public static class SampleMoleSupervisor implements MoleSupervisor{

        private Optional<StepSession> optionalStepSession = Optional.of(null);
        private Optional<RunSession> optionalRunSession = Optional.of(null);
        
        @Override
        public <I,O> Either<Exception, Ack> startMission(Mission mission, I args, 
                MissionMode mode, String missionExecutionId) {
            try {
                if (mode == MissionMode.RUN) {
                    RunSession rs = new RunMoleSpawner().<I,O>spawnMoleRunner(mission, args);
                    setRunSession(rs);
                } else if (mode == MissionMode.STEP) {
                    StepSession ss = new StepMoleSpawner().<I,O>spawnMoleRunner(mission, args);
                    setStepSession(ss);
                }
                return result(ACK);
            } catch (Exception e) {
                return exception(e);
            }
        }

        @Override
        public Either<Exception, Ack> abortCurrentMission() {
            new EventualExit().exitIn(30000);
            return result(ACK);
        }

        @Override
        public Either<Exception, Ack> stepCurrentMission() {
            try {
                StepSession s = optionalStepSession.orElseThrow(
                        () -> new ModeMismatchException("Cannot step through running mission"));
                s.getController().stepForward();
                return result(ACK);
            } catch (Exception e) {
                return exception(e);
            }
        }

        @Override
        public Either<Exception, Ack> continueCurrentMission() {
            try {
                StepSession s = optionalStepSession.orElseThrow(
                        () -> new ModeMismatchException("Cannot continue running mission"));
                s.getController().resume();
                return result(ACK);
            } catch (Exception e) {
                return exception(e);
            }
        }

        public void setStepSession(StepSession session) {
            this.optionalStepSession = Optional.of(session);
        }
        
        public void setRunSession(RunSession session) {
            this.optionalRunSession = Optional.of(session);
        }

        private static <R> Either<Exception,R> result(R result){
            return new Either.Right<>(result);
        }
        
        private static <R> Either<Exception,R> exception(Exception e){
            return new Either.Left<>(e);
        }
        
    }

    /**
     * 
     * Mole spawner impl for run mode
     * @author nachivpn 
     * @param <I>
     * @param <O>
     */
    public static class RunMoleSpawner implements MoleSpawner<RunSession>{

        @Override
        public <I,O> RunSession spawnMoleRunner(Mission mission, I args) throws Exception {
            String moleClassName = mission.getMoleClassName();
            @SuppressWarnings("unchecked")
            Class<Mole<I,O>> moleClass = (Class<Mole<I, O>>) Class.forName(moleClassName);
            Mole<I,O> mole = moleClass.getConstructor().newInstance();
            SampleRunSession<I,O> runSession= new SampleRunSession<>(mole, mission, args);
            runSession.startSession();
            return runSession;
        }

    }

    /**
     * Mole spawner impl for step mode
     * 
     * @author nachivpn 
     * @param <I>
     * @param <O>
     */
    public static class StepMoleSpawner implements MoleSpawner<StepSession>{

        @Override
        public <I, O> StepSession spawnMoleRunner(Mission m, I args) throws Exception {
            return null;
        }


    }
    
    public static class SampleRunSession <I,O> implements RunSession{
        
        private Mole<I,O> mole;
        private Mission mission;
        private I args;
        private Thread runnerThread;
        private Exception sessionException;
        private MissionStatus status;
        
        public SampleRunSession(Mole<I,O> mole, Mission mission, I args) {
            this.mole = mole;
            this.mission = mission;
            this.args = args;
            this.runnerThread = newRunnerThread();
        }
        
        public Thread newRunnerThread() {
            return new Thread(() -> {
                try {
                    mole.run(mission, args);
                    status = MissionStatus.SUCCESS;
                } catch (MissionExecutionException e1) {
                    sessionException = e1;
                    status = MissionStatus.FAILED;
                }
            });
        }
        
        @Override
        public Thread getRunnerThread() {
            return runnerThread;
        }
        
        @Override
        public void startSession() {
            runnerThread.start();
        }

        @Override
        public Optional<Exception> getException() {
            return Optional.of(sessionException);
        }

        @Override
        public MissionStatus getMissionStatus() {
            return status;
        }

    }

    public static class EventualExit extends TimerTask{
        
        Timer timer = new Timer();
        
        public void exitIn(long timeInMillis) {
            timer.schedule(this, new Date(System.currentTimeMillis()+timeInMillis));
        }
        
        @Override
        public void run() {
            System.exit(1);
        }
    }

    public static MoleSupervisor getNewMoleSupervisor() {
        return new SampleMoleSupervisor();
    }
    
}
