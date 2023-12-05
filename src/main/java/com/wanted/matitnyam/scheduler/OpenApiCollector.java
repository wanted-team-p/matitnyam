package com.wanted.matitnyam.scheduler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenApiCollector {

    @Value("${open-api.key-url}")
    private String keyUrl;

    public void collect(String openApiUrlInString, String fileUrlInString) {
        try {
            URL url = new URL(openApiUrlInString + keyUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

            URL xmlFileUrl = getClass().getClassLoader().getResource(fileUrlInString);
            File xmlFile = new File(xmlFileUrl.toURI());
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(xmlFile));
            while (bufferedReader.ready()) {
                String readLine = bufferedReader.readLine();
                bufferedWriter.write(readLine);
                bufferedWriter.write('\n');
            }
            bufferedWriter.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
