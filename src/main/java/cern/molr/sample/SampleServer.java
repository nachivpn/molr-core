/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;

import cern.molr.commons.mission.Mission;
import cern.molr.server.MissionDeploymentService;
import cern.molr.server.MissionExecutionService;
import cern.molr.server.request.MissionAbortRequest;
import cern.molr.server.request.MissionContinueRequest;
import cern.molr.server.request.MissionRunRequest;
import cern.molr.server.request.MissionStepRequest;
import cern.molr.server.response.MissionAbortResponse;
import cern.molr.server.response.MissionContinueResponse;
import cern.molr.server.response.MissionRunResponse;
import cern.molr.server.response.MissionStepResponse;
import cern.molr.supervisor.MoleSupervisor;
import cern.molr.type.Ack;
import cern.molr.type.Either;

/**
 * Sample implementation and usage of the server interfaces to demonstrate communication
 * 
 * @author nachivpn
 */
public class SampleServer {

    /* State of the server */
    private Map<String, Mission> missionRegistry = new HashMap<>();
    private Map<String,MoleSupervisor> missionExecutionRegistry = new HashMap<>();

    public class SampleMissionExecutionService implements MissionExecutionService{

        @Override
        public <I,O> MissionRunResponse<O> run(MissionRunRequest<I> request) {

            /*new mission id and freshly spawned mole supervisor*/
            String missionEId = makeMissionEId();
            MoleSupervisor moleSupervisor = SampleSupervisor.getNewMoleSupervisor();

            /*store the mole supervisor in local registry*/
            missionExecutionRegistry.put(missionEId, moleSupervisor);

            /*find mission from registry*/
            Optional<Mission> optionalMission = Optional.of(missionRegistry.get(request.getMissionDefnClassName())); 

            /*return response with a future<result>*/
            return optionalMission.map(mission -> {
                Either<Exception,Ack> missionStartResp = moleSupervisor.<I,O>startMission(mission, request.getArgs(),request.getMissionMode(), missionEId);
                return missionStartResp.<MissionRunResponse<O>>match(
                        (Exception e) -> new ErrorMissionRunResponse<O>("Mission start failed"),
                        (Ack ack) -> new SuccMissionRunResponseImpl<O>(missionEId,makeMagicalFuture(missionEId))
                        );}
                    ).orElse(new ErrorMissionRunResponse<O>("Mission not registered"));
        }

        @Override
        public MissionStepResponse step(MissionStepRequest request) {
            return null;
        }

        @Override
        public MissionContinueResponse continue_(MissionContinueRequest request) {
            return null;
        }

        @Override
        public MissionAbortResponse abort(MissionAbortRequest request) {
            String missionEId = request.getMissionId();

            Optional<MoleSupervisor> optionalMoleSupervisor = Optional.of(missionExecutionRegistry.get(missionEId));
            
            return optionalMoleSupervisor.map(moleSupervisor -> {
                Either<Exception,Ack> missionStartResp = moleSupervisor.abortCurrentMission();
                return missionStartResp.<MissionAbortResponse>match(
                        (Exception e) -> new ErrorMissionAbortResponse("Mission abort failed"),
                        (Ack ack) -> new SuccMissionAbortResponse()
                        );}
                    ).orElse(new ErrorMissionAbortResponse("No such Mission is running"));
        }

        private String makeMissionEId() {
            return "42";
        }
        
        /**
         * This method makes a Future object for the operator to wait over
         * @param missionEId
         * @return
         */
        private <T> Future<T> makeMagicalFuture(String missionEId){
            //TODO :)
            return null;
        }

    }

    public class SampleMissionDeploymentService implements MissionDeploymentService{

        @Override
        public void deploy(Mission m) {
            missionRegistry.put(m.getMissionDefnClassName(), m);
        }

    }

    public MissionExecutionService getMissionExecutionService() {
        return new SampleMissionExecutionService();
    }

    public MissionDeploymentService getMissionDeploymentService() {
        return new SampleMissionDeploymentService();
    }

}
