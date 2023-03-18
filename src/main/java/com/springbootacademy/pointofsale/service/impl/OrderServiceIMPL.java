package com.springbootacademy.pointofsale.service.impl;

import com.springbootacademy.pointofsale.dto.paginated.PaginatedResponseOrderDetailsDTO;
import com.springbootacademy.pointofsale.dto.queryInterface.OrderDetailInterface;
import com.springbootacademy.pointofsale.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.pointofsale.dto.response.ResponseOrderDetailsDTO;
import com.springbootacademy.pointofsale.entity.Order;
import com.springbootacademy.pointofsale.entity.OrderDetails;
import com.springbootacademy.pointofsale.repo.CustomerRepo;
import com.springbootacademy.pointofsale.repo.ItemRepo;
import com.springbootacademy.pointofsale.repo.OrderDeatailsRepo;
import com.springbootacademy.pointofsale.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderDeatailsRepo orderDetailsRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order = new Order(
                customerRepo.getById(requestOrderSaveDTO.getCustomers()),
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal()
        );
        orderRepo.save(order);
        if (orderRepo.existsById(order.getOrderId())) {
            List<OrderDetails> orderDetails = modelMapper
                    .map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>() {
                    }.getType());

            for (int i = 0; i < orderDetails.size(); i++) {
                orderDetails.get(i).setOrd(order);
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));
            }

            if (orderDetails.size() > 0) {
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "Saved";
        }
        return null;
    }

    @Override
    public PaginatedResponseOrderDetailsDTO getAllOrderDetails(boolean status, int page, int size) {
        List<OrderDetailInterface> orderDetailInterfaces = orderRepo.getAllOrderDetails(status, PageRequest.of(page, size));
//        System.out.println(orderDetailInterfaces.get(0).getCustomerName());
        List<ResponseOrderDetailsDTO> list = new ArrayList<>();

        for (OrderDetailInterface o : orderDetailInterfaces) {
//            ResponseOrderDetailsDTO responseOrderDetailsDTO = new ResponseOrderDetailsDTO(
//                    o.getCustomerName(),
//                    o.getCustomerAddress(),
//                    o.getContactNumber(),
//                    o.getDate(),
//                    o.getTotal()
//            );
//            list.add(responseOrderDetailsDTO);
            list.add(new ResponseOrderDetailsDTO(
                            o.getCustomerName(),
                            o.getCustomerAddress(),
                            o.getContactNumber(),
                            o.getDate(),
                            o.getTotal()
                    )
            );
        }

        PaginatedResponseOrderDetailsDTO paginatedResponseOrderDetails = new PaginatedResponseOrderDetailsDTO(
                list,5
        );
        return paginatedResponseOrderDetails;
    }
}
