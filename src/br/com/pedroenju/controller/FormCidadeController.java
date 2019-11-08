package br.com.pedroenju.controller;

import br.com.pedroenju.contracts.LookupControllerInterface;
import br.com.pedroenju.model.Cidade;
import br.com.pedroenju.model.Estado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author lukas
 */
public class FormCidadeController implements Initializable, LookupControllerInterface {

    @FXML
    private AnchorPane apCidade;
    @FXML
    private Font x1;
    @FXML
    private TextField txtNome;
    @FXML
    private Insets x2;
    @FXML
    private TextField txtEstado;
    @FXML
    private Button btLookup;
    private Cidade cidade;
    private LookupController lkp;
    private BooleanProperty hasActiveLookup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.hasActiveLookup = new SimpleBooleanProperty(false);

    }

    @FXML
    private void buscarEstado(ActionEvent event) {
        StackPane parent = (StackPane) apCidade.getParent();
        parent.getChildren().add(lkp.getLayout());
        lkp.startForm();
        this.hasActiveLookup.setValue(Boolean.TRUE);

        Task<Object> lookup = new Task() {
            @Override
            protected Object call() throws Exception {
                while (!lkp.isCloseRequested()) {
                    Thread.sleep(500);
                }
                return lkp.getModel();
            }
        };

        lookup.setOnSucceeded((evt) -> {
            if (lookup.getValue() != null) {
                cidade.setEstado((Estado) lookup.getValue());
                this.setModel(this.getModel());
            }
            this.hasActiveLookup.setValue(Boolean.FALSE);
            parent.getChildren().remove(lkp.getLayout());
        });

        new Thread(lookup).start();
    }

    @Override
    public Parent getLayout() {
        return this.apCidade;
    }

    @Override
    public void setModel(Object model) {
        this.cidade = (Cidade) model;
        txtNome.setText(this.cidade.getNomeCidade());
        if (this.cidade.getEstado() != null) {
            txtEstado.setText(this.cidade.getEstado().getNomeEstado());
        } else {
            txtEstado.setText("");
        }
    }

    @Override
    public Object getModel() {
        this.cidade.setNomeCidade(txtNome.getText());

        return this.cidade;
    }

    @Override
    public void startForm() {
        this.setModel(new Cidade());
    }

    @Override
    public void setLookUp(LookupControllerInterface lkp) {
        this.lkp = (LookupController) lkp;
    }

    @Override
    public BooleanProperty hasActiveLookup() {
        return this.hasActiveLookup;
    }

}
