package com.example.sanduicheria;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// a linha abaixo é para definir o nome da tabela.

@Entity(tableName = "sanduiche_table")

public class SanduicheModal {

    // a linha abaixo é para auto incrementar id para cada sanduíche.
    @PrimaryKey(autoGenerate = true)

    // variável para o nosso id.
    private int id;

    // variável para o nome do sanduíche.
    private String sanduicheName;

    // variável para a descrição do sanduíche.
    private String sanduicheDescription;

    // variável para o tipo do sanduíche.
    private String sanduicheType;

    // abaixo da linha estamos criando uma classe de construtor.
    // Dentro da classe do construtor não estamos passando nosso id
    // porque ele está incrementando automaticamente

    public SanduicheModal(String sanduicheName, String sanduicheDescription, String sanduicheType) {
        this.sanduicheName = sanduicheName;
        this.sanduicheDescription = sanduicheDescription;
        this.sanduicheType = sanduicheType;
    }

    // na linha abaixo estamos criando métodos getter e setter.
    public String getSanduicheName() {
        return sanduicheName;
    }

    public void setSanduicheName(String sanduicheName) {
        this.sanduicheName = sanduicheName;
    }

    public String getSanduicheDescription() {
        return sanduicheDescription;
    }

    public void setSanduicheDescription(String sanduicheDescription) {
        this.sanduicheDescription = sanduicheDescription;
    }

    public String getSanduicheType() {
        return sanduicheType;
    }

    public void setSanduicheType(String sanduicheType) {
        this.sanduicheType = sanduicheType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
