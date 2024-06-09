package com.example.springwebtask.service;

import com.example.springwebtask.entity.*;

import java.util.List;

public interface pgService {
    //ログイン画面
    Entity findByLogin(String login_id, String password);
    //メニュー画面
    List<Menu> findAll();
    List<Category> findAll2();
    List<Menu> search(String name);
    int insert(NewName stationery);
    Detail findById(int id);
    int delete (int id);
    int update (NewName change);
    NewName findByProductId(String product_id);
    Category findByCategory(int id);

}
