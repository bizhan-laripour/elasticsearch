package org.example.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.util.Map;

public class JsonUtil {

/**
you can convert this type of json to standard json
*/
//  String json = "{shenasehParvandeh:p, isHadeaghalAsnad: , sanadList: , pardakhtList: , tarikhGoshayesh:1401-11-12, tarikhSarresid: , samtakSwiftKargozarList: , isBedounPardakhtOrSanad: , bankShobehCode:548687684, mablaghArzi:10000, abzarPardakhtTypeCode:5454545, shomarehAbzar:1450065978, shomarehSabtSefaresh:15024071, bedounTaahod: ,isHavalehGheyreBanki: , nahveyePardakhtList: }";

    public static Map jsonConverter(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder builder = new StringBuilder();
        String[] split = json.split(",");
        for(String s : split){
            String[] keyValue = s.split(":");
            if(keyValue.length == 2){
                if(!keyValue[1].trim().equals("}")) {
                    builder.append(keyValue[0].trim()).append(":").append("\"").append(keyValue[1].trim()).append("\"").append(",");
                }else{
                    builder.append(keyValue[0].trim()).append(":").append("\"\"").append(keyValue[1].trim());
                }
            } else if (keyValue.length == 1) {
                builder.append(keyValue[0].trim()).append(":").append("\"\"").append(",");
            }
        }
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new StdDeserializer<String>(String.class) {
            @Override
            public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                String result = StringDeserializer.instance.deserialize(p, ctxt);
                if (StringUtils.isEmpty(result)) {
                    return null;
                }
                return result;
            }
        });
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.registerModule(module);
        return mapper.readValue(builder.toString(), Map.class);

    }
}

