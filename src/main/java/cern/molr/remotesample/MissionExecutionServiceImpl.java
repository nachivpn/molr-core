/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import cern.molr.client.MissionExecutionService;
import cern.molr.client.RunMissionController;
import cern.molr.client.StepMissionController;
import cern.molr.exception.UnsupportedMissionOpTypeException;
import cern.molr.remotesample.reqres.MissionExecutionRequest;
import cern.molr.remotesample.reqres.MissionExecutionResponse;
import cern.molr.remotesample.reqres.MissionIntegerResponse;
import cern.molr.remotesample.reqres.MissionResultRequest;
import cern.molr.remotesample.reqres.TryResponse;
import cern.molr.type.Ack;
import reactor.core.publisher.Mono;

/**
 * Implementation used by the operator
 * 
 * @author nachivpn
 */
public class MissionExecutionServiceImpl implements MissionExecutionService{

    WebClient client = WebClient.create("http://localhost:8080");

    @Override
    public <I, O> CompletableFuture<RunMissionController<O>> runToCompletion(String missionDefnClassName, I args, Class<I> cI, Class<O> cO) {
        MissionExecutionRequest<I> execRequest = new MissionExecutionRequest<>(missionDefnClassName, args);
        return CompletableFuture.<RunMissionController<O>>supplyAsync(() -> {
            final String missionExecutionId = client.post().uri("/mission")
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromPublisher(Mono.just(execRequest), MissionExecutionRequest.class)).exchange()
                    .flatMapMany(value -> value.bodyToMono(MissionExecutionResponse.class))
                    .doOnError(throwable ->  {throw new CompletionException(throwable);})
                    .map(value -> value.match(
                            (Exception e) -> {throw new CompletionException(e);}, 
                            (String meID) -> meID))
                    .blockFirst();
            return new RunMissionController<O>() {
                @SuppressWarnings("unchecked")
                @Override
                public CompletableFuture<O> getResult() {
                    return CompletableFuture.supplyAsync(() ->{
                        MissionResultRequest resultRequest = new MissionResultRequest(missionExecutionId);
                        return (O) client.post().uri("/result")
                                .accept(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(Mono.just(resultRequest), MissionResultRequest.class)).exchange()
                                .flatMapMany(value -> {
                                    try {
                                        return value.bodyToMono(getMissionResultResponseType(cO));
                                    } catch (UnsupportedMissionOpTypeException e) {
                                        throw new CompletionException(e);
                                    }})
                                .doOnError(throwable ->  {throw new CompletionException(throwable);})
                                .map(value -> value.getResult())
                                .blockFirst();
                    });
                }

                @Override
                public CompletableFuture<Ack> cancel() {
                    return null;
                }

            };
        });

    }

    @Override
    public <I, O> CompletableFuture<StepMissionController<O>> step(String missionDefnClassName, I args) {
        return null;
    }

    private Class<? extends TryResponse<?>> getMissionResultResponseType(Class<?> c) throws UnsupportedMissionOpTypeException {
        if(c.equals(Integer.class))
            return MissionIntegerResponse.class;
        else
            throw new UnsupportedMissionOpTypeException(c.getName() + "is not supported yet!");
    }


}
