package util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author: ywzheng
 * @Description: TODO
 * @date: 2021/9/17 9:21 AM
 */
public class GSONDateDeserializer implements JsonDeserializer<Date> {

    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String date = jsonElement.getAsString();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            return format.parse(date);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
            return null;
        }
    }
}
