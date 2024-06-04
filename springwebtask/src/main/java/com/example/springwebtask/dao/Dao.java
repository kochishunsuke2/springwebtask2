package com.example.springwebtask.dao;

import com.example.springwebtask.entity.Entity;
import com.example.springwebtask.entity.Menu;
import com.example.springwebtask.entity.NewName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.DataClassRowMapper;

import java.util.List;

@Repository
public class Dao implements pgDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //ログイン画面
    @Override
    public Entity findByLogin(String login_id, String password) {
        var param = new MapSqlParameterSource();
        param.addValue("login_id", login_id);
        param.addValue("password", password);
        var list = jdbcTemplate.query("SELECT * FROM users WHERE login_id = :login_id AND password = :password", param, new DataClassRowMapper<>(Entity.class));
        return list.isEmpty() ? null : list.get(0);

    }

    //メニュー画面
    @Override
    public List<Menu> findAll() {
        return jdbcTemplate.query("SELECT p.product_id, p.name, p.price, c.category_name, p.description FROM products p JOIN categories c  ON p.category_id = c.id ORDER BY p.id",
                new DataClassRowMapper<>(Menu.class));
    }

//    @Override
//    public List<Category> findAll2() {
//        return jdbcTemplate.query("SELECT * FROM categories ORDER BY id;",
//                new DataClassRowMapper<>(Category.class));
//    }

    @Override
    public List<Menu> search(String name) {
        var param2 = new MapSqlParameterSource();
        param2.addValue("name", name);
        return jdbcTemplate.query("SELECT p.product_id, p.name, p.price, c.category_name, p.description FROM products p JOIN categories c  ON p.category_id = c.id  WHERE  p.name LIKE '%' || :name || '%' ORDER BY p.id",
                param2, new DataClassRowMapper<>(Menu.class));
    }

    @Override
    public int insert(NewName stationery) {
        var param = new MapSqlParameterSource();
        param.addValue("code", stationery.product_code());
        param.addValue("name", stationery.name());
        param.addValue("price", stationery.price());
        param.addValue("id", stationery.category_id());
        param.addValue("description", stationery.description());
        return jdbcTemplate.update("INSERT INTO products (product_code, name, price, category_id, description) VALUES (:code, :name, :price, :id, :description)", param);
    }
}
