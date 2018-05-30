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
import Dao.HistoricoEquiDAOImp;
import Dao.HistoricoEquipeDAO;
import Entidade.Casal;
import Entidade.Encontro;
import Entidade.Equipe;
import Entidade.HistoricoEquipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raquelmelo
 */
public class SelecionarCasaisController implements Initializable {

    private Casal Objeto2;

	/**
     * Initializes the controller class.
     */
    
    public RegistrarEncontroController rec;
	
    @FXML
    private TableView<Casal> tabelaIntegrantes;
    
    @FXML
    private TableColumn<Casal, String> nomeDele;
    
    @FXML
    private TableColumn<Casal, String> nomeDela;
    
    @FXML
    private TableColumn<Casal, String> apelido;
    
    private Encontro encontro;
    private Equipe equipe;
    
    @SuppressWarnings("restriction")
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        
        apelido.setCellValueFactory(
                new PropertyValueFactory<>("apelido"));
        
        nomeDela.setCellValueFactory(
                new PropertyValueFactory<>("nomeDela"));
        
        nomeDele.setCellValueFactory(
                new PropertyValueFactory<>("nomeDele"));
        
        tabelaIntegrantes.setItems(CasaisSelecionados());  
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
        
    }
    
    public void init(Encontro encontro, Equipe equipe)
    {
    	this.encontro = encontro;
    	this.equipe = equipe;
    }
    
    private ObservableList<Casal> CasaisSelecionados() {
        
    	BaseDAO baseDAO;
    	List<Casal> casais = new ArrayList<>();
		try {
			baseDAO = new BaseDAO();
			CasalDAO casalDAO = new CasalDAOImp(baseDAO);
			casais = casalDAO.queryForAll();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return FXCollections.observableArrayList(casais);
    }
    
    @FXML
    public void goEnviarCasaisSelecionados(ActionEvent event) throws IOException{
        System.out.println("goEnviarCasaisSelecionados");
        ObservableList<Casal> var = tabelaIntegrantes.getItems();
        for(Casal c : var) {
        	if(c.getSelected()) {
        		HistoricoEquipe historicoEquipe = new HistoricoEquipe();
        		historicoEquipe.setCasal(c);
        		historicoEquipe.setEncontro(encontro);
        		historicoEquipe.setEquipe(equipe);
        		
        		try {
					BaseDAO baseDAO = new BaseDAO();
					HistoricoEquipeDAO historicoEquipeDAO = new HistoricoEquiDAOImp(baseDAO);
					historicoEquipeDAO.create(historicoEquipe);
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
        for (Casal c: tabelaIntegrantes.getSelectionModel().getSelectedItems()) {
        	c.setSelected(false);
        }
        retornarRegistro(event);
    }
    
    public void retornarRegistro(ActionEvent event) throws IOException{
        System.out.println("Retornando ao Menu Principal");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/RegistrarEncontro.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        stage.setScene(new Scene(fxmlLoader.load()));
        RegistrarEncontroController registrarEncontroController = fxmlLoader.<RegistrarEncontroController>getController();
        registrarEncontroController.fromSelecao(encontro, equipe);
        stage.show();
    }

	public Casal getObjeto2() {
		return Objeto2;
	}
	public void setObjeto2(Casal objeto2) {
		Objeto2 = objeto2;
	}

	public void ConfiguraObjetoParaSCC(Casal obj) {
		setObjeto2(obj);
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
