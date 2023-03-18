package com.springbootacademy.pointofsale.controller;

import com.springbootacademy.pointofsale.dto.ItemDTO;
import com.springbootacademy.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pointofsale.service.ItemService;
import com.springbootacademy.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String Id = itemService.addItem(itemSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, Id + "Item Successfully saved", Id),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = "get-all-items")
    public ResponseEntity<StandardResponse> getAllItems() {
        List<ItemDTO> allItems = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", allItems),
                HttpStatus.OK
        );
    }

    //ehema nam -- ?
    //ehema nattam -- :
    @GetMapping(path = {"get-all-items-by-state"},
            params = {"state"}
    )
    public ResponseEntity<StandardResponse> getAllItemsByState(@RequestParam(value = "state") String state) {
        if (state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")) {
//            boolean status = false;
//            if(state.equalsIgnoreCase("active")){
//                status = true;
//            }
            boolean status = state.equalsIgnoreCase("active") ? true : false;
            List<ItemDTO> allItemsByState = itemService.getAllItemsByState(status);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Success", allItemsByState),
                    HttpStatus.OK
            );
        } else {
            List<ItemDTO> allItemsByState = itemService.getAllItems();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Success", allItemsByState),
                    HttpStatus.OK
            );
        }
    }

    @GetMapping(
            path = "count-all-item"
    )
    public ResponseEntity<StandardResponse> getAllItemCounts() {
        int itemCount = itemService.getItemCount();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Sucees",itemCount),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = "get-all-items-paginated",
            params = {"page", "size","activeState"}
    )
    public ResponseEntity<StandardResponse> getAllItemsPaginated(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size

    ){
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemsPaginated(page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Sucees",paginatedResponseItemDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = "get-all-active-items-paginated",
            params = {"page", "size","activeState"}
    )
    public ResponseEntity<StandardResponse> getAllActiveItemsPaginated(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size,
            @RequestParam(value = "activeState") boolean activeState
    ){
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllActiveItemsPaginated(page, size, activeState);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Sucees",paginatedResponseItemDTO),
                HttpStatus.OK
        );
    }

}
