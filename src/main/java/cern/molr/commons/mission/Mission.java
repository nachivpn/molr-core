/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.commons.mission;

import java.io.Serializable;

public interface Mission extends Serializable{

    String getMoleClassName();

    String getMissionDefnClassName();

    Object[] getArgs();

}