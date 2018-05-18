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
public class Padre {
    private final SimpleBooleanProperty selected;
    private final SimpleStringProperty nome;

    public Padre(String nome) {
        this.selected = new SimpleBooleanProperty(false);
        this.nome = new SimpleStringProperty(nome);
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
    
    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }
    
}
