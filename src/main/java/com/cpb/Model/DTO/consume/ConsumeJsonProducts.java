package com.cpb.Model.DTO.consume;

import com.tienda_back.model.dto.generic.LongIntDto;

import java.util.List;

public record ConsumeJsonProducts(List<LongIntDto> products) {
}
