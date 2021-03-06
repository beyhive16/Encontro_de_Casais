/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Dao.BaseDAO;
import Dao.EncontroDAO;
import Dao.EncontroDAOImp;
import Entidade.Encontro;
import Util.Datas;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raquelmelo
 */
public class MenuPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void goPaginaInicial(ActionEvent event){
        System.out.println("goPaginaInicial");
    }
    
    public void goEncontro(ActionEvent event) throws IOException{
    	
    	Encontro encontro = new Encontro();
    	try {
			BaseDAO baseDAO = new BaseDAO();
			EncontroDAO encontroDAO = new EncontroDAOImp(baseDAO);
			encontro.setAno(Datas.anoAtual());
			encontroDAO.createOrUpdate(encontro);
			baseDAO.getConnection().close();
    	} catch (SQLException | IOException e ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Ocorreu um erro ao consultar o banco!");
			alert.setHeaderText(e.getMessage());
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
		}
    	
        System.out.println("goEncontro");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/RegistrarEncontro.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
        RegistrarEncontroController registrarEncontroController = fxmlLoader.<RegistrarEncontroController>getController();
        registrarEncontroController.init(encontro);
        stage.show();
    }
    
    public void goHistorico(ActionEvent event) throws IOException{
        System.out.println("goHistorico");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/HistoricoEncontros.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void goSair(ActionEvent event){
        System.out.println("goSair");
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void goCasais(ActionEvent event) throws IOException{
        System.out.println("goCasais");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/CasaisCadastrados.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void goCadastros(ActionEvent event) throws IOException{
        System.out.println("goCadastros");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/SelecaoDestinoCadastro.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void goHistoricoCasais(ActionEvent event) throws IOException{
        System.out.println("goHistoricoCasais");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/HistoricoCasal.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
