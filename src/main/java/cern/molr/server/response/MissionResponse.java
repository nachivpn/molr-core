/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.server.response;

public interface MissionResponse {

    public boolean isSuccessful();
    
    public String getError();
    
}
