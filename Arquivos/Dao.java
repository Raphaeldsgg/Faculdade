package com.example.sanduicheria;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Adicionando anotação à nossa classe Dao
@androidx.room.Dao

public interface Dao {

    // o método abaixo é usado para adicionar dados ao banco de dados.
    @Insert
    void insert(SanduicheModal model);

    // o método abaixo é usado para atualizar os dados em nosso banco de dados.
    @Update
    void update(SanduicheModal model);

    // a linha abaixo é usada para deletar um sanduíche específico em nosso banco de dados.
    @Delete
    void delete(SanduicheModal model);

    // na linha abaixo estamos fazendo uma consulta para deletar todos os sanduíches de nosso banco de dados.
    @Query("DELETE FROM sanduiche_table")
    void deleteAllSanduiches();

    // a linha abaixo é para ler todos os sanduíches de nosso banco de dados.
    // Na linha, estamos ordenando nossos sanduíches em ordem crescente com o nome do nosso sanduíche.
    @Query("SELECT * FROM sanduiche_table ORDER BY sanduicheName ASC")
    LiveData<List<SanduicheModal>> getAllSanduiches();

}
