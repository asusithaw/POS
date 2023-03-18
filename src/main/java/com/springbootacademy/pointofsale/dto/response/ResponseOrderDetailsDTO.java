package com.springbootacademy.pointofsale.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrderDetailsDTO {
    private String customerName;
    private String customerAddress;
    private ArrayList contactNumber;
    private Date date;
    private Double total;
}
