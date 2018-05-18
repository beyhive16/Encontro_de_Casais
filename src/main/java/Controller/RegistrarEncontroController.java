/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entidade.Casal;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
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
    TextField campoTema,campoPadre,campoAno;
    
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
    
    @SuppressWarnings("restriction")
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.campoTema = new TextField("");
        this.campoPadre = new TextField("");
        this.campoAno = new TextField("");
      
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
    
    public void goAdicionarIntegrante(ActionEvent event) throws IOException{
        System.out.println("goAdicionarIntegrante");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/SelecionarCasais.fxml"));
        
        //PARTE QUE MUDA DE TELA
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void goRemoverIntegrante(ActionEvent event){
        System.out.println("goRemoverIntegrante");
        ObservableList<Casal> var = tabelaIntegrantes.getItems();
        for(Casal c : var) {
        	if(c.getSelected()) {
        		System.out.println(c.getApelidoDoCasal());
        		//todos os casais selecionados que virem voce apaga da equipe
        	}
        }
        for (Casal c: tabelaIntegrantes.getSelectionModel().getSelectedItems()) {
        	c.setSelected(false);
        }
        
    }
    
    public void goSelecionarPadre(ActionEvent event) throws IOException{
        System.out.println("goSelecionarPadre");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/SelecionarPadre.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void goRegistrar(ActionEvent event) throws IOException{
        System.out.println("goRegistrar");
        //PEGA AS VARIAVEIS
        String VARcampoTema = campoTema.getText();
        String VARcampoPadre = campoPadre.getText();
        String VARcampoAno = campoAno.getText();
        //MANDA PRO BANCO
        //VAI PRO MENU        
        Parent next = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void retornarMenu(ActionEvent event) throws IOException{
        System.out.println("Retornando ao Menu Principal");
        Parent next = FXMLLoader.load(getClass().getResource("/Views/MenuPrincipal.fxml"));
        Scene scene = new Scene(next);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    private ObservableList<String> ChoiceBoxEquipes() {
        return FXCollections.observableArrayList("Item 1","Item 2","Item 3");
    }
    
    public void pegaEscolha(ChoiceBox<String> choiceBox){
    	String escolha = choiceBox.getValue();
    	//aqui voce pega essa escolha e compara com o nome das equipes, a impressao é pra teste
    	System.out.println(escolha);
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
    
}
