/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

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
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//adiciona itens ao choicebox
    	selectAno.setItems(ChoiceBoxAnos());
    	selectCasal.setItems(ChoiceBoxCasais());
   
        //mostra a escolha
    	selectAno.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->pegaEscolha(selectAno));
    	selectCasal.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->pegaEscolha(selectCasal));
    	
    }    
    
    private ObservableList<String> ChoiceBoxAnos() {
        return FXCollections.observableArrayList("Ano 1","Ano 2","Ano 3");
    }
    
    private ObservableList<String> ChoiceBoxCasais() {
        return FXCollections.observableArrayList("Casal 1","Casal 2","Casal 3");
    }
    
    public void pegaEscolha(ChoiceBox<String> choiceBox){
    	String escolha = choiceBox.getValue();
    	//aqui voce pega essa escolha e compara com o nome no banco, a impressao é pra teste
    	System.out.println(escolha);
    }
    
    public void goEditarCasal(ActionEvent event) throws IOException{
        System.out.println("goEditarCasal");
        System.out.println("Retornando ao Menu Principal");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/CadastroCasais.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
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
