package com.example.springwebtask.service;

import com.example.springwebtask.dao.Dao;
import com.example.springwebtask.entity.Category;
import com.example.springwebtask.entity.Entity;
import com.example.springwebtask.entity.Menu;
import com.example.springwebtask.entity.NewName;
import com.example.springwebtask.exception.ProductNotFoundException;
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

    @Override
    public List<Category> findAll2() {
        return Dao.findAll2();
    }

    @Override
    public List<Menu> search(String name) {
        return Dao.search(name);
    }

    @Override
    public int insert(NewName stationery) {
        return Dao.insert(stationery);
    }

    @Override
    public Menu findById(int id) {
        var product = Dao.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("");
        }
        return product;
    }

    @Override
    public int delete(int id) {
        return Dao.delete(id);
    }

    @Override
    public int update(NewName change) {
        return Dao.update(change);
    }
    @Override
    public NewName findByProductId(String product_id) {
        var product = Dao.findByProductId(product_id);
        return product;
    }
}
