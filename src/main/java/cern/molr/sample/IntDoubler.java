/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import java.util.function.Function;

import cern.molr.commons.mole.RunWithMole;

@RunWithMole(IntegerFunctionMole.class)
public class IntDoubler implements Function<Integer,Integer>{

    @Override
    public Integer apply(Integer v) {
        return v * 2;
    }


}
