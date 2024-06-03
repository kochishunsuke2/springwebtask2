package com.example.springwebtask.entity;

import jakarta.validation.constraints.*;

public record Entity(
        Integer id,
        @NotEmpty(message = "ログインIDは必須入力です")
        String login_id,
        @NotEmpty(message = "パスワードは必須入力です")
        String password,
        String name,
        Integer role
//        Integer id,
////        @NotBlank(message = "{name.required}")
////        @Size(min = 1, max = 50, message = "{name.size}")
//        Integer product_id,
//
//        Integer category_id,
//        String name,
////        @NotNull(message = "{price.required}")
////        @Min(value = 0, message = "{price.min}")
////        @Max(value = Integer.MAX_VALUE, message = "{price.max}")
//        Integer price,
//
//        String  image_path,
//
//        String  description

) {
}
