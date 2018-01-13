package com.library.Core.json;

import com.library.Core.Operation;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by rbuga on 1/9/2018.
 */
public class JsonOperationImpl implements Operation {
    public void print(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        System.out.println(json);

    }
}
