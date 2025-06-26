package com.cpb.Model.DTO.consume;

import com.tienda_back.model.dto.generic.LongIntDto;

import java.util.List;

public record ConsumeJsonCart(Long userId,List<LongIntDto> products){
}
