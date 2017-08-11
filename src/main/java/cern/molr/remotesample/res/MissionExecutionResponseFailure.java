/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.res;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import cern.molr.remotesample.res.bean.MissionExecutionResponseBean;
import cern.molr.type.trye.Failure;

@JsonDeserialize(as = MissionExecutionResponseFailure.class)
public class MissionExecutionResponseFailure extends Failure<MissionExecutionResponseBean> implements MissionExecutionResponse{

    /**
     */
    public MissionExecutionResponseFailure() {
        super(null);
    }
    /**
     * @param l
     */
    public MissionExecutionResponseFailure(Exception l) {
        super(l);
    }

    @Override
    public Exception getException() {
        return this.l;
    }
    @Override
    public void setException(Exception e) {
        this.l = e;
    }
    
    @Override
    public MissionExecutionResponseBean getResult() {
        return null;
    }
    @Override
    public void setResult(MissionExecutionResponseBean r) {
        return;
    }


}
