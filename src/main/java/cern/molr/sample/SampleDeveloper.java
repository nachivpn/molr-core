/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import cern.molr.commons.mission.Mission;
import cern.molr.commons.mission.MissionMaterializer;
import cern.molr.server.MissionDeploymentService;

/**
 * Sample implementation and usage of the developer's interfaces to demonstrate communication
 * 
 * @author nachivpn
 */
public class SampleDeveloper {

    MissionMaterializer materializer = null;
    MissionDeploymentService mDService = new SampleServer().getMissionDeploymentService();
    
    public void developerDeploy() {
        Mission mission = materializer.materialize(RunnableHelloWriter.class);
        mDService.deploy(mission);
    }

}
