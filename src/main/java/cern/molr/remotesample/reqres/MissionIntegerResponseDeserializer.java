/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.molr.remotesample.reqres;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MissionIntegerResponseDeserializer extends JsonDeserializer<MissionIntegerResponse>{

    @Override
    public MissionIntegerResponse deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = (ObjectNode) mapper.readTree(jp);
        Class<? extends MissionIntegerResponse> instanceClass = null;
        if(root.get("exception").isNull()) {
            instanceClass = MissionIntegerResponseSuccess.class;
        } else { 
            instanceClass = MissionIntegerResponseFailure.class;
        }
        return mapper.readValue(root.toString(), instanceClass);
    }
    
    
}
