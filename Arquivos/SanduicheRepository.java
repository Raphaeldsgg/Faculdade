package com.example.sanduicheria;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SanduicheRepository {

    // abaixo da linha é criar uma variável para dao e listar todos os sanduíches.
    private Dao dao;
    private LiveData<List<SanduicheModal>> allSanduiches;

    // criando um construtor para nossas variáveis e passando as variáveis para ele.
    public SanduicheRepository(Application application) {
        SanduicheDatabase database = SanduicheDatabase.getInstance(application);
        dao = database.Dao();
        allSanduiches = dao.getAllSanduiches();
    }

    //criando um método para inserir os dados em nosso banco de dados..
    public void insert(SanduicheModal model) {
        new InsertSanduicheAsyncTask(dao).execute(model);
    }

    // criando um método para atualizar os dados no banco de dados.
    public void update(SanduicheModal model) {
        new UpdateSanduicheAsyncTask(dao).execute(model);
    }

    // criando um método para deletar os dados em nosso banco de dados.
    public void delete(SanduicheModal model) {
        new DeleteSanduicheAsyncTask(dao).execute(model);
    }

    // abaixo está o método para deletar todos os sanduíches.
    public void deleteAllSanduiches() {
        new DeleteAllSanduichesAsyncTask(dao).execute();
    }

    // o método abaixo é ler todos os sanduíches
    public LiveData<List<SanduicheModal>> getAllSanduiches() {
        return allSanduiches;
    }

    // estamos criando um método de tarefa assíncrona para inserir um novo sanduíche.
    private static class InsertSanduicheAsyncTask extends AsyncTask<SanduicheModal, Void, Void> {
        private Dao dao;
        private InsertSanduicheAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(SanduicheModal... model) {
            // a linha abaixo é usada para inserir nosso modal no dao.
            dao.insert(model[0]);
            return null;
        }
    }

    // estamos criando um método de tarefa assíncrona para atualizar nosso sanduíche.
    private static class UpdateSanduicheAsyncTask extends AsyncTask<SanduicheModal, Void, Void> {
        private Dao dao;

        private UpdateSanduicheAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(SanduicheModal... models) {
            // a linha abaixo é usada para atualizar nosso modal no dao.
                dao.update(models[0]);
                return null;
        }
    }

    // estamos criando um método de tarefa assíncrona para excluir o sanduíche.
    private static class DeleteSanduicheAsyncTask extends AsyncTask<SanduicheModal, Void, Void> {
        private Dao dao;

        private DeleteSanduicheAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(SanduicheModal... models) {
            // a linha abaixo é usada para deletar nosso modal de sanduíche no dao.
            dao.delete(models[0]);
            return null;
        }
    }

    // estamos criando um método de tarefa assíncrona para excluir todos os sanduíches.
    private static class DeleteAllSanduichesAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;
        private DeleteAllSanduichesAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // método chamado para deletar todos os sanduíches.
            dao.deleteAllSanduiches();
            return null;
        }
    }
}
