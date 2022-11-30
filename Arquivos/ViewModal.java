package com.example.sanduicheria;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {

    // criando uma nova variável para o repositório do sanduíche.
    private SanduicheRepository repository;

    // a linha abaixo é para criar uma variável de
    // dados onde todos os sanduíches estão presentes.
    private LiveData<List<SanduicheModal>> allSanduiches;

    // construtor para nosso modo de exibição.
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new SanduicheRepository(application);
        allSanduiches = repository.getAllSanduiches();
    }

    // o método abaixo é usado para inserir os dados em nosso repositório.
    public void insert(SanduicheModal model) {
        repository.insert(model);
    }

    // a linha abaixo é para atualizar os dados em nosso repositório.
    public void update(SanduicheModal model) {
        repository.update(model);
    }

    // a linha abaixo é para deletar os dados em nosso repositório.
    public void delete(SanduicheModal model) {
        repository.delete(model);
    }

    // o método abaixo é excluir todos os sanduíches de nossa lista.
    public void deleteAllSanduiches() {
        repository.deleteAllSanduiches();
    }

    // o método abaixo é obter todos os sanduíches de nossa lista.
    public LiveData<List<SanduicheModal>> getAllSanduiches() {
        return allSanduiches;
    }
}