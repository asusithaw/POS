package com.springbootacademy.pointofsale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerUpdateByDTO {
    private String customerName;
    private double customerSalary;
    private String nic;
}
