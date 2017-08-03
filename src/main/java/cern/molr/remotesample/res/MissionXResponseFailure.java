/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.res;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import cern.molr.type.trye.Failure;

@JsonDeserialize(as = MissionXResponseFailure.class)
public class MissionXResponseFailure<X> extends Failure<X> implements MissionXResponse<X>{

    public MissionXResponseFailure() {
        super(null);
    }
    
    /**
     * @param l
     */
    public MissionXResponseFailure(Exception l) {
        super(l);
    }

    @Override
    public Exception getException() {
        return this.l;
    }

    @Override
    public X getResult() {
        return null;
    }

    @Override
    public void setException(Exception e) {
        this.l = e;
    }

    @Override
    public void setResult(X i) {
        return;
    }

}
