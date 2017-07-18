/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.site;

import cern.molr.commons.mission.Mission;

public interface MoleSpawner<T> {

    <I,O> T spawnMoleRunner(Mission<I, O> arg0, I args) throws Exception;

}
