/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import cern.molr.client.MissionDeploymentService;
import cern.molr.commons.mission.Mission;
import cern.molr.commons.mission.MissionMaterializer;
import cern.molr.exception.MissionMaterializationException;

/**
 * Sample implementation and usage of the developer's interfaces to demonstrate communication
 * 
 * @author nachivpn
 */
public class SampleDeveloper {

    MissionMaterializer materializer;
    MissionDeploymentService mDService;
    
    public SampleDeveloper(MissionMaterializer materializer, MissionDeploymentService mDService) {
        this.materializer = materializer;
        this.mDService = mDService;
    }
    
    public void developerDeploy() throws MissionMaterializationException {
        Mission mission1 = materializer.materialize(RunnableHelloWriter.class);
        mDService.deploy(mission1);
        Mission mission2 = materializer.materialize(IntDoubler.class);
        mDService.deploy(mission2);
    }

}
