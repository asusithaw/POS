package com.springbootacademy.pointofsale.util.mappers;

import com.springbootacademy.pointofsale.dto.ItemDTO;
import com.springbootacademy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pointofsale.entity.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-14T15:51:36+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public Item RequestDtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO) {
        if ( itemSaveRequestDTO == null ) {
            return null;
        }

        Item item = new Item();

        item.setItemName( itemSaveRequestDTO.getItemName() );
        item.setMeasuringUnit( itemSaveRequestDTO.getMeasuringUnit() );
        item.setBalanceQty( itemSaveRequestDTO.getBalanceQty() );
        item.setSupplierPrice( itemSaveRequestDTO.getSupplierPrice() );
        item.setSellingPrice( itemSaveRequestDTO.getSellingPrice() );

        return item;
    }

    @Override
    public List<ItemDTO> pageToList(Page<Item> page) {
        if ( page == null ) {
            return null;
        }

        List<ItemDTO> list = new ArrayList<ItemDTO>();
        for ( Item item : page ) {
            list.add( itemToItemDTO( item ) );
        }

        return list;
    }

    protected ItemDTO itemToItemDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setItemId( item.getItemId() );
        itemDTO.setItemName( item.getItemName() );
        itemDTO.setMeasuringUnit( item.getMeasuringUnit() );
        itemDTO.setBalanceQty( item.getBalanceQty() );
        itemDTO.setSupplierPrice( item.getSupplierPrice() );
        itemDTO.setSellingPrice( item.getSellingPrice() );
        itemDTO.setActiveState( item.isActiveState() );

        return itemDTO;
    }
}
