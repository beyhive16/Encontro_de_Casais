/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Dao.BaseDAO;
import Dao.EncontroDAO;
import Dao.EncontroDAOImp;
import Dao.EquipeDAO;
import Dao.EquipeDAOImp;
import Dao.HistoricoEquiDAOImp;
import Dao.HistoricoEquipeDAO;
import Dao.TipoEquipeDAO;
import Dao.TipoEquipeDAOImp;
import Entidade.Casal;
import Entidade.Encontro;
import Entidade.Equipe;
import Entidade.TipoEquipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raquelmelo
 */
public class RegistrarEncontroController implements Initializable {

	/**
	 * Initializes the controller class.
	 */

	@FXML
	private ChoiceBox<String> selectEquipe;

	@FXML
	TextField campoTema, campoPadre, campoAno;

	@FXML
	private TableView<Casal> tabelaIntegrantes;

	@FXML
	private TableColumn<Casal, Boolean> nomeDele;

	@FXML
	private TableColumn<Casal, Boolean> nomeDela;

	@FXML
	private TableColumn<Casal, Boolean> apelido;

	private Casal Objeto1;

	public SelecionarCasaisController scc;
	
	private Encontro encontro;
	private Equipe equipe;
	private List<Casal> casais = new ArrayList<>();

	@SuppressWarnings("restriction")
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		apelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));

		nomeDela.setCellValueFactory(new PropertyValueFactory<>("nomeDela"));

		nomeDele.setCellValueFactory(new PropertyValueFactory<>("nomeDele"));

		tabelaIntegrantes.setItems(CasaisSelecionados(new ArrayList<Casal>()));
		tabelaIntegrantes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tabelaIntegrantes.getSelectionModel().setCellSelectionEnabled(true);
		tabelaIntegrantes.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				boolean estadoAtual = tabelaIntegrantes.getSelectionModel().getSelectedItem().getSelected();
				tabelaIntegrantes.getSelectionModel().getSelectedItem().setSelected(!estadoAtual);
			}
		});

		// adiciona itens ao choicebox
		selectEquipe.setItems(ChoiceBoxEquipes());
		// define a escolha
		selectEquipe.getSelectionModel().selectedItemProperty()
				.addListener((v, oldValue, newValue) -> pegaEscolha(selectEquipe));

	}
	
	public void init(Encontro encontro)
	{
		this.encontro=encontro;
		this.campoAno.setText(this.encontro.getAno().toString());
	}

	public void fromSelecao(Encontro encontro, Equipe equipe)
	{
		this.encontro = encontro;
		this.equipe = equipe;
		campoAno.setText(this.encontro.getAno().toString());
		campoTema.setText(this.encontro.getTema());
		campoPadre.setText(this.encontro.getOrientadorEspiritual().getNome());
	}
	
	public void fromPadre(Encontro encontro)
	{
		this.encontro = encontro;
		campoAno.setText(this.encontro.getAno().toString());
		campoTema.setText(this.encontro.getTema());
		if(this.encontro.getOrientadorEspiritual()!=null)
		{
			campoPadre.setText(this.encontro.getOrientadorEspiritual().getNome());			
		}
	}
	
	private ObservableList<Casal> CasaisSelecionados(List<Casal> casais) {
		return FXCollections.observableArrayList(casais);
	}

	public void goAdicionarIntegrante(ActionEvent event) throws IOException {
		System.out.println("goAdicionarIntegrante");
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/SelecionarCasais.fxml"));
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(fxmlLoader.load()));
		SelecionarCasaisController selecionarCasaisController = fxmlLoader.<SelecionarCasaisController>getController();
		getEncontro().setAno(Integer.parseInt(campoAno.getText()));
		getEncontro().setTema(campoTema.getText());
		selecionarCasaisController.init(getEncontro(), getEquipe());
		stage.show();
	}

	public void goRemoverIntegrante(ActionEvent event) {
		System.out.println("goRemoverIntegrante");
		ObservableList<Casal> var = tabelaIntegrantes.getItems();
		for (Casal c : var) {
			if (c.getSelected()) {
				System.out.println(c.getApelidoDoCasal());
				try {
					BaseDAO baseDAO = new BaseDAO();
					HistoricoEquipeDAO historicoEquipeDAO = new HistoricoEquiDAOImp(baseDAO);
					historicoEquipeDAO.deleteHistoricoCasal(equipe, encontro, c);
					baseDAO.getConnection().close();
				} catch (SQLException | IOException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Ocorreu um erro ao consultar o banco!");
					alert.setHeaderText(e.getMessage());
					alert.showAndWait().ifPresent(rs -> {
						if (rs == ButtonType.OK) {
							System.out.println("Pressed OK.");
						}
					});
				}
			}
		}
		for (Casal c : tabelaIntegrantes.getSelectionModel().getSelectedItems()) {
			c.setSelected(false);
		}

	}

	public void goSelecionarPadre(ActionEvent event) throws IOException {
		System.out.println("goSelecionarPadre");
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/SelecionarPadre.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(fxmlLoader.load());
		
		SelecionarPadreController selecionarPadreController = fxmlLoader.<SelecionarPadreController> getController();
		encontro.setTema(campoTema.getText());
		encontro.setAno(Integer.parseInt(campoAno.getText()));
		equipe = new Equipe();
		equipe.setAno(encontro.getAno());
		selecionarPadreController.setEncontro(encontro);
		stage.setScene(scene);
		stage.show();
	}

	public void goRegistrar(ActionEvent event) throws IOException {
		System.out.println("goRegistrar");
		String VARcampoTema = campoTema.getText();
		String VARcampoAno = campoAno.getText();
		
		try {
			BaseDAO baseDAO = new BaseDAO();
			EncontroDAO encontroDAO = new EncontroDAOImp(baseDAO);
			this.encontro.setAno(Integer.parseInt(VARcampoAno));
			this.encontro.setTema(VARcampoTema);
			encontroDAO.createOrUpdate(encontro);
			baseDAO.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Parent next = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
		Scene scene = new Scene(next);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void retornarMenu(ActionEvent event) throws IOException {
		System.out.println("Retornando ao Menu Principal");
		try {
			BaseDAO baseDAO = new BaseDAO();
			EncontroDAO encontroDAO = new EncontroDAOImp(baseDAO);
			encontroDAO.delete(this.encontro);
			baseDAO.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Parent next = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
		Scene scene = new Scene(next);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	private ObservableList<String> ChoiceBoxEquipes() {
		List<String> out = new ArrayList<>();
		try {
			BaseDAO baseDAO = new BaseDAO();
			TipoEquipeDAO tipoEquipeDAO = new TipoEquipeDAOImp(baseDAO);
			out = tipoEquipeDAO.getAllTipoEquipe();
			baseDAO.getConnection().close();
		} catch (SQLException | IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Ocorreu um erro ao tentar acessar o banco!");
			alert.setHeaderText("Foi impossivel acessar o banco de dados!");
			alert.setContentText(e.toString());
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
		}
		return FXCollections.observableArrayList(out);
	}

	public void pegaEscolha(ChoiceBox<String> choiceBox) {
		String escolha = choiceBox.getValue();
		encontro.setAno(Integer.parseInt(campoAno.getText()));
		try {
			BaseDAO baseDAO = new BaseDAO();
			TipoEquipeDAO tipoEquipeDAO = new TipoEquipeDAOImp(baseDAO);
			TipoEquipe tipoEquipe = tipoEquipeDAO.getForName(escolha);
			
			EquipeDAO equipeDAO = new EquipeDAOImp(baseDAO);
			Equipe equipe = equipeDAO.getEquipeForTipoAno(tipoEquipe,Integer.parseInt(campoAno.getText()));
			if(equipe==null)
			{
				equipe = new Equipe();
				equipe.setAno(Integer.parseInt(campoAno.getText()));
				equipe.setTipoEquipe(tipoEquipe);
				equipeDAO.createOrUpdate(equipe);
			}
			this.equipe = equipe;
			HistoricoEquipeDAO historicoEquipeDAO = new HistoricoEquiDAOImp(baseDAO);
			this.casais =  historicoEquipeDAO.getCasalForEquipeEncontro(equipe, encontro);
			tabelaIntegrantes.getItems().clear();
			tabelaIntegrantes.getItems().addAll(this.casais);
			baseDAO.getConnection().close();
		}catch (SQLException | IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Ocorreu um erro ao acessar o banco!");
			alert.setHeaderText(e.getMessage());
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
		}
		System.out.println(this.equipe);
	}

	public Casal getObjeto1() {
		return Objeto1;
	}

	public void setObjeto1(Casal objeto1) {
		Objeto1 = objeto1;
	}

	public Casal CarregaObjetoDeSCC() {
		return scc.getObjeto2();
	}

	public Encontro getEncontro() {
		return encontro;
	}

	public void setEncontro(Encontro encontro) {
		this.encontro = encontro;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

}
