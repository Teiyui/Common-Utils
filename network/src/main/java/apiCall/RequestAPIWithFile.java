package apiCall;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.io.File;

/**
 * @author: ywzheng
 * @Description: TODO
 * @date: 2021/9/29 11:10 AM
 */
public class RequestAPIWithFile {

    public HttpResponse<String> requestAndGetResponse() {
        HttpResponse<String> response = null;
        try {
            Unirest.setTimeouts(0, 0);
            response = Unirest.post("http://10.224.56.35:8084/vqt")
                    .field("degfile", new File("/Users/youwzhen/Documents/Media Quality E2E Test/audios/f1779ats-chu-msedgeTomsedge-26_09_2021_09_34_46-count_drop_50_1.pcm"))
                    .field("reffile", new File("/Users/youwzhen/Documents/Media Quality E2E Test/audios/f1779ats-chu-msedgeTomsedge-26_09_2021_09_34_46-count_drop_50_1.pcm"))
                    .field("samplerate", "48000")
                    .asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static void main(String[] args) {
        RequestAPIWithFile requestAPIWithFile = new RequestAPIWithFile();
        HttpResponse<String> response = requestAPIWithFile.requestAndGetResponse();
    }
}
