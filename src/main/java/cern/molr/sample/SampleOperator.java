/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import cern.molr.commons.mission.MissionMode;
import cern.molr.server.MissionExecutionService;
import cern.molr.server.response.MissionRunResponse;
/**
 * Sample implementation and usage of the operator's interfaces to demonstrate communication
 * 
 * @author nachivpn
 */
public class SampleOperator {

    /**
     * Usage of MolR by the operator client (the request-response will be done under the hood later)
     */
    
    MissionExecutionService mExecService = new SampleServer().getMissionExecutionService();
    
    public void operatorRun() {
        /*request execution of the mission*/
        MissionRunResponse<Void> runResp = mExecService.run(
                new SampleMissionRunRequestImpl<Void>(RunnableHelloWriter.class.getName(), null, MissionMode.RUN));
        /*request the result of the mission - not quite applicable here*/
        runResp.getMissionResult();
    }


}
