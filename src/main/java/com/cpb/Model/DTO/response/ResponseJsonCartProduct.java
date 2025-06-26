package com.cpb.Model.DTO.response;

public record ResponseJsonCartProduct(long productID,
                                      String name,
                                      String sku,
                                      double price,
                                      String brand,
                                      int quantity) {
}
