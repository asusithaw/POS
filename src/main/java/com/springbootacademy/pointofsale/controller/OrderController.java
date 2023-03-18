package com.springbootacademy.pointofsale.controller;

import com.springbootacademy.pointofsale.dto.paginated.PaginatedResponseOrderDetailsDTO;
import com.springbootacademy.pointofsale.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.pointofsale.service.impl.OrderService;
import com.springbootacademy.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveOrder(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {
        String id = orderService.addOrder(requestOrderSaveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, id + "item Successfully Saved", id),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            params = {"stateType", "page", "size"},
            path = {"get-order-details"}
    )
    public ResponseEntity<StandardResponse> getAllOrderDeatails(
            @RequestParam(value = "stateType") String stateType,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ) {
        PaginatedResponseOrderDetailsDTO paginatedResponseOrderDetails = null;
        if (stateType.equalsIgnoreCase("active") | stateType.equalsIgnoreCase("inactive")) {
            boolean status = stateType.equalsIgnoreCase("active") ? true : false;
            paginatedResponseOrderDetails = orderService.getAllOrderDetails(status,page,size);
        } else {

        }

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "item Successfully Saved", paginatedResponseOrderDetails),
                HttpStatus.CREATED
        );
    }
}
