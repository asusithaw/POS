package com.springbootacademy.pointofsale.service.impl;

import com.springbootacademy.pointofsale.dto.ItemDTO;
import com.springbootacademy.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pointofsale.entity.Item;
import com.springbootacademy.pointofsale.repo.ItemRepo;
import com.springbootacademy.pointofsale.service.ItemService;
import com.springbootacademy.pointofsale.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addItem(ItemSaveRequestDTO itemSaveRequestDTO) {
//      Item item = itemMapper.RequestDtoToEntity(itemSaveRequestDTO);
        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
        item.setActiveState(true);
        if (!itemRepo.existsById(item.getItemId())) {
            return itemRepo.save(item).getItemName();
        } else {
            throw new RuntimeException("Already in database");
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> getItems = itemRepo.findAll();
        List<ItemDTO> itemDTOList = modelMapper.map(getItems, new TypeToken<List<ItemDTO>>() {
        }.getType());
        return itemDTOList;
    }

    @Override
    public List<ItemDTO> getAllItemsByState(boolean status) {
        List<Item> getItems = itemRepo.findAllByActiveStateEquals(status);
        List<ItemDTO> itemDTOList = modelMapper.map(getItems, new TypeToken<List<ItemDTO>>() {
        }.getType());
        return itemDTOList;
    }

    @Override
    public int getItemCount() {
        int count = itemRepo.countAllByActiveStateEquals(true);
        return count;
    }

    @Override
    public PaginatedResponseItemDTO getAllItemsPaginated(int page, int size) {
        Page<Item> getAllItemsByPaginated = itemRepo.findAll(PageRequest.of(page, size));
        return new PaginatedResponseItemDTO(
                itemMapper.pageToList(getAllItemsByPaginated),
                itemRepo.count()
        );
    }

    @Override
    public PaginatedResponseItemDTO getAllActiveItemsPaginated(int page, int size, boolean activeState) {
        Page<Item> getAllActiveItemsByPaginated = itemRepo.findAllByActiveStateEquals(activeState, PageRequest.of(page, size));
        return new PaginatedResponseItemDTO(
                itemMapper.pageToList(getAllActiveItemsByPaginated),
                itemRepo.countAllByActiveStateEquals(activeState)
        );
    }


}
