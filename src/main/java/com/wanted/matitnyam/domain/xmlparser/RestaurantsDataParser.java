package com.wanted.matitnyam.domain.xmlparser;

import com.wanted.matitnyam.domain.RestaurantsData;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.stereotype.Component;

@Component
public class RestaurantsDataParser {

    public RestaurantsData parse(Class<?> restaurantsDataClass, String filePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(restaurantsDataClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (RestaurantsData) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException | IOException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

}
