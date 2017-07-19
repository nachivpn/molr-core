/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.commons.mole;

import java.lang.reflect.Method;
import java.util.List;

import cern.molr.commons.mission.Mission;
import cern.molr.exception.IncompatibleMissionException;
import cern.molr.exception.MissionExecutionException;

/**
 * A {@link Mole} executes a given mission. Only a {@link Mole} knows how to run a mission.
 * 
 * @author nachivpn 
 * @param <I>
 * @param <O>
 */
public interface Mole<I, O> {

    List<Method> discover(Class<?> classType) throws IncompatibleMissionException;
    
    O run(Mission mission, I args) throws MissionExecutionException;
    
}
