package br.com.pedroenju.controller;

import br.com.pedroenju.contracts.TableModelInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Pedro Enju
 */
public class PesquisaController implements Initializable {

    @FXML
    private BorderPane borderPanePesquisar;
    @FXML
    private TextField inputPesquisar;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TableView<Object> tableViewPesquisar;

    private TableModelInterface tableModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void config(TableModelInterface tm) {
        this.tableModel = tm;
        this.start();
    }

    private void start() {
        /* Limpar Linhas */
        this.tableViewPesquisar.getItems().clear();
        /* Limpar Colunas */
        this.tableViewPesquisar.getColumns().clear();

        /* Criar as colunas na tabela de acordo com o TableModel configurado */
        this.tableViewPesquisar.getColumns().addAll(this.tableModel.getCols());
    }

    @FXML
    private void btnPesquisarOnAction(ActionEvent event) {
        this.tableViewPesquisar.getItems().clear();
        this.tableViewPesquisar.getItems().addAll(FXCollections.observableArrayList(this.tableModel.search(inputPesquisar.getText())));
    }
    
    public Parent getLayout() {
        return this.borderPanePesquisar;
    }

    public TableView<Object> getTableViewPesquisar() {
        return tableViewPesquisar;
    }

}
