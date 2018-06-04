/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import Dao.BaseDAO;
import Dao.CasalDAO;
import Dao.CasalDAOImp;
import Dao.EnderecoDAO;
import Dao.EnderecoDAOImp;
import Entidade.Casal;
import Entidade.Endereco;
import Util.ImageTools;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
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
	TextField campoRua;

	@FXML
	TextField campoNumero;

	@FXML
	TextField campoBairro;

	@FXML
	TextField campoCidade;

	@FXML
	TextField campoComplemento;

	@FXML
	TextField campoCEP;

	@FXML
	ImageView campoImagem;

	private Casal casal;

	private Image imagem;

	public void init(Casal casal) {
		this.casal = casal;
		this.campoApelido.setText(this.casal.getApelidoDoCasal());
		this.campoNomeDela.setText(this.casal.getNomeDela());
		this.campoNomeDele.setText(this.casal.getNomeDele());
		this.campoTelefoneDela.setText(this.casal.getTelefoneDela());
		this.campoTelefoneDele.setText(this.casal.getTelefoneDele());
		
		// this.campoEndereco.setText(casal.getEndereço());
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	public void goCadastrarCasais(ActionEvent event) throws IOException {
		// System.out.println("goCadastrarCasais");

		//CAMPOS BASES
		String VARcampoApelido = campoApelido.getText();
		String VARcampoNomeDela = campoNomeDela.getText();
		String VARcampoNomeDele = campoNomeDele.getText();
		String VARcampoTelefoneDela = campoTelefoneDela.getText();
		String VARcampoTelefoneDele = campoTelefoneDele.getText();
		
		byte[] VARImagem = ImageTools.toByteArray(campoImagem);
		
		//CAMPOS ENDEREÇOS
		String VARcampoRua = campoRua.getText();
		String VARcampoNumero = campoNumero.getText();
		String VARcampoBairro = campoBairro.getText();
		String VARcampoCidade = campoCidade.getText();
		String VARcampoComplemento = campoComplemento.getText();
		String VARcampoCEP = campoCEP.getText();
		
		if (VARcampoApelido.isEmpty() | VARcampoNomeDela.isEmpty() | VARcampoNomeDele.isEmpty()
				| VARcampoTelefoneDela.isEmpty() | VARcampoTelefoneDele.isEmpty() | VARcampoRua.isEmpty()
				| VARcampoNumero.isEmpty()| VARcampoBairro.isEmpty() | VARcampoCidade.isEmpty()
				| VARcampoCEP.isEmpty()) {
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
				EnderecoDAO enderecoDAO = new EnderecoDAOImp(baseDAO);

				if (this.casal == null) {
					this.casal = new Casal();
				}

				casal.setApelidoDoCasal(VARcampoApelido);
				casal.setNomeDela(VARcampoNomeDela);
				casal.setNomeDele(VARcampoNomeDele);
				casal.setTelefoneDela(VARcampoTelefoneDela);
				casal.setTelefoneDele(VARcampoTelefoneDele);
				
				Endereco endereco = new Endereco();
				endereco.setBairro(VARcampoBairro);
				endereco.setCep(VARcampoCEP);
				endereco.setCidade(VARcampoCidade);
				endereco.setComplemento(VARcampoComplemento);
				endereco.setnCasa(VARcampoNumero);
				endereco.setRua(VARcampoRua);
				
				Endereco endereco2 = enderecoDAO.createIfNotExists(endereco);
				
				casal.setFoto(VARImagem);
				casal.setEndereco(endereco2);

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
		//retornarMenu(event);
		this.limpaCampos();
	}

	public void retornarMenu(ActionEvent event) throws IOException {
		System.out.println("Retornando ao Menu Principal");
		Parent next = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
		Scene scene = new Scene(next);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void limpaCampos() {
		campoApelido.setText("");
		campoNomeDela.setText("");
		campoNomeDele.setText("");
		campoTelefoneDela.setText("");
		campoTelefoneDele.setText("");
		campoBairro.setText("");
		campoCEP.setText("");
	}

	public void goSelecionarImagem(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", "*.jpeg"));
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			campoImagem.setImage(image);
		}
	}
}
