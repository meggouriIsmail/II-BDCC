package com.meggouri.tp_javafx.presentation.controllers;

import com.meggouri.tp_javafx.dao.DaoCategoryImp;
import com.meggouri.tp_javafx.dao.DaoProductImp;
import com.meggouri.tp_javafx.dao.entities.Category;
import com.meggouri.tp_javafx.dao.entities.Product;
import com.meggouri.tp_javafx.services.CategoryService;
import com.meggouri.tp_javafx.services.CategoryServiceImp;
import com.meggouri.tp_javafx.services.ProductService;
import com.meggouri.tp_javafx.services.ProductServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import javax.xml.crypto.Data;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductController implements Initializable {

    public BorderPane borderPane;
    @FXML
    private TableView<Product> tableViewProducts;
    @FXML
    private ComboBox<Category> categoryCombo;
    @FXML
    private TableColumn<Product, String> descCol;
    @FXML
    private TextField descriptionTxt;
    @FXML
    private TableColumn<Product, Integer> idCol;
    @FXML
    private TableColumn<Product, String> nameCol;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private TableColumn<Product, Double> priceCol;
    @FXML
    private TableColumn<Product, Integer> qteCol;
    @FXML
    private TextField qteTxt;
    @FXML
    private TableColumn<Data, Void> operationCol;
    @FXML
    private TextField searchTxt;
    private ProductService productService;
    private CategoryService categoryService;
    ObservableList<Product> productObservableList;
    ObservableList<Category> categoryObservableList;
    private int productId;
    private  boolean toUpdate = false;
    public ProductController() {

    }
    private void loadCategory(){
        categoryObservableList = FXCollections.observableArrayList(
                categoryService.getALl()
        );

        categoryCombo.setItems(categoryObservableList);
    }
    private void loadProduct(){
        loadCategory();

        productObservableList = FXCollections.observableArrayList(
                productService.getALl()
        );
        idCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        qteCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("qte"));
        descCol.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));


        tableViewProducts.setItems(productObservableList);

        //add cell of button edit
        Callback<TableColumn<Data, Void>, TableCell<Data, Void>> cellFoctory = (TableColumn<Data, Void> param) -> {
            // make cell containing buttons
            final TableCell<Data, Void> cell = new TableCell<Data, Void>() {

                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

                        Label deleteIcon = new Label();
                        deleteIcon.setText("  ❌");
                        deleteIcon.setTextFill(Color.color(1, 0, 0));
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                Product product = tableViewProducts.getSelectionModel().getSelectedItem();
                                productService.delete(product);
                                productObservableList.remove(product);

                            } catch (Exception ex) {
                                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        HBox managebtn = new HBox(deleteIcon);

                        managebtn.setStyle("-fx-alignment:center"); // ????
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };
            return cell;
        };
        operationCol.setCellFactory(cellFoctory);
        tableViewProducts.setItems(productObservableList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productService = new ProductServiceImp(new DaoProductImp());
        categoryService = new CategoryServiceImp(new DaoCategoryImp());

        try{
            loadProduct();
            loadCategory();
            filter();
            tableViewProducts.getSelectionModel().selectedItemProperty().addListener((observable -> {
                toUpdate = true;
                Product p = tableViewProducts.getSelectionModel().getSelectedItem();
                Category categoryIndex = categoryCombo.getSelectionModel().getSelectedItem();

                if (p != null) {
                    productId = p.getId();
                    nameTxt.setText(p.getName());
                    descriptionTxt.setText(p.getDescription());
                    priceTxt.setText(String.valueOf(p.getPrice()));
                    qteTxt.setText(String.valueOf(p.getQte()));
                    categoryCombo.getSelectionModel().select(categoryIndex);
                }
            }));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void addNewProduct(){

        if(nameTxt.getText().isEmpty() || descriptionTxt.getText().isEmpty() || priceTxt.getText().isEmpty()|| qteTxt.getText().isEmpty()){
            alertErr();
            return;
        }
        Product p = new Product();

        p.setName(nameTxt.getText());
        p.setDescription(descriptionTxt.getText());
        p.setPrice(Double.parseDouble(priceTxt.getText()));
        p.setQte(Integer.parseInt(qteTxt.getText()));
        p.setCategory(categoryCombo.getSelectionModel().getSelectedItem());

        try{
            if(!toUpdate) {
                productService.add(p);
            } else {
                p.setId(productId);
                productService.update(p);
            }
            clean();
            loadProduct();
            alertSucc();
        }catch(Exception e){
            alertErr();
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    @FXML
    private void clean() {
        toUpdate = false;
        nameTxt.setText(null);
        descriptionTxt.setText(null);
        priceTxt.setText(null);
        qteTxt.setText(null);
    }
    @FXML
    private void search() {
        if (searchTxt.getText().isEmpty()) return;
        productObservableList = FXCollections.observableArrayList(
                productService.getByQuery(searchTxt.getText())
        );
    }
    private void alertErr(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Please Fill All DATA");
        alert.showAndWait();
    }

    private void alertSucc(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        if(!toUpdate)
            alert.setContentText("Product added successfully");
        else
            alert.setContentText("Product updated successfully");
        alert.showAndWait();
    }
    private  void filter(){
        FilteredList<Product> filteredData = new FilteredList<>(productObservableList, b ->true);
        searchTxt.textProperty().addListener(((observableValue, oldVal, newVal) -> {
            filteredData.setPredicate(product -> {
                if(newVal == null || newVal.isEmpty()){
                    return true;
                }
                String keyWord = searchTxt.getText();
                if(product.getName().toLowerCase().contains(keyWord)) return true;
                else return product.getDescription().toLowerCase().contains(keyWord);
            });
        }));
        SortedList<Product> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(tableViewProducts.comparatorProperty());

        tableViewProducts.setItems(sortedList);
    }
}
