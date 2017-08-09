/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.rservice;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Service;

import cern.molr.commons.mission.Mission;
import cern.molr.commons.mission.MissionMaterializer;
import cern.molr.exception.MissionMaterializationException;
import cern.molr.exception.UnknownMissionException;
import cern.molr.remotesample.MissionExecutionServiceImpl;
import cern.molr.sample.AnnotatedMissionMaterializer;
import cern.molr.sample.Fibonacci;
import cern.molr.sample.IntDoubler;
import cern.molr.sample.LocalSupervisor;
import cern.molr.sample.RunnableHelloWriter;
import cern.molr.supervisor.MoleSupervisor;
import cern.molr.type.Ack;
/**
 * Gateway used for communication between {@link MissionExecutionServiceImpl} amd {@link MissionExecutionServiceImpl}
 * 
 * @author nachivpn
 */
@Service
public class ServerRestExecutionService {

    ServerState registry = new ServerState();

    public ServerRestExecutionService() {
        //TODO remove this init code
        MissionMaterializer m = new AnnotatedMissionMaterializer();
        try {
            registry.registerNewMission(m.materialize(RunnableHelloWriter.class));
            registry.registerNewMission(m.materialize(IntDoubler.class));
            registry.registerNewMission(m.materialize(Fibonacci.class));
        } catch (MissionMaterializationException e) {
            throw new RuntimeException(e);
        }
    }

    public <I,O> String runMission(String missionDefnClassName, I args) throws UnknownMissionException {
        String missionEId = makeEId();
        MoleSupervisor moleSupervisor = LocalSupervisor.getNewMoleSupervisor();
        return registry.getMission(missionDefnClassName).map(mission ->{
            CompletableFuture<O> cf = moleSupervisor.run(mission, args, missionEId);
            registry.registerNewMissionExecution(missionEId, moleSupervisor, cf);
            return missionEId;
        }).orElseThrow(() -> new UnknownMissionException("No such mission known!"));
    }

    public CompletableFuture<?> getResult(String mEId) throws UnknownMissionException{
        Optional<CompletableFuture<?>> optionalResultFuture = registry.getMissionExecutionFuture(mEId);
        return optionalResultFuture.orElseThrow(() -> new UnknownMissionException("No such mission running"));
    }

    private String makeEId() {
        return UUID.randomUUID().toString();
    }

    public static class ServerState {

        /* State of the server */
        private ConcurrentMap<String, Mission> missionRegistry = new ConcurrentHashMap<>();
        private ConcurrentMap<String, CompletableFuture<?>> missionExecutionRegistry =  new ConcurrentHashMap<>();
        private ConcurrentMap<String, MoleSupervisor> moleSupervisorRegistry =  new ConcurrentHashMap<>();

        public void registerNewMission(Mission m) {
            missionRegistry.put(m.getMissionDefnClassName(), m);
        }

        public void registerNewMissionExecution(String missionId, MoleSupervisor ms, CompletableFuture<?> cf) {
            moleSupervisorRegistry.put(missionId, ms);
            missionExecutionRegistry.put(missionId, cf);
        }

        public Optional<Mission> getMission(String mName) {
            return Optional.ofNullable(missionRegistry.get(mName));
        }

        public Optional<CompletableFuture<?>> getMissionExecutionFuture(String missionEId){
            return Optional.ofNullable(missionExecutionRegistry.get(missionEId));
        }

        public MoleSupervisor getMoleSupervisor(String missionExecutionId) {
            return moleSupervisorRegistry.get(missionExecutionId);
        }

    }

    /**
     * @param missionExecutionId
     * @return
     * @throws UnknownMissionException 
     */
    public CompletableFuture<Ack> cancel(String missionExecutionId) throws UnknownMissionException {
        Optional<MoleSupervisor> optionalSupervisor = Optional.ofNullable(registry.getMoleSupervisor(missionExecutionId));
        return optionalSupervisor
                .orElseThrow(() -> new UnknownMissionException("No such mission running"))
                .cancel();
    }

}
