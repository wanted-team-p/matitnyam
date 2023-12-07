package com.wandted.matitnyam.domain.csvparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wandted.matitnyam.domain.Region;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SggDataParserTest {

    private static final ObjectWriter objectWriter = new ObjectMapper()
            .writer()
            .withDefaultPrettyPrinter();

    @Autowired
    private SggDataParser sggDataParser;

    @Test
    void parseTest() throws IOException {
        List<Region> regionList = sggDataParser.parse();
        for (Region region : regionList) {
            String regionAsJsonString = objectWriter.writeValueAsString(region);
            System.out.println(regionAsJsonString);;
        }
    }

}
