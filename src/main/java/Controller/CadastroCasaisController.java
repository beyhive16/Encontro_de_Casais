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

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

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
	TextField campoBairro;
        
        @FXML
	TextField campoCEP;
        
        @FXML
        ImageView campoImagem;
        
        private Image imagem;
        
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
                String VARcampoBairro = campoBairro.getText();
                String VARcampoCEP = campoCEP.getText();

		if (VARcampoApelido.isEmpty() | VARcampoNomeDela.isEmpty() | VARcampoNomeDele.isEmpty()
				| VARcampoTelefoneDela.isEmpty() | VARcampoTelefoneDele.isEmpty() | VARcampoBairro.isEmpty() | VARcampoCEP.isEmpty()) {
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

				Casal casal = new Casal();
				casal.setApelidoDoCasal(VARcampoApelido);
				casal.setNomeDela(VARcampoNomeDela);
				casal.setNomeDele(VARcampoNomeDele);
				casal.setTelefoneDela(VARcampoTelefoneDela);
				casal.setTelefoneDele(VARcampoTelefoneDele);
                                /*casal.setEndereco(VARcampoEndereco); ESSA LINHA VAI MORRER PQ NAO EXISTE MAIS ESSE CAMPO
                                AS SEGUINTES LINHAS SAO ADICIONADAS E VOCE TEM QUE CRIAR OS CAMPOS PRA ELAS NO BANCO
                                casal.setBairro(VARcampoBairro);
                                casal.setCEP(VARcampoCEP);*/
				casalDAO.create(casal);

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
		limpaCampos();
	}

	public void retornarMenu(ActionEvent event) throws IOException {
		System.out.println("Retornando ao Menu Principal");
		Parent next = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
		Scene scene = new Scene(next);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
        public void limpaCampos(){
            campoApelido.setText("");
            campoNomeDela.setText("");
            campoNomeDele.setText("");
            campoTelefoneDela.setText("");
            campoTelefoneDele.setText("");
            campoBairro.setText("");
            campoCEP.setText("");
        }
        public void goSelecionarImagem(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        if (file!=null){
            Image image = new Image(file.toURI().toString());
            campoImagem.setImage(image);
        }
    }
}
