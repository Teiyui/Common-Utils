package apiCall;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.ResponseInfo;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import util.GSONDateDeserializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: ywzheng
 * @Description: TODO
 * @date: 2021/9/17 8:55 AM
 */
public class RequestAPI {

    public String requestAndGetResponse() {
        String responseContent = null;
        List<ResponseInfo> responseInfos = new ArrayList<ResponseInfo>();
        try {
            // Execute server command and get the response
            String profile =  FileUtils.readFileToString(
                    new File("src/main/resources/profile.json"), "UTF-8");
            int statusCode = 0;
            Response response = null;
            HttpResponse httpResponse = null;
            while (statusCode != 200) {
                response = Request.Post("http://127.0.0.1:8084/run")
                        .bodyString(profile, ContentType.APPLICATION_JSON).execute();
                httpResponse = response.returnResponse();
                statusCode = httpResponse.getStatusLine().getStatusCode();
            }

            // Read the content from InputStream of HttpResponse
            String line =null;
            StringBuilder entityStringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"),8*1024);
            while ((line=bufferedReader.readLine())!=null) {
                entityStringBuilder.append(line);
            }
            responseContent = entityStringBuilder.toString();

            // Convert JSON response to VQTInformation Object
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new GSONDateDeserializer());
            Gson gson = gsonBuilder.setPrettyPrinting().create();
            // The reason that why used TypToken here is we want to change the list format of JSON file to ArrayList.
            // The examples are in the followings:
            // [{"score": 0, "time": 0.0}, {"score": 0, "time": 0.0}]
            responseInfos = gson.fromJson(responseContent, new TypeToken<List<ResponseInfo>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseContent;
    }
}
