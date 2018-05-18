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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author raquelmelo
 */
public class LoginController implements Initializable {

	@FXML
	private AnchorPane conteudo;

	@FXML
	TextField campoApelido;
	
	@FXML
	PasswordField campoSenha;

	@FXML
	private void handleButtonAction2(ActionEvent event) {
		System.out.println("FUNFOU!");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		//this.campoApelido = new TextField("");
		//this.campoSenha = new TextField("");
	}

	public void Entrar(ActionEvent event) throws IOException, SQLException {
		String VARcampoApelido = campoApelido.getText();
		String VARcampoSenha = campoSenha.getText();
		if (VARcampoApelido.isEmpty() || VARcampoSenha.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Preencha todos os campos");
			alert.setHeaderText("É necessário preencher todos os campos!");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
		} else {
			BaseDAO baseDAO = new BaseDAO();
			UsuarioDAO usuarioDAO = new UsuarioDAOImp(baseDAO);
			Usuario user = usuarioDAO.login(VARcampoApelido, VARcampoSenha);

			if (user == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Usuario ou senha incorretos!");
				alert.setHeaderText("Verifique se o seu login e senha foram digitados corretamente e tente novamente!");
				alert.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
						System.out.println("Pressed OK.");
					}
				});
			} else {
				Parent next = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
				Scene scene = new Scene(next);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
		}
	}

	// public void Cadastrar(ActionEvent event){
	// System.out.println("Entrou na funcao CADASTRAR");
	// }
	//
	public void Sair(ActionEvent event) {
		System.out.println("Entrou na funcao SAIR");
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

}
