/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entidade.OrientadorEspiritual;
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
        return FXCollections.observableArrayList(
                new OrientadorEspiritual("João"),
                new OrientadorEspiritual("José"),
                new OrientadorEspiritual("Jacó")
        );
    }
    
    public void goEnviarPadreSelecionado(ActionEvent event) throws IOException{
        System.out.println("goEnviarPadreSelecionado");
        ObservableList<OrientadorEspiritual> var = tabelaPadres.getItems();
        for(OrientadorEspiritual p : var) {
        	if(p.getSelected()) {
        		System.out.println(p.getNome());
        		//vai pegar o padre P que voltou, trazer ele pra tela anterior e adicionar ao campo 
        		//vai receber o ultimo padre clicado
        	}
        }
        for (OrientadorEspiritual p: tabelaPadres.getSelectionModel().getSelectedItems()) {
        	p.setSelected(false);
        }
        retornarRegistro(event);
    }
    
    public void retornarRegistro(ActionEvent event) throws IOException{
        System.out.println("Retornando ao Menu Principal");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/RegistrarEncontro.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
}
