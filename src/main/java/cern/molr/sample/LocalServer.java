/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import cern.molr.client.MissionDeploymentService;
import cern.molr.client.MissionExecutionService;
import cern.molr.client.RunMissionController;
import cern.molr.client.StepMissionController;
import cern.molr.client.StepResult;
import cern.molr.commons.mission.Mission;
import cern.molr.exception.UnknownMissionException;
import cern.molr.supervisor.MoleSupervisor;
import cern.molr.type.Ack;
import cern.molr.type.either.Either;

public class LocalServer{
    
    private Map<String, Mission> missionRegistry = new HashMap<>();
    
    public MissionDeploymentService getMissionDeploymentService() {
        return new LocalDeploymentService();
    }
    
    public MissionExecutionService getMissionExecutionService() {
        return new LocalMissionExecutionService();
    }
    
    public class LocalMissionExecutionService implements MissionExecutionService {

        @Override
        public <I, O> CompletableFuture<RunMissionController<O>> runToCompletion(String missionDefnClassName, I args, Class<I> cI, Class<O> cO) {
            MoleSupervisor moleSupervisor = LocalSupervisor.getNewMoleSupervisor();
            String missionEId = makeMissionEId();

            /*find mission from registry*/
            Optional<Mission> optionalMission = Optional.ofNullable(missionRegistry.get(missionDefnClassName)); 

            return CompletableFuture.supplyAsync(() -> {
                try {
                    return optionalMission.<RunMissionController<O>> map(mission ->
                    new RunMissionController<O>() {
                        @Override
                        public CompletableFuture<O> getResult() {
                            return moleSupervisor.run(mission, args, missionEId);
                        }
                        @Override
                        public CompletableFuture<Ack> cancel() {
                            return moleSupervisor.cancel();
                        }
                    }).orElseThrow(() -> new UnknownMissionException("No such mission registered"));
                } catch (Exception e) {
                    throw new CompletionException(e);
                }
            });

        }

        @Override
        public <I, O> CompletableFuture<StepMissionController<O>> step(String missionDefnClassName, I args) {
            MoleSupervisor moleSupervisor = LocalSupervisor.getNewMoleSupervisor();
            String missionEId = makeMissionEId();

            /*find mission from registry*/
            Optional<Mission> optionalMission = Optional.ofNullable(missionRegistry.get(missionDefnClassName)); 

            return CompletableFuture.supplyAsync(() -> {
                try {
                    return optionalMission.<StepMissionController<O>> map(mission ->
                    new StepMissionController<O>() {
                        @Override
                        public CompletableFuture<Ack> cancel() {
                            return moleSupervisor.cancel();
                        }

                        @Override
                        public CompletableFuture<O> runToCompletion() {
                            return moleSupervisor.resume(mission, args, missionEId);
                        }

                        @Override
                        public CompletableFuture<Either<StepResult, O>> step() {
                            return moleSupervisor.step(mission, args, missionEId);
                        }
                    }).orElseThrow(() -> new UnknownMissionException("No such mission registered"));
                } catch (Exception e) {
                    throw new CompletionException(e);
                }
            });

        }

        private String makeMissionEId() {
            return "42";
        }

    }
    
    
    public class LocalDeploymentService implements MissionDeploymentService{

        private LocalDeploymentService() {}
        
        @Override
        public void deploy(Mission m) {
            missionRegistry.put(m.getMissionDefnClassName(), m);
        }
        
    }


}