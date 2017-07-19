/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.server;
/**
 * {@link MissionExecutionService} is the mole supervisor - server interface
 * Used to report when a mission completes, fails etc.
 * 
 * @author nachivpn
 */
public interface MissionReportService {

    <O> void reportCompletion(String missionId, O result);
    
    void reportException(String missionId, Exception e);

}
