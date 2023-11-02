package com.wandted.matitnyam.service.impl;

import com.wandted.matitnyam.domain.entity.Region;
import com.wandted.matitnyam.domain.vo.FileVo.Create;
import com.wandted.matitnyam.service.FileService;
import com.wandted.matitnyam.service.RegionService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 민경수
 * @description file service impl
 * @since 2023.11.02
 **********************************************************************************************************************/

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final RegionService service;

    @Override
    public void set(Create create) {

        MultipartFile file = create.getFile();

        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일을 업로드 해주세요.");
        }

        saveLocationsFromCsv(file);
    }

    public void saveLocationsFromCsv(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            // Skip the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println("data[0] = " + data[0]);
                System.out.println("data[1] = " + data[1]);
                System.out.println("data[2] = " + data[2]);
                System.out.println("data[3] = " + data[3]);

                Region build = Region.builder().city(data[0]).district(data[1]).longitude(Double.parseDouble(data[2])).latitude(Double.parseDouble(data[3])).build();
                service.set(build);
            }

        } catch (Exception ex) {
            throw new IllegalArgumentException("Failed to parse CSV file: " + ex.getMessage());
        }
    }

}