/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entidade.Casal;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.BaseDAO;
import dao.CasalDAO;
import dao.CasalDAOImp;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raquelmelo
 */
public class CasaisCadastradosController implements Initializable {

    /**
     * Initializes the controller class.
     */
	@FXML
    TableView<Casal>tabelaCasaisCadastrados;
    
    @FXML
    private TableColumn<Casal, String> nomeDele;
    
    @FXML
    private TableColumn<Casal, String> nomeDela;
    
    @FXML
    private TableColumn<Casal, String> telefoneDele;
    
    @FXML
    private TableColumn<Casal, String> apelido;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        apelido.setCellValueFactory(
        		new PropertyValueFactory<Casal,String>("apelidoDoCasal"));
        
        nomeDela.setCellValueFactory(
                new PropertyValueFactory<Casal,String>("nomeDela"));
        
        nomeDele.setCellValueFactory(
                new PropertyValueFactory<Casal,String>("nomeDele"));
        
        telefoneDele.setCellValueFactory(
                new PropertyValueFactory<Casal,String>("telefoneDele"));
        
        
        Casal a = new Casal("Joyce", "Abel", "Tops","0000");
        Casal b = new Casal("Thaila", "Ítalo", "Grude","0000");
        Casal c = new Casal("Juliana", "Cadu", "Farra","000000");
        
        System.out.println(tabelaCasaisCadastrados);
        tabelaCasaisCadastrados.setItems(CasaisCadastrados());
        
        tabelaCasaisCadastrados.setRowFactory( tv->{
            TableRow<Casal> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                    Casal rowData = row.getItem();
                    System.out.println(rowData);
            });
            return row ;
        });
        
    }    
    
    private ObservableList<Casal> CasaisCadastrados() {
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
    
    public void retornarMenu(ActionEvent event) throws IOException{
        System.out.println("Retornando ao Menu Principal");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void ok(ActionEvent event) throws IOException{
        Casal c = tabelaCasaisCadastrados.getSelectionModel().getSelectedItem();
        
//        if (c==null){
//            //mostrar que nao ha nada selecionado em uma wizard
//        }else{
//            String apelido = c.getApelidoDoCasal();
//        }
//        System.out.println(tabelaCasaisCadastrados.getSelectionModel().getSelectedItem().getApelido());
//        Parent next = FXMLLoader.load(getClass().getResource("/Views/HistoricoCasal.fxml"));
//        Scene scene = new Scene(next);
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
    }
    
}
