/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.commons.mission;

public interface MissionMaterializer {

    Mission materialize(Class<?> classType);
    
}
