/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.commons.mission;

import java.io.Serializable;

public interface Mission<I,O> extends Serializable{

    String getMoleClassName();

    String getMissionDefnClassName();

    I getInputArgs();

}