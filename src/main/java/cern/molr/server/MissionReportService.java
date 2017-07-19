/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.server;
/**
 * {@link MissionExecutionService} is the mission host - server interface
 * 
 * @author nachivpn
 */
public interface MissionReportService {

    <O> void reportCompletion(String missionId, O result);
    
    void reportException(String missionId, Exception e);

}
