package com.meggouri.tp_javafx.dao;


import com.meggouri.tp_javafx.dao.entities.Category;
import com.meggouri.tp_javafx.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoProductImp implements DaoProduct{

    @Override
    public List<Product> findALl() {
        List<Product> list = new ArrayList<>();
        Connection connection;
        try{
            connection = SingletonConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from PRODUCTS");
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                Product p  = new Product();
                p.setId(res.getInt("ID"));
                p.setName(res.getString("NAME"));
                p.setDescription(res.getString("DESCRIPTION"));
                p.setPrice(res.getDouble("PRICE"));
                p.setQte(res.getInt("QTE"));
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM CATEGORIES WHERE ID  = ?");
                ps.setInt(1, res.getInt("ID"));
                ResultSet res2 = ps.executeQuery();
                if(res2.next()){
                    Category c = new Category();
                    c.setId(res2.getInt("ID"));
                    c.setName(res2.getString("NAME"));
                }

                list.add(p);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product findById(int id) {
        Connection connection = SingletonConnectionDB.getConnection();
        try{

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PRODUIT WHERE ID  = ?");
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();

            Product product = new Product();

            product.setId(res.getInt("ID"));
            product.setName(res.getString("NAME"));
            product.setDescription(res.getString("DESCRIPTION"));
            product.setPrice(res.getDouble("PRICE"));
            product.setQte(res.getInt("QTE"));
            return product;

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Product save(Product prod) {
        try{
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCTS(NAME, DESCRIPTION, PRICE, QTE, CATID) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1,prod.getName());
            preparedStatement.setString(2,prod.getDescription());
            preparedStatement.setDouble(3,prod.getPrice());
            preparedStatement.setInt(4,prod.getQte());
            preparedStatement.setInt(5,prod.getCategory().getId());
            preparedStatement.executeUpdate();
            return prod;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Product prod) {
        try{
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUCTS WHERE ID = ?");
            preparedStatement.setInt(1,prod.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product update(Product prod) {
        try{
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Update PRODUCTS SET NAME = ?, DESCRIPTION = ?, PRICE = ?, QTE = ?, CATID=? WHERE ID=?");
            preparedStatement.setString(1,prod.getName());
            preparedStatement.setString(2,prod.getDescription());
            preparedStatement.setDouble(3,prod.getPrice());
            preparedStatement.setInt(4,prod.getQte());
            preparedStatement.setInt(5,prod.getCategory().getId());
            preparedStatement.setInt(6,prod.getId());
            preparedStatement.executeUpdate();

            return prod;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getProductsByQuery(String query) {
        List<Product> list = new ArrayList<>();
        Connection connection;
        try{
            connection = SingletonConnectionDB.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from PRODUCTS WHERE NAME like ? or DESCRIPTION like ?");
            statement.setString(1, "%"+query+"%");
            statement.setString(2, "%"+query+"%");
            ResultSet res = statement.executeQuery();
            while (res.next()){
                Product p  = new Product();
                p.setId(res.getInt("ID"));
                p.setName(res.getString("NAME"));
                p.setDescription(res.getString("DESCRIPTION"));
                p.setPrice(res.getDouble("PRICE"));
                p.setQte(res.getInt("QTE"));
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM CATEGORIES WHERE ID  = ?");
                ps.setInt(1, res.getInt("ID"));
                ResultSet res2 = ps.executeQuery();
                if(res2.next()){
                    Category c = new Category();
                    c.setId(res2.getInt("ID"));
                    c.setName(res2.getString("NAME"));
                }

                list.add(p);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
