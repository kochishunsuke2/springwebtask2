package com.example.springwebtask.service;

import com.example.springwebtask.dao.Dao;
import com.example.springwebtask.entity.Category;
import com.example.springwebtask.entity.Entity;
import com.example.springwebtask.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements pgService{

    @Autowired
    private Dao Dao;

    //ログイン画面
@Override
public Entity findByLogin(String login_id, String password) {
    var product = Dao.findByLogin(login_id,password);
    return product;
}
//メニュー画面
@Override
public List<Menu> findAll() {
    return Dao.findAll();
}
//@Override
//public List<Category> findAll2() {
//        return Dao.findAll2();
//    }

    @Override
    public List<Menu> search(String name) {
        return Dao.search(name);
    }
}
