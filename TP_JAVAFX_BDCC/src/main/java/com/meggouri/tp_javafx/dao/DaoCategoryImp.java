package com.meggouri.tp_javafx.dao;


import com.meggouri.tp_javafx.dao.entities.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoCategoryImp implements DaoCategory {
    @Override
    public List<Category> findALl() {
        List<Category> list = new ArrayList<>();
        Connection connection;
        try{
            connection = SingletonConnectionDB.getConnection();
            String query = "select * from CATEGORIES";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                Category c = new Category();
                c.setId(res.getInt("ID"));
                c.setName(res.getString("NAME"));
                list.add(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Category findById(int id) {
        Connection connection;
        try{
            connection = SingletonConnectionDB.getConnection();
            String query = "select * from CATEGORIES where ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet res = preparedStatement.executeQuery();
            Category category = new Category();
            category.setId(res.getInt("ID"));
            category.setName(res.getString("NAME"));
            return category;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category save(Category cat) {
        try{
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CATEGORIES(NAME) VALUES (?)");
            preparedStatement.setString(1,cat.getName());
            preparedStatement.executeUpdate();
            return cat;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Category cat) {
        try{
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM CATEGORIES WHERE ID = ?");
            preparedStatement.setInt(1,cat.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category update(Category cat) {
        try{
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Update CATEGORIES SET NAME = ? WHERE ID = ?");
            preparedStatement.setString(1,cat.getName());
            preparedStatement.setInt(2,cat.getId());
            preparedStatement.executeUpdate();
            return cat;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
