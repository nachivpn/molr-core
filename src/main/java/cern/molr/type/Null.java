/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.type;

public class Null {

    @Override
    public boolean equals(Object o) {
        return null == o || o instanceof Null;
    }
    
}
