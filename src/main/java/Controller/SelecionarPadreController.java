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
import Dao.OrientadorEspiritualDAO;
import Dao.OrientadorEspiritualDAOImp;
import Entidade.Encontro;
import Entidade.OrientadorEspiritual;
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
public class SelecionarPadreController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<OrientadorEspiritual> tabelaPadres;
    
    @FXML
    private TableColumn<OrientadorEspiritual, Boolean> nome;
    
    private Encontro encontro = new Encontro();
    
    @SuppressWarnings("restriction")
	@Override
    public void initialize(URL url, ResourceBundle rb) {

        nome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        
        tabelaPadres.setItems(PadreSelecionado());        
        List<OrientadorEspiritual> listaTemp = new ArrayList<OrientadorEspiritual>();
        tabelaPadres.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				
				OrientadorEspiritual padreAtual = tabelaPadres.getSelectionModel().getSelectedItem();
				listaTemp.add(padreAtual);
				for (OrientadorEspiritual p : listaTemp) {
						p.setSelected(false);
				}
				listaTemp.get(listaTemp.size()-1).setSelected(true);
			}	
		});
        
    }    
    private ObservableList<OrientadorEspiritual> PadreSelecionado() {
        List<OrientadorEspiritual> orientadorEspirituals = new ArrayList<>();
    	try {
			BaseDAO baseDAO = new BaseDAO();
			OrientadorEspiritualDAO orientadorEspiritualDAO = new OrientadorEspiritualDAOImp(baseDAO);
			orientadorEspirituals = orientadorEspiritualDAO.getAll();
			baseDAO.getConnection().close();
    	} catch (SQLException | IOException e) {
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
    	return FXCollections.observableArrayList(orientadorEspirituals);
    }
    
    public void goEnviarPadreSelecionado(ActionEvent event) throws IOException{
        System.out.println("goEnviarPadreSelecionado");
        ObservableList<OrientadorEspiritual> var = tabelaPadres.getItems();
        for(OrientadorEspiritual p : var) {
        	if(p.getSelected()) {
        		System.out.println(p.getNome());
        		try {
					BaseDAO baseDAO = new BaseDAO();
					EncontroDAO encontroDAO = new EncontroDAOImp(baseDAO);
					this.encontro.setOrientadorEspiritual(p);
					encontroDAO.createOrUpdate(encontro);
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
        for (OrientadorEspiritual p: tabelaPadres.getSelectionModel().getSelectedItems()) {
        	p.setSelected(false);
        }
        retornarRegistro(event);
    }
    
    public void retornarRegistro(ActionEvent event) throws IOException{
        System.out.println("Retornando ao Menu Principal");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/RegistrarEncontro.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        RegistrarEncontroController registrarEncontroController = fxmlLoader.<RegistrarEncontroController>getController();
        registrarEncontroController.fromPadre(encontro);
        stage.setScene(scene);
        stage.show();
    }
	public Encontro getEncontro() {
		return encontro;
	}
	public void setEncontro(Encontro encontro) {
		this.encontro = encontro;
	}
    
    
}
