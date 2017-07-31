/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.sample;

import java.util.function.Function;

import cern.molr.commons.mole.RunWithMole;

/* 
 * The programmer of a mission only needs to specify the mole type. 
 * Since the programmer need not create/interact with mole objects, 
 * the mole implementation is completely hidden from the programmer.
 */

@RunWithMole(IntegerFunctionMole.class)

/*
 * A simple Runnable instance (no molr specific code here)
 */
public class IntDoubler implements Function<Integer,Integer>{

    @Override
    public Integer apply(Integer v) {
        return v * 2;
    }


}
