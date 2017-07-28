/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.supervisor;

import java.time.ZonedDateTime;

import cern.molr.client.StepResult;
import cern.molr.commons.mission.Mission;
import cern.molr.type.Either;

/**
 * A generic {@link StepSession}, encapsulates the information of a currently running {@link Mission}
 *
 * @author tiagomr
 * @author nachivpn
 */
public interface StepSession extends Session{

    JdiController getController();

    /**
     * @return a {@link ZonedDateTime} to timestamp the creation of the {@link StepSession}
     */
    ZonedDateTime getTimeStamp();
    
    <O> Either<StepResult,O> getResult();
    
}
