/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entidade.Casal;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raquelmelo
 */
public class DetalhesEncontroController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
	@FXML
	private ChoiceBox<String> selectEquipe;
	
    @FXML
    private TableView<Casal> tabelaIntegrantes;

    @FXML
    private TableColumn<Casal, Boolean> nomeDele;
    
    @FXML
    private TableColumn<Casal, Boolean> nomeDela;
    
    @FXML
    private TableColumn<Casal, Boolean> apelido;
    
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
        
        //adiciona itens ao choicebox
        selectEquipe.setItems(ChoiceBoxEquipes());
        //define a escolha
        selectEquipe.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->pegaEscolha(selectEquipe));
        
    }    
    private ObservableList<Casal> CasaisSelecionados() {
        return FXCollections.observableArrayList(
                new Casal("Joyce", "Abel", "Tops","0000"),
                new Casal("Thaila", "Ítalo", "Grude","0000"),
                new Casal("Juliana", "Cadu", "Farra","000000")
        );
    }
    
    private ObservableList<String> ChoiceBoxEquipes() {
        return FXCollections.observableArrayList("Item 1","Item 2","Item 3");
    }
    
    public void pegaEscolha(ChoiceBox<String> choiceBox){
    	String escolha = choiceBox.getValue();
    	//aqui voce pega essa escolha e compara com o nome das equipes, a impressao é pra teste
    	System.out.println(escolha);
    }
    
    public void retornarEncontro(ActionEvent event) throws IOException{
        System.out.println("Retornando ao Menu Principal");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
