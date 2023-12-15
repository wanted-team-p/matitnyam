package com.wandted.matitnyam.domain.csvparser;

import com.wandted.matitnyam.domain.Region;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SggDataParser {

    @Value("${sgg-data.file-path}")
    private String sggFilePath;

    public List<Region> parse() throws IOException {
        BufferedReader bufferedReader = getBufferedReaderOfSggFile();

        String firstLine = bufferedReader.readLine();
        String[] columnInformation = firstLine.split(",");

        List<Region> regionList = new ArrayList<>();
        while (bufferedReader.ready()) {
            String readLine = bufferedReader.readLine();
            Region parsedRegion = parseLineAsRegion(readLine);
            regionList.add(parsedRegion);
        }
        return regionList;
    }

    private BufferedReader getBufferedReaderOfSggFile() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(sggFilePath);
        assert inputStream != null;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }

    private Region parseLineAsRegion(String readLine) {
        String[] splitLine = readLine.split(",");
        String dosi = splitLine[0];
        String sgg = splitLine[1];
        String lon = splitLine[2];
        String lat = splitLine[3];

        return Region.builder()
                .dosi(dosi)
                .sgg(sgg)
                .lon(Double.parseDouble(lon))
                .lat(Double.parseDouble(lat))
                .build();
    }

}
