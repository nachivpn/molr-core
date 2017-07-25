/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.supervisor;

import java.time.ZonedDateTime;

import cern.molr.commons.mission.Mission;
import cern.molr.inspector.controller.StatefulJdiController;

/**
 * A generic {@link Session}, encapsulates the information of a currently running {@link Mission}
 *
 * @author tiagomr
 */
public interface Session {

    /**
     * @return the {@link Mission} being executed
     */
    Mission getMission();

    /**
     * @return the {@link StatefulJdiController} used to control the execution flow for this specific execution
     */
    StatefulJdiController getController();

    /**
     * @return a {@link ZonedDateTime} to timestamp the creation of the {@link Session}
     */
    ZonedDateTime getTimeStamp();
}
