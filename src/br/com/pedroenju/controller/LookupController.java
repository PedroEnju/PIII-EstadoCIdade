package br.com.pedroenju.controller;

import br.com.pedroenju.contracts.FormControllerInterface;
import br.com.pedroenju.contracts.LookupControllerInterface;
import br.com.pedroenju.contracts.TableModelInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lukas
 */
public class LookupController implements Initializable, LookupControllerInterface {

    @FXML
    private AnchorPane aoLookup;
    @FXML
    private Button btSelecionar;
    @FXML
    private Button btCancelar;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btPesquisar;
    @FXML
    private TableView<Object> tblDados;
    private TableModelInterface tm;
    private Object selecionado;
    private boolean closeRequested;

    public boolean isCloseRequested() {
        return closeRequested;
    }

    public LookupController() {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void selecionar(ActionEvent event) {
        this.selecionado = tblDados.getSelectionModel().getSelectedItem();
        this.closeRequested = true;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        this.selecionado = null;
        this.closeRequested = true;
    }

    @FXML
    private void pesquisarClicked(ActionEvent event) {
        this.tblDados.getItems().clear();
        this.tblDados.getItems().addAll(tm.search(txtPesquisa.getText()));
    }

    @Override
    public Parent getLayout() {
        return this.aoLookup;
    }

    @Override
    public void setModel(Object model) {
//
    }

    @Override
    public Object getModel() {
        return selecionado;
    }

    @Override
    public void startForm() {
        this.closeRequested = false;
        this.btSelecionar.setDisable(true);
        this.tblDados.getItems().clear();
        this.tblDados.getColumns().clear();
        this.tblDados.getColumns().addAll(tm.getCols());
        this.tblDados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.btSelecionar.setDisable(false);
            } else {
                this.btSelecionar.setDisable(true);
            }
        });
    }

    public void configurar(TableModelInterface tm, String titulo) {
        this.tm = tm;

    }

    @Override
    public void setLookUp(LookupControllerInterface lkp) {

    }

    @Override
    public BooleanProperty hasActiveLookup() {
        return null;
    }

}
