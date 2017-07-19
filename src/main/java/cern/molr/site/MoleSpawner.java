/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.site;

import cern.molr.commons.mission.Mission;

public interface MoleSpawner<T> {

    <I> T spawnMoleRunner(Mission m, I args) throws Exception;

}
