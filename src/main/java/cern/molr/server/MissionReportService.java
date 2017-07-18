/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.server;

public interface MissionReportService {

    void reportCompletion(String missionId);
    
    void reportException(String missionId, Exception e);

}
