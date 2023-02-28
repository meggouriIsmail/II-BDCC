package com.meggouri.tp_javafx.presentation.controllers;

import com.meggouri.tp_javafx.dao.DaoCategoryImp;
import com.meggouri.tp_javafx.dao.entities.Category;
import com.meggouri.tp_javafx.services.CategoryService;
import com.meggouri.tp_javafx.services.CategoryServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    public TextField category_txt;
    public TableView<Category> cat_tableView;
    public TableColumn<Category, Integer> id_cell;
    public TableColumn<Category, String> Name_cell;

    ObservableList<Category> observableList;

    CategoryService categoryService;

    public CategoryController() {
        categoryService = new CategoryServiceImp(new DaoCategoryImp());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCategory();
        id_cell.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
        Name_cell.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
    }
    private void loadCategory(){
        observableList = FXCollections.observableArrayList(
                categoryService.getALl()
        );
        cat_tableView.setItems(observableList);
    }
    @FXML
    private void addCat(){
        try{
            Category c = new Category();
            c.setName(category_txt.getText());
            categoryService.add(c);
            category_txt.clear();
            loadCategory();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


