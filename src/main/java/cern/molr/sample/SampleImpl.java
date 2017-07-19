/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import cern.molr.commons.mission.Mission;
import cern.molr.commons.mission.MissionMaterializer;
import cern.molr.server.MissionExecutionService;
import cern.molr.server.request.MissionAbortRequest;
import cern.molr.server.request.MissionContinueRequest;
import cern.molr.server.request.MissionResultRequest;
import cern.molr.server.request.MissionRunRequest;
import cern.molr.server.request.MissionStepRequest;
import cern.molr.server.response.MissionAbortResponse;
import cern.molr.server.response.MissionContinueResponse;
import cern.molr.server.response.MissionResultResponse;
import cern.molr.server.response.MissionRunResponse;
import cern.molr.server.response.MissionStepResponse;
import cern.molr.site.MoleSupervisor;
import cern.molr.type.ACK;
import cern.molr.type.Either;
import cern.molr.type.Null;
/**
 * Sample implementation and usage of the interfaces to demonstrate communication
 * 
 * @author nachivpn
 */
public class SampleImpl {


    MissionExecutionService mExecService = new MissionExecutionService() {
        
        MoleSupervisor moleSupervisor = null;

        @Override
        public <I> MissionRunResponse run(MissionRunRequest<I> request) {
            String missionEId = makeMissionEId();
            Either<Exception,ACK> missionStartResp = moleSupervisor.<I>startMission(request.getMission(), request.getArgs());
            return missionStartResp.<MissionRunResponse>match(
                    (Exception e) -> new MissionRunResponse() {
                        @Override
                        public String getMissionExecutionId() {
                            return null;
                        }
                        @Override
                        public boolean isSuccesful() {
                            return false;
                        }
                        @Override
                        public String getError() {
                            return "Mission start failed";
                        }
                    },
                    (ACK ack) -> new MissionRunResponse() {
                        @Override
                        public String getMissionExecutionId() {
                            return missionEId;
                        }
                        @Override
                        public boolean isSuccesful() {
                            return true;
                        }
                        @Override
                        public String getError() {
                            return null;
                        }
                    });
        }

        @Override
        public <T> MissionResultResponse<T> getResult(MissionResultRequest request) {
            /*TODO: Need to think through*/
            return null;
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
            return null;
        }

        private String makeMissionEId() {
            return "42";
        }

    };

    /**
     * Usage of MolR by the operator client (the request-response will be done under the hood later)
     */
    public void operatorRun() {
        /*materialize a mission from mission defn class*/
        MissionMaterializer materializer = null;
        Mission mission = materializer.materialize(RunnableHelloWriter.class);
        
        /*request execution of the mission*/
        MissionRunResponse runResp = mExecService.run(new MissionRunRequest<Null>() {
            @Override
            public Mission getMission() {
                return mission;
            }
            @Override
            public Null getArgs() {
                return null;
            }
        });
        
        /*request the result of the mission*/
        String missionExecId = runResp.getMissionExecutionId();
        MissionResultResponse<Null> resResp = mExecService.getResult(() -> missionExecId);  //What if result is not ready?
        resResp.getResult().equals(null);
    }


}
