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
import Dao.CasalDAO;
import Dao.CasalDAOImp;
import Entidade.Casal;
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
public class CadastroCasaisController implements Initializable {

	/**
	 * Initializes the controller class.
	 */

	@FXML
	TextField campoNomeDela;

	@FXML
	TextField campoApelido;

	@FXML
	TextField campoTelefoneDele;

	@FXML
	TextField campoTelefoneDela;

	@FXML
	TextField campoNomeDele;

	@FXML
	TextArea campoEndereco;

	private Casal casal;
	
	public void init(Casal casal)
	{
		this.casal = casal;
		this.campoApelido.setText(this.casal.getApelidoDoCasal());
		this.campoNomeDela.setText(this.casal.getNomeDela());
		this.campoNomeDele.setText(this.casal.getNomeDele());
		this.campoTelefoneDela.setText(this.casal.getTelefoneDela());
		this.campoTelefoneDele.setText(this.casal.getTelefoneDele());
		//this.campoEndereco.setText(casal.getEndereço());
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	public void goCadastrarCasais(ActionEvent event) throws IOException {
		System.out.println("goCadastrarCasais");

		String VARcampoApelido = campoApelido.getText();
		String VARcampoNomeDela = campoNomeDela.getText();
		String VARcampoNomeDele = campoNomeDele.getText();
		String VARcampoTelefoneDela = campoTelefoneDela.getText();
		String VARcampoTelefoneDele = campoTelefoneDele.getText();
		String VARcampoEndereco = campoEndereco.getText();

		if (VARcampoApelido.isEmpty() | VARcampoNomeDela.isEmpty() | VARcampoNomeDele.isEmpty()
				| VARcampoTelefoneDela.isEmpty() | VARcampoTelefoneDele.isEmpty() | VARcampoEndereco.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Preencha todos os campos");
			alert.setHeaderText("É necessário preencher todos os campos!");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
		} else {

			try {
				BaseDAO baseDAO = new BaseDAO();
				CasalDAO casalDAO = new CasalDAOImp(baseDAO);

				if(this.casal==null)
				{
					this.casal = new Casal();
				}
				
				casal.setApelidoDoCasal(VARcampoApelido);
				casal.setNomeDela(VARcampoNomeDela);
				casal.setNomeDele(VARcampoNomeDele);
				casal.setTelefoneDela(VARcampoTelefoneDela);
				casal.setTelefoneDele(VARcampoTelefoneDele);
				//casal.setEndereço(VARcampoEndereco);

				casalDAO.createOrUpdate(casal);

				baseDAO.getConnection().close();
			} catch (SQLException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Ocorreu um erro ao tentar salvar!");
				alert.setHeaderText("Foi impossivel salvar no banco de dados!");
				alert.setContentText(e.toString());
				alert.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
						System.out.println("Pressed OK.");
					}
				});
			}
		}
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
