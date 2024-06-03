package com.example.springwebtask.entity;

public record Menu(
        String  product_id,
        String  name,
        Integer price,
        String category_name,
        String  description
) {
}
