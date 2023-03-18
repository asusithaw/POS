package com.springbootacademy.pointofsale.dto.paginated;

import com.springbootacademy.pointofsale.dto.response.ResponseOrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseOrderDetailsDTO {
    private List<ResponseOrderDetailsDTO> list;
    private long dataCount;
}
