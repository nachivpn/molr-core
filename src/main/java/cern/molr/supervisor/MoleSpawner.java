/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.supervisor;

import cern.molr.commons.mission.Mission;
/**
 * The {@link MoleSpawner} runs a given mission by instantiating (or spawning) a mole and asking it to run the mission
 * 
 * @author nachivpn 
 * @param <T> - return type of the span action (In step through mode, it could be session)
 */
public interface MoleSpawner<T> {

    <I,O> T spawnMoleRunner(Mission m, I args) throws Exception;

}
