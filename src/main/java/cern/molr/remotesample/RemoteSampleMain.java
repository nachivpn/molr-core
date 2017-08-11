/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample;

import cern.molr.sample.SampleOperator;

public class RemoteSampleMain {

    public static void main(String[] args) throws Exception {
        SampleOperator operator = new SampleOperator(new MissionExecutionServiceImpl());
        System.out.println("The meaning of life is " + operator.operatorRun3());
    }
    
}
