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
import Dao.OrientadorEspiritualDAO;
import Dao.OrientadorEspiritualDAOImp;
import Entidade.OrientadorEspiritual;
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
public class CadastroPadreController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@FXML
	TextField campoNome;
	
	@FXML
	TextField campoTelefone;
	
	@FXML
	TextArea campoEndereco;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	//	this.campoNome = new TextField("");
	//	this.campoTelefone = new TextField("");
	//	this.campoEndereco = new TextField("");
	}

	public void goCadastrarPadre(ActionEvent event) throws IOException {
		// System.out.println("goCadastrarPadre");
		// PEGA AS VARIAVEIS
		String VARcampoNome = campoNome.getText().toUpperCase();
		String VARcampoTelefone = campoTelefone.getText().toUpperCase();
		String VARcampoEndereco = campoEndereco.getText().toUpperCase();
		System.out.println(VARcampoEndereco+"/"+VARcampoNome+"/"+VARcampoTelefone);
		/*if (VARcampoEndereco.isEmpty() || VARcampoNome.isEmpty() || VARcampoNome.isEmpty()) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Campos não preenchidos!");
			alert.setHeaderText("Preencha todos os campos antes de tentar salvar!");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
		} else {*/
			try {
				BaseDAO baseDAO = new BaseDAO();
				OrientadorEspiritualDAO orientadorEspiritualDAO = new OrientadorEspiritualDAOImp(baseDAO);
				OrientadorEspiritual orientadorEspiritual = new OrientadorEspiritual();
				orientadorEspiritual.setNome(VARcampoNome);
				orientadorEspiritual.setTelefone(VARcampoTelefone);
				//orientadorEspiritual.setEndereco(VARcampoEndereco);
				orientadorEspiritual.setApelido("");

				orientadorEspiritualDAO.create(orientadorEspiritual);
				baseDAO.getConnection().close();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Salvo com sucesso!");
				alert.setHeaderText("Todos os dados foram salvos corretamente!");
				alert.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
						System.out.println("Pressed OK.");
					}
				});
			} catch (SQLException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Ocorreu um erro ao tentar salvar!");
				alert.setHeaderText(e.toString());
				alert.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
						System.out.println("Pressed OK.");
					}
				});
			}
		//}

		// CADASTRA NO BANCO
		// FECHA A TELA
		retornarMenu(event);
	}

	public void retornarMenu(ActionEvent event) throws IOException {
		System.out.println("Retornando ao Menu Principal");
		Parent next = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
		Scene scene = new Scene(next);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
}
