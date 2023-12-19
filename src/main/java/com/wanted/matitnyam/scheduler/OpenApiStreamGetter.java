package com.wanted.matitnyam.scheduler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Component;

@Component
public class OpenApiStreamGetter {

    public InputStream get(String openApiUrlAndKeyInString) throws IOException {
        URL url = new URL(openApiUrlAndKeyInString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        return urlConnection.getInputStream();
    }

}
