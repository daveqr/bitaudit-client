package com.davedaniels.bitaudit;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.Throwables;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;


public class Main {
    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public void sendMessage(String urls, String paramsArg) {
        final HttpURLConnection connection;
        try {

            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
            GenericUrl url = new GenericUrl(urls);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("data", "thevalue");

            HttpContent content = new UrlEncodedContent(params);

            HttpRequest request = requestFactory.buildPostRequest(url, content);
            HttpResponse response = request.execute();
            System.out.println("Sent parameters: " + params + " - Received response: " + response.getStatusMessage());
            System.out.println("Response content: " + CharStreams.toString(new InputStreamReader(response.getContent())));
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8080/xxx";

        Main main = new Main();
        main.sendMessage("http://localhost:8080/xxx", "");
    }
}
