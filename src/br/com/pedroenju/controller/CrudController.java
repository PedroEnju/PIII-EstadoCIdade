package br.com.pedroenju.controller;

import br.com.pedroenju.contracts.CrudInterface;
import br.com.pedroenju.contracts.DaoInterface;
import br.com.pedroenju.contracts.FormControllerInterface;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Pedro Enju
 */
public class CrudController implements Initializable, CrudInterface {

    @FXML
    private AnchorPane apCrud;
    @FXML
    private Label lblTitulo;
    @FXML
    private Insets x1;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnRemover;
    @FXML
    private StackPane stackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private DaoInterface dao;
    private PesquisaController pc;
    private FormControllerInterface fc;

    @FXML
    private void btnNovoClick(ActionEvent event) {
        this.stackPane.getChildren().clear();
        this.stackPane.getChildren().add(fc.getLayout());
        this.fc.startForm();

        btnNovo.setDisable(true);
        btnEditar.setDisable(true);
        btnSalvar.setDisable(false);
        btnCancelar.setDisable(false);
        btnRemover.setDisable(true);
    }

    @FXML
    private void btnEditarClick(ActionEvent event) {
        Object item = this.pc.getTableViewPesquisar().getSelectionModel().getSelectedItem();
        btnNovoClick(event);
        fc.setModel(item);
    }

    @FXML
    private void btnSalvarClick(ActionEvent event) {
        dao.save(fc.getModel());
        Alert msg = new Alert(Alert.AlertType.INFORMATION, "Salvo com sucesso!");
        msg.showAndWait();
        start();
    }

    @FXML
    private void btnCancelarClick(ActionEvent event) {
        start();
    }

    @FXML
    private void btnRemoverClick(ActionEvent event) {
        Alert request = new Alert(Alert.AlertType.CONFIRMATION, "Deseja realmente remover?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> response = request.showAndWait();
        if (response.get().equals(ButtonType.YES)) {
            dao.delete(this.pc.getTableViewPesquisar().getSelectionModel().getSelectedItem());
            Alert msg = new Alert(Alert.AlertType.INFORMATION, "Removido com sucesso!");
            msg.showAndWait();
            start();
        }
    }

    @Override
    public void config(DaoInterface dao, PesquisaController pc, FormControllerInterface fc, String titulo) {
        this.dao = dao;
        this.pc = pc;
        this.fc = fc;
        this.lblTitulo.setText(titulo);
        pc.getTableViewPesquisar().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        btnEditar.setDisable(false);
                        btnRemover.setDisable(false);
                    } else {
                        btnEditar.setDisable(true);
                        btnRemover.setDisable(true);
                    }
                }
        );
        this.start();
    }

    @Override
    public Parent getLayout() {
        return this.apCrud;
    }

    private void start() {
        this.stackPane.getChildren().clear();
        this.stackPane.getChildren().add(pc.getLayout());

        btnNovo.setDisable(false);
        btnEditar.setDisable(true);
        btnRemover.setDisable(true);
        btnSalvar.setDisable(true);
        btnCancelar.setDisable(true);
    }

}
