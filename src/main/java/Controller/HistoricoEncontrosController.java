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
import Entidade.EncontroTransient;
import Entidade.Equipe;
import Entidade.TipoEquipe;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raquelmelo
 */
public class HistoricoEncontrosController implements Initializable {

    /**
     * Initializes the controller class.
	
     */
	@FXML
	private TableView<EncontroTransient> tabelaEncontro;

	@FXML
	private TableColumn<EncontroTransient, Integer> ano;
	
	@FXML
	private TableColumn<EncontroTransient, String> orientadorEspiritual;
	
	@FXML
	private TableColumn<EncontroTransient, String> casalCoordenador;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		//.setCellValueFactory(new PropertyValueFactory<>("apelido"));
		this.ano.setCellValueFactory(new PropertyValueFactory<>("ano"));
		this.orientadorEspiritual.setCellValueFactory(new PropertyValueFactory<>("nome_padre"));
		this.casalCoordenador.setCellValueFactory(new PropertyValueFactory<>("apelido_casal"));
		
		List<Encontro> encontro;
		TipoEquipe tipoEquipe;
		List<EncontroTransient> encontroTransients = new ArrayList<>();
		try {
			BaseDAO baseDAO = new BaseDAO();
			TipoEquipeDAO tipoEquipeDAO = new TipoEquipeDAOImp(baseDAO);
			tipoEquipe = tipoEquipeDAO.getForName("Coordenador Geral");
			
			EncontroDAO encontroDAO = new EncontroDAOImp(baseDAO);
			encontro = encontroDAO.getAll();
			
			HistoricoEquipeDAO historicoEquipeDAO = new HistoricoEquiDAOImp(baseDAO);
			EquipeDAO equipeDAO = new EquipeDAOImp(baseDAO);
			for (Encontro encontroTemp : encontro) {
				Equipe equipe = equipeDAO.getEquipeForTipoAno(tipoEquipe, encontroTemp.getAno());
				Casal casal = null;
				if(equipe==null)
				{
					equipe = new Equipe();
				}else
				{					
					casal = historicoEquipeDAO.getCasalEquipeEncontro(equipe, encontroTemp);
				}
				if(casal==null)
				{
					casal = new Casal();
				}
				EncontroTransient a = new EncontroTransient(encontroTemp, casal);
				System.out.println(a);
				encontroTransients.add(a);
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
		
		this.tabelaEncontro.getItems().addAll(encontroSelecionados(encontroTransients));
	}    
    
	private ObservableList<EncontroTransient> encontroSelecionados(List<EncontroTransient> encontroTransients) {
		return FXCollections.observableArrayList(encontroTransients);
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
