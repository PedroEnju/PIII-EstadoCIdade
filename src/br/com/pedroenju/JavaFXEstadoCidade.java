package br.com.pedroenju;

import br.com.pedroenju.contracts.TableModelInterface;
import br.com.pedroenju.controller.NoIdeaController;
import br.com.pedroenju.controller.PesquisaController;
import br.com.pedroenju.dao.CidadeDao;
import br.com.pedroenju.dao.EstadoDao;
import br.com.pedroenju.services.Conexao;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Pedro Enju
 */
public class JavaFXEstadoCidade extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/br/com/pedroenju/view/PesquisaView.fxml"));

        /* Carrega o LAYOUT e o controller Principal */
        /* begin: */
        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/br/com/pedroenju/view/NoIdeaView.fxml"));
        Parent root = loaderMain.load();
        NoIdeaController nic = loaderMain.getController();
        /* end; */

        /* Carrega o LAYOUT e o controller Pesquisa */
        /* begin: */
        FXMLLoader loaderSearch = new FXMLLoader(getClass().getResource("/br/com/pedroenju/view/PesquisaView.fxml"));
        Parent borderPane = loaderSearch.load();
        PesquisaController pc = loaderSearch.getController();
        /* end; */

        Scene scene = new Scene(root);
        stage.setTitle("Menu Principal");
        stage.setScene(scene);
        stage.show();
        
        nic.getBtnPesquisarEstado().addEventHandler(ActionEvent.ACTION, (event) -> {
            nic.getStackPane().getChildren().clear();
            nic.getStackPane().getChildren().add(borderPane);
            TableModelInterface tableModel = new EstadoDao(Conexao.getInstance().getConn());
            pc.config(tableModel);
            stage.setTitle("Pesquisa de Estado");
        });

        nic.getBtnPesquisarCidade().addEventHandler(ActionEvent.ACTION ,(event) -> {
            nic.getStackPane().getChildren().clear();
            nic.getStackPane().getChildren().add(borderPane);
            TableModelInterface tableModel = new CidadeDao(Conexao.getInstance().getConn());
            pc.config(tableModel);
            stage.setTitle("Pesquisa de Cidade");
        });
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
