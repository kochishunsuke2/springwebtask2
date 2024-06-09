package com.example.springwebtask.entity;

public record Detail(Integer id,
                     String  product_id,
                     String  name,
                     Integer price,
                     Integer category_id,
                     String  description) {
}
