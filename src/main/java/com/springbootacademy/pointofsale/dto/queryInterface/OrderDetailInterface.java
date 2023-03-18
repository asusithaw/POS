package com.springbootacademy.pointofsale.dto.queryInterface;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailInterface {
    String getCustomerName();
    String getCustomerAddress();
    ArrayList getContactNumber();
    Date getDate();
    Double getTotal();
}
