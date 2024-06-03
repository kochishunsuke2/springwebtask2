package com.example.springwebtask.form;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty(message = "ログインIDは必須入力です")
    private String login_id;

    @NotEmpty(message = "パスワードは必須入力です")
   // @Length(min = 8, max = 50, message ="8文字以上50文字以下")
    private String password;

}
