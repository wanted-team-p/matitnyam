package com.wanted.matitnyam.domain.xmlparser;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.InputStream;
import org.springframework.stereotype.Component;

@Component
public class RestaurantsDataParser {

    public RestaurantsData parse(Class<?> restaurantsDataClass, InputStream inputStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(restaurantsDataClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (RestaurantsData) unmarshaller.unmarshal(inputStream);
    }

}
