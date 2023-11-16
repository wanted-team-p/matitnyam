package com.wandted.matitnyam.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wandted.matitnyam.domain.vo.FileVo.Create;
import com.wandted.matitnyam.domain.vo.RegionVo;
import com.wandted.matitnyam.service.FileService;
import com.wandted.matitnyam.service.RegionService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            List<RegionVo.Data> parse = new CsvToBeanBuilder<RegionVo.Data>(reader)
                    .withType(RegionVo.Data.class)
                    .build()
                    .parse();

            for (RegionVo.Data data : parse) {
                data.validateData();

                service.set(data.toEntity());
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("파일 처리중 오류가 발생했습니다.: " + ex.getMessage());
        }
    }

}