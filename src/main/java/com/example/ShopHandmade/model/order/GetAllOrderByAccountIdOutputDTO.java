package com.example.ShopHandmade.model.order;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetAllOrderByAccountIdOutputDTO {
    private short id;
    private String status;
    private LocalDateTime orderDate;
}
