/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import java.util.concurrent.CompletableFuture;

import cern.molr.client.MissionExecutionService;
import cern.molr.client.RunMissionController;
/**
 * Sample implementation and usage of the operator's interfaces to demonstrate communication
 * 
 * @author nachivpn
 */
public class SampleOperator {

    /**
     * Usage of MolR by the operator client (the request-response will be done under the hood later)
     */

    MissionExecutionService mExecService = new SampleMissionExecutionService();

    public void operatorRun() throws Exception{
        CompletableFuture<RunMissionController<Void>> futureController = mExecService.<Void, Void>runToCompletion("cern.molr.sample.RunnableHelloWriter", null);
        try {
            RunMissionController<Void> controller = futureController.get();
            CompletableFuture<Void> futureResult = controller.getResult();
            futureResult.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


}
