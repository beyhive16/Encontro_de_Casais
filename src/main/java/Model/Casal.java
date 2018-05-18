/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author raquelmelo
 */
public class Casal {
    private final SimpleBooleanProperty selected;
    private final SimpleStringProperty nomedela;
    private final SimpleStringProperty nomedele;
    private final SimpleStringProperty telefoneDele;
//    private final SimpleStringProperty telefoneDela;
    private final SimpleStringProperty apelido;

    public Casal(String nomedela,String nomedele,String apelido, String telefoneDele) {
        this.selected = new SimpleBooleanProperty(false);
        this.nomedela = new SimpleStringProperty(nomedela);
        this.nomedele = new SimpleStringProperty(nomedele);
        this.telefoneDele = new SimpleStringProperty(telefoneDele);
//        this.telefoneDela = new SimpleStringProperty(telefoneDela);
        this.apelido = new SimpleStringProperty(apelido);
    }
    public boolean isSelected() {
        return selected.get();
    }

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }
    
    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
    
    public String getNomeDela() {
        return nomedela.get().toString();// TODO Auto-generated constructor stub
    }

    public SimpleStringProperty nomeDelaProperty() {
        return nomedela;
    }

    public void setNomeDela(String nome) {
        this.nomedela.set(nome);
    }
    
    public String getNomeDele() {
        return nomedele.get().toString();
    }

    public SimpleStringProperty nomeDeleProperty() {
        return nomedele;
    }

    public void setNomeDele(String nome) {
        this.nomedele.set(nome);
    }
    
    public String getApelido() {
        return apelido.get().toString();
    }

    public SimpleStringProperty apelidoProperty() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido.set(apelido);
    }
    
    public String getTelefoneDele() {
        return telefoneDele.get().toString();
    }

    public SimpleStringProperty telefoneDeleProperty() {
        return telefoneDele;
    }

    public void setTelefoneDele(String telefoneDele) {
        this.telefoneDele.set(telefoneDele);
    }
//    
//    public String getTelefoneDela() {
//        return telefoneDela.get();
//    }
//
//    public SimpleStringProperty telefoneDelaProperty() {
//        return telefoneDela;
//    }
//
//    public void setTelefoneDela(String telefoneDela) {
//        this.telefoneDela.set(telefoneDela);
//    }
}
