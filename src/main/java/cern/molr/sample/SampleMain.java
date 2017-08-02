/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

public class SampleMain {

    public static void main(String args[]) throws Exception {
        
        LocalServer server = new LocalServer();
        
        SampleDeveloper dev = new SampleDeveloper(new AnnotatedMissionMaterializer(), server.getMissionDeploymentService());
        dev.developerDeploy();
        
        SampleOperator op = new SampleOperator(server.getMissionExecutionService());
        op.operatorRun1();
        System.out.println(op.operatorRun2());
        
    }
    
}
