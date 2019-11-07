/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedroenju.controller;

import br.com.pedroenju.contracts.FormControllerInterface;
import br.com.pedroenju.model.Estado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pedro Enju
 */
public class FormEstadoController implements Initializable, FormControllerInterface {

    @FXML
    private AnchorPane apEstado;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtUF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private Estado model;
    
    @Override
    public Parent getLayout() {
        return this.apEstado;
    }

    @Override
    public void setModel(Object model) {
        this.model = (Estado) model;
        txtNome.setText(this.model.getNomeEstado());
        txtUF.setText(this.model.getUf());
    }

    @Override
    public Object getModel() {
        this.model.setNomeEstado(txtNome.getText());
        this.model.setUf(txtUF.getText());
        return this.model;
    }

    @Override
    public void startForm() {
        this.setModel(new Estado());
    }
    
}
