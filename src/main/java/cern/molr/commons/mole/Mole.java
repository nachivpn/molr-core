/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.commons.mole;

import cern.molr.commons.mission.Mission;

public interface Mole {

    <I, O> O run(Mission<I, O> mission, I args);
    
}
