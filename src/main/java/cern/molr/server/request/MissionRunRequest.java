/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.server.request;

public interface MissionRunRequest<T> {

    String getMissionDefnClassName();
    
    T getArgs();
    
}
