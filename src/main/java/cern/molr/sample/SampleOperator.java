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

    MissionExecutionService mExecService;

    public SampleOperator( MissionExecutionService mExecService) {
        this.mExecService = mExecService;
    }
    
    public void operatorRun1() throws Exception{
        CompletableFuture<RunMissionController<Void>> futureController = mExecService.<Void, Void>runToCompletion("cern.molr.sample.RunnableHelloWriter", null, Void.class, Void.class);
        try {
            RunMissionController<Void> controller = futureController.get();
            CompletableFuture<Void> futureResult = controller.getResult();
            futureResult.get();
        } catch (Exception e) {
            //handle
            System.err.println("Operator received an error:");
            e.printStackTrace();
            throw e;
        }
    }
    
    public Integer operatorRun2() throws Exception{
        CompletableFuture<RunMissionController<Integer>> futureController = mExecService.<Integer, Integer>runToCompletion("cern.molr.sample.IntDoubler", 21, Integer.class, Integer.class);
        try {
            RunMissionController<Integer> controller = futureController.get();
            CompletableFuture<Integer> futureResult = controller.getResult();
            return futureResult.get();
        } catch (Exception e) {
            //handle
            System.err.println("Operator received an error:");
            e.printStackTrace();
            throw e;
        }
        
    }
    
}
