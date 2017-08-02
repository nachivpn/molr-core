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

import cern.molr.exception.UnknownMissionException;
import cern.molr.remotesample.reqres.MissionExecutionRequest;
import cern.molr.remotesample.reqres.MissionExecutionResponse;
import cern.molr.remotesample.reqres.MissionExecutionResponseFailure;
import cern.molr.remotesample.reqres.MissionExecutionResponseSuccess;
import cern.molr.remotesample.reqres.MissionResultRequest;
import cern.molr.remotesample.reqres.MissionXResponse;
import cern.molr.remotesample.reqres.MissionXResponseFailure;
import cern.molr.remotesample.reqres.MissionXResponseSuccess;
import cern.molr.remotesample.rservice.MissionExecutionGatewayService;

@RestController
public class ServerRestController {

    private final MissionExecutionGatewayService meGateway;

    @Autowired
    public ServerRestController(MissionExecutionGatewayService meGateway) {
        this.meGateway = meGateway;
    }

    @RequestMapping(path = "/mission", method = RequestMethod.POST)
    public <I> MissionExecutionResponse newMission(@RequestBody MissionExecutionRequest<I> request) {
        try {
            String mEId = meGateway.runMission(request.getMissionDefnClassName(), request.getArgs());
            return new MissionExecutionResponseSuccess(mEId);
        } catch (UnknownMissionException e) {
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
                return new MissionXResponseFailure<>(e);
            }
        });
    }

}
