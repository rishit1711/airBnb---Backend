package com.example.Project_Rishit.airBnbApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class HotelReportDto {
    private Long bookingCount;
    private BigDecimal totalRevenue;
    private BigDecimal avgRevenue;

}
