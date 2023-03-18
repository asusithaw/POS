package com.springbootacademy.pointofsale.dto.request;

import com.springbootacademy.pointofsale.entity.Item;
import com.springbootacademy.pointofsale.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSaveDTO {

    private String itemName;
    private double qty;
    private Double amount;
    private int items;

}
