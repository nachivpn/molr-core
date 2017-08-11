/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.rcontroller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cern.molr.exception.MissionExecutionException;
import cern.molr.remotesample.req.MissionCancelRequest;
import cern.molr.remotesample.req.MissionExecutionRequest;
import cern.molr.remotesample.req.MissionResultRequest;
import cern.molr.remotesample.res.MissionCancelResponse;
import cern.molr.remotesample.res.MissionCancelResponseFailure;
import cern.molr.remotesample.res.MissionCancelResponseSuccess;
import cern.molr.remotesample.res.MissionExecutionResponse;
import cern.molr.remotesample.res.MissionExecutionResponseFailure;
import cern.molr.remotesample.res.MissionExecutionResponseSuccess;
import cern.molr.remotesample.res.MissionXResponse;
import cern.molr.remotesample.res.MissionXResponseFailure;
import cern.molr.remotesample.res.MissionXResponseSuccess;
import cern.molr.remotesample.res.bean.MissionExecutionResponseBean;
import cern.molr.remotesample.rservice.ServerRestExecutionService;
import cern.molr.type.Ack;

@RestController
public class ServerRestController {

    private final ServerRestExecutionService meGateway;

    @Autowired
    public ServerRestController(ServerRestExecutionService meGateway) {
        this.meGateway = meGateway;
    }

    @RequestMapping(path = "/mission", method = RequestMethod.POST)
    public <I> MissionExecutionResponse newMission(@RequestBody MissionExecutionRequest<I> request) {
        try {
            String mEId = meGateway.runMission(request.getMissionDefnClassName(), request.getArgs());
            return new MissionExecutionResponseSuccess(new MissionExecutionResponseBean(mEId));
        } catch (Exception e) {
            return new MissionExecutionResponseFailure(e);
        }
        
    }

    @RequestMapping(path = "/result", method = RequestMethod.POST)
    public CompletableFuture<MissionXResponse<Object>> result(@RequestBody MissionResultRequest request) {
        return CompletableFuture.supplyAsync(() ->{
            try {
                CompletableFuture<?> resultFuture = meGateway.getResult(request.getMissionExecutionId());
                return new MissionXResponseSuccess<>(resultFuture.get());
            } catch (Exception e) {
                return new MissionXResponseFailure<>(new MissionExecutionException(e));
            }
        });
    }
    
    @RequestMapping(path = "/cancel", method = RequestMethod.POST)
    public CompletableFuture<MissionCancelResponse> result(@RequestBody MissionCancelRequest request) {
        return CompletableFuture.supplyAsync(() ->{
            try {
                CompletableFuture<Ack> resultFuture = meGateway.cancel(request.getMissionExecutionId());
                return new MissionCancelResponseSuccess(resultFuture.get());
            } catch (Exception e) {
                return new MissionCancelResponseFailure(e);
            }
        });
    }

}
