package br.com.pedroenju;

import br.com.pedroenju.contracts.CrudInterface;
import br.com.pedroenju.contracts.DaoInterface;
import br.com.pedroenju.contracts.FormControllerInterface;
import br.com.pedroenju.contracts.LookupControllerInterface;
import br.com.pedroenju.contracts.TableModelInterface;
import br.com.pedroenju.controller.LookupController;
import br.com.pedroenju.controller.NoIdeaController;
import br.com.pedroenju.controller.PesquisaController;
import br.com.pedroenju.dao.CidadeDao;
import br.com.pedroenju.dao.ClienteDao;
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
        
        FXMLLoader loadCrudView = new FXMLLoader(getClass().getResource("/br/com/pedroenju/view/CrudView.fxml"));
        Parent crudView = loadCrudView.load();
        CrudInterface ci = loadCrudView.getController();
        
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
        
        FXMLLoader loadFormEstado = new FXMLLoader(getClass().getResource("/br/com/pedroenju/view/FormEstadoView.fxml"));
        Parent formEstado = loadFormEstado.load();
        FormControllerInterface ce = loadFormEstado.getController();
        
        FXMLLoader loadFormCidade = new FXMLLoader(getClass().getResource("/br/com/pedroenju/view/FormCidadeView.fxml"));
        Parent formCidade = loadFormCidade.load();
        FormControllerInterface cc = loadFormCidade.getController();
        
        FXMLLoader loadFormCliente = new FXMLLoader(getClass().getResource("/br/com/pedroenju/view/FormClienteView.fxml"));
        Parent formCliente = loadFormCliente.load();
        FormControllerInterface ccl = loadFormCliente.getController();
        
        FXMLLoader loadLookup = new FXMLLoader(getClass().getResource("/br/com/pedroenju/view/Lookup.fxml"));
        Parent formLookup = loadLookup.load();
        FormControllerInterface cl = loadLookup.getController();
        
        nic.getBtnPesquisarEstado().addEventHandler(ActionEvent.ACTION, (event) -> {
            TableModelInterface tableModel = new EstadoDao(Conexao.getInstance().getConn());
            pc.config(tableModel);
            ci.config((DaoInterface) tableModel, pc, ce, "Gerenciar Estado");
                    
            nic.getStackPane().getChildren().clear();
            nic.getStackPane().getChildren().add(crudView);
            stage.setTitle("Pesquisa de Estado");
        });

        nic.getBtnPesquisarCidade().addEventHandler(ActionEvent.ACTION ,(event) -> {
            ((LookupController) cl).configurar(new EstadoDao(Conexao.getInstance().getConn()), "Pesquisar Estado");
            
            TableModelInterface tableModel = new CidadeDao(Conexao.getInstance().getConn());
            ((LookupControllerInterface) cc).setLookUp((LookupControllerInterface) cl);
            pc.config(tableModel);
            ci.config((DaoInterface) tableModel, pc, cc, "Gerenciar Cidade");
            
            nic.getStackPane().getChildren().clear();
            nic.getStackPane().getChildren().add(crudView);
            stage.setTitle("Pesquisa de Cidade");
        });

        nic.getBtnPesquisarCliente().addEventHandler(ActionEvent.ACTION ,(event) -> {
            ((LookupController) cl).configurar(new CidadeDao(Conexao.getInstance().getConn()), "Pesquisar Cidade");
            
            TableModelInterface tableModel = new ClienteDao(Conexao.getInstance().getConn());
            ((LookupControllerInterface) ccl).setLookUp((LookupControllerInterface) cl);
            pc.config(tableModel);
            ci.config((DaoInterface) tableModel, pc, ccl, "Gerenciar Cliente");
            
            nic.getStackPane().getChildren().clear();
            nic.getStackPane().getChildren().add(crudView);
            stage.setTitle("Pesquisa de Cliente");
        });
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
