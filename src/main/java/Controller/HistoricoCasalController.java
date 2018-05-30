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
import Dao.CasalDAO;
import Dao.CasalDAOImp;
import Dao.EncontroDAO;
import Dao.EncontroDAOImp;
import Dao.HistoricoEquiDAOImp;
import Dao.HistoricoEquipeDAO;
import Entidade.Casal;
import Entidade.HistoricoEquipe;
import Entidade.HistoricoEquipeTransient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author raquelmelo
 */
public class HistoricoCasalController implements Initializable {

    /**
     * Initializes the controller class.
     */
	
	@FXML
	private ChoiceBox<String> selectAno;
	
	@FXML
	private ChoiceBox<String> selectCasal;
	
	
	@FXML
	private TableView<HistoricoEquipeTransient> tabelaCasal;
	
	@FXML
	private TableColumn<HistoricoEquipeTransient, Integer> ano;
	
	@FXML
	private TableColumn<HistoricoEquipeTransient, String> nome;
	
	//VARIAVEIS USADAS APENAS NO CONTROLLER
	private String anoSelecionado = new String();
	private String casalSelecionado = new String();
	private List<HistoricoEquipe> casais = new ArrayList<>();
	
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//adiciona itens ao choicebox
    	selectAno.setItems(ChoiceBoxAnos());
    	selectCasal.setItems(ChoiceBoxCasais());
    	
    	ano.setCellValueFactory(new PropertyValueFactory<HistoricoEquipeTransient, Integer>("ano"));
    	nome.setCellValueFactory(new PropertyValueFactory<HistoricoEquipeTransient, String>("nome"));
    	
    	tabelaCasal.setItems(historicoCasaisList(casais));
    	
        //mostra a escolha
    	selectAno.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->selecionaAno(selectAno));
    	selectCasal.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->selecionaCasal(selectCasal));
    	
    }    
    
    private ObservableList<String> ChoiceBoxAnos() {
    	BaseDAO baseDAO;
    	List<String> anos = null;
		try {
			baseDAO = new BaseDAO();
			EncontroDAO encontroDAO = new EncontroDAOImp(baseDAO);
			anos = encontroDAO.getAnos();
			if(anos==null)
			{

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Não existem dados cadastrados!");
				alert.setHeaderText("Não existem dados no banco, por favor cadastre Encontros, Casais e Padres para começar!");
				alert.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
						System.out.println("Pressed OK.");
					}
				});
				anos = new ArrayList<>();
			}
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
		} catch (NullPointerException e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Não existem dados cadastrados!");
			alert.setHeaderText(e.getMessage() + "   Não existem dados no banco, por favor cadastre Encontros, Casais e Padres para começar!");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
		}
        return FXCollections.observableArrayList(anos);
    }
    
    private ObservableList<String> ChoiceBoxCasais() {
    	BaseDAO baseDAO;
    	List<Casal> casals = new ArrayList<>();
    	List<String> saida = new ArrayList<String>();
    	
    	
    	try {
			baseDAO = new BaseDAO();
			CasalDAO casalDAO = new CasalDAOImp(baseDAO);
			casals = casalDAO.getAll();
			for (Casal casal : casals) {
				saida.add(casal.getApelidoDoCasal());
			}
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
        return FXCollections.observableArrayList(saida);
    }
    
    
    public void selecionaAno(ChoiceBox<String> choiceBox)
    {
    	String escolha = choiceBox.getValue();
    	this.anoSelecionado = escolha;
    }
    
    public void selecionaCasal(ChoiceBox<String> choiceBox)
    {
    	this.casalSelecionado = choiceBox.getValue();
    	List<HistoricoEquipe> historicoEquipes = new ArrayList<>();
    	
    	if(anoSelecionado.isEmpty())
    	{
    		try {
				BaseDAO baseDAO = new BaseDAO();
				HistoricoEquipeDAO historicoEquipeDAO = new HistoricoEquiDAOImp(baseDAO);
				
				casais = historicoEquipeDAO.getHistoricoPorApelido(casalSelecionado);
				
				tabelaCasal.setItems(historicoCasaisList(casais));
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
    	else 
    	{
    		try {
				BaseDAO baseDAO = new BaseDAO();
				HistoricoEquipeDAO historicoEquipeDAO = new HistoricoEquiDAOImp(baseDAO);
				
				casais = historicoEquipeDAO.getHistoricoPorApelidoAno(casalSelecionado, anoSelecionado);
				
				tabelaCasal.setItems(historicoCasaisList(casais));
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
    
    public ObservableList<HistoricoEquipeTransient> historicoCasaisList(List<HistoricoEquipe> casais)
    {
    	if(casais==null)
    	{
    		return FXCollections.observableArrayList();
    	}
    	List<HistoricoEquipeTransient> historicoEquipeTransients = new ArrayList<>();
    	for (HistoricoEquipe historicoEquipe : casais) {
			HistoricoEquipeTransient historicoEquipeTransient = new HistoricoEquipeTransient(historicoEquipe);
			historicoEquipeTransients.add(historicoEquipeTransient);
		}
    	return FXCollections.observableArrayList(historicoEquipeTransients);
    }
    
    public void goEditarCasal(ActionEvent event) throws IOException{
        System.out.println("goEditarCasal");
        System.out.println("Retornando ao Menu Principal");
        
        Casal casal = new Casal();
        BaseDAO baseDAO;
		try {
			baseDAO = new BaseDAO();
			CasalDAO casalDAO = new CasalDAOImp(baseDAO);
			casal = casalDAO.getCasalForApelido(casalSelecionado);
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
        
        
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/CadastroCasais.fxml"));
		Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(fxmlLoader.load()));
        CadastroCasaisController controller = fxmlLoader.<CadastroCasaisController>getController();
        controller.init(casal);
        stage.show();
       
    }
    
    public void retornarCasais(ActionEvent event) throws IOException{
        System.out.println("Retornando ao Menu Principal");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
