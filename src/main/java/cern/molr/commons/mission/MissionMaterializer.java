/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.commons.mission;

/**
 * Used to converts a given mission definition class file into a mission object
 * 
 * @author nachivpn
 */
public interface MissionMaterializer {

    Mission materialize(Class<?> classType);
    
}
