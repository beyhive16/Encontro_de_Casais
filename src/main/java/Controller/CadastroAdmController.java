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
import Dao.UsuarioDAO;
import Dao.UsuarioDAOImp;
import Entidade.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author raquelmelo
 */
public class CadastroAdmController implements Initializable {

    /**
     * Initializes the controller class.
     */
	@FXML
    TextField campoApelido;
   
	@FXML
	TextField campoConfirmarSenha;
    
	@FXML
	TextField campoSenha;
    
	@FXML
	TextField campoTelefone;
    
	@FXML
	TextArea campoEndereco;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   //     this.campoApelido = new TextField("");
    //    this.campoConfirmarSenha = new TextField("");
     //   this.campoSenha = new TextField("");
      //  this.campoTelefone = new TextField("");
       // this.campoEndereco = new TextField("");
    }
    
    public void goCadastrarAdm(ActionEvent event) throws IOException{
        System.out.println("goCadastrarAdm");
        //PEGAR AS VARIAVEIS
        String VARcampoApelido = campoApelido.getText();
        String VARcampoConfirmarSenha = campoConfirmarSenha.getText();
        String VARcampoSenha = campoSenha.getText();
        String VARcampoTelefone = campoTelefone.getText();
        String VARcampoEndereco = campoEndereco.getText();
        
        if(VARcampoApelido.isEmpty()|VARcampoEndereco.isEmpty()|VARcampoTelefone.isEmpty()|VARcampoSenha.isEmpty()|VARcampoConfirmarSenha.isEmpty())
        {
        	Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Preencha todos os dados!");
			alert.setHeaderText("É necessário que todos os dados sejam preenchidos!");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
			
			if(!VARcampoSenha.equals(VARcampoConfirmarSenha))
			{
				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("As senhas não conferem!");
				alert2.setHeaderText("Digite novamente a sua senha!");
				alert2.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
						System.out.println("Pressed OK.");
					}
				});
			}
        }else
        {
        	BaseDAO baseDAO;
        	try {
        		baseDAO = new BaseDAO();
        		UsuarioDAO userDAO  = new UsuarioDAOImp(baseDAO);
        		Usuario user = new Usuario();
        		user.setNomeUsual(VARcampoApelido);
        		user.setNome("");
        		user.setSenha(VARcampoSenha);
        		user.setTelefone(VARcampoTelefone);
        		//user.setEndereco(VARcampoEndereco);
        		userDAO.create(user);
        		baseDAO.getConnection().close();
        	} catch (SQLException e) {
        		Alert alert = new Alert(AlertType.ERROR);
        		alert.setTitle("Não foi possivel salvar os dados");
        		alert.setHeaderText("Algum erro ocorreu ao tentar salvar os dados, verifique seu banco de dados!");
        		alert.showAndWait().ifPresent(rs -> {
        			if (rs == ButtonType.OK) {
        				System.out.println("Pressed OK.");
        			}
        		});
        	}	
        }
        retornarMenu(event);
    }
    
    public void retornarMenu(ActionEvent event) throws IOException{
        System.out.println("Retornando ao Menu Principal");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
