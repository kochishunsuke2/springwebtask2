package com.example.springwebtask.dao;

import com.example.springwebtask.entity.Entity;
import com.example.springwebtask.entity.Menu;
import com.example.springwebtask.entity.NewName;

import java.util.List;

public interface pgDao {
    //ログイン画面
    Entity findByLogin(String login_id,String password);
    //メニュー画面
    List<Menu> findAll();
//    List<Category> findAll2();
    List<Menu> search(String name);
    int insert(NewName stationery);
}
