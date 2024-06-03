package com.example.springwebtask.service;

import com.example.springwebtask.entity.Category;
import com.example.springwebtask.entity.Entity;
import com.example.springwebtask.entity.Menu;

import java.util.List;

public interface pgService {
    //ログイン画面
    Entity findByLogin(String login_id, String password);
    //メニュー画面
    List<Menu> findAll();
//    List<Category> findAll2();
    List<Menu> search(String name);

}
