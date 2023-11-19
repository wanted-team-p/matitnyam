package com.wandted.matitnyam.restaurant.dto;

import java.util.List;
import lombok.Data;

@Data
public class ApiResponse {
    public Head head;
    public List<Row> row;
}
