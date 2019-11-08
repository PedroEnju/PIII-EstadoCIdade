package br.com.pedroenju.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Pedro Enju
 */
public class NoIdeaController implements Initializable {

    @FXML
    private Button btnPesquisarEstado;
    @FXML
    private Button btnPesquisarCidade;
    @FXML
    private Button btnPesquisarCliente;
    @FXML
    private StackPane stackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Button getBtnPesquisarEstado() {
        return btnPesquisarEstado;
    }

    public void setBtnPesquisarEstado(Button btnPesquisarEstado) {
        this.btnPesquisarEstado = btnPesquisarEstado;
    }

    public Button getBtnPesquisarCidade() {
        return btnPesquisarCidade;
    }

    public void setBtnPesquisarCidade(Button btnPesquisarCidade) {
        this.btnPesquisarCidade = btnPesquisarCidade;
    }

    public Button getBtnPesquisarCliente() {
        return btnPesquisarCliente;
    }

    public void setBtnPesquisarCliente(Button btnPesquisarCliente) {
        this.btnPesquisarCliente = btnPesquisarCliente;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }
    
    
}
