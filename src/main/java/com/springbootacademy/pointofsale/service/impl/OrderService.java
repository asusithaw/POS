package com.springbootacademy.pointofsale.service.impl;

import com.springbootacademy.pointofsale.dto.paginated.PaginatedResponseOrderDetailsDTO;
import com.springbootacademy.pointofsale.dto.request.RequestOrderSaveDTO;

public interface OrderService {

    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    PaginatedResponseOrderDetailsDTO getAllOrderDetails(boolean status, int page, int size);
}
