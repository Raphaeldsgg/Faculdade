package com.example.sanduicheria;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// adicionando anotações para nossas entidades de banco de dados e versão db..
@Database(entities = {SanduicheModal.class}, version = 1)
public abstract class SanduicheDatabase extends RoomDatabase {

    // a linha abaixo é para criar uma instância para nossa classe de banco de dados.
    private static SanduicheDatabase instance;

    // a linha abaixo é para criar uma variável abstrata para dao.
    public abstract Dao Dao();

    // na linha abaixo estamos obtendo instância para nosso banco de dados.
    public static synchronized SanduicheDatabase getInstance(Context context) {

        // abaixo da linha é para verificar se a instância é nula ou não.
        if (instance == null) {
            // se a instância for nula, estamos criando uma nova instância
            instance =
            // para criar uma instância para nosso banco de dados,
            // estamos criando um construtor de banco de dados e passando
            // nossa classe de banco de dados com nosso nome de banco de dados.
            Room.databaseBuilder(context.getApplicationContext(), SanduicheDatabase.class, "sanduiche database")

            // a linha abaixo é usada para adicionar retorno à migração destrutiva para nosso banco de dados.
            .fallbackToDestructiveMigration()

            // a linha abaixo é para adicionar retorno de chamada ao nosso banco de dados.
            .addCallback(roomCallback)

            // a linha abaixo é para construir nosso banco de dados.
            .build();
        }

        // depois de criar uma instância, estamos retornando nossa instância
        return instance;
    }

    // a linha abaixo é para criar um retorno de chamada para nosso banco de dados ROOM.
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // este método é chamado quando o banco de dados é criado e a linha abaixo é para preencher nossos dados.
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // estamos criando uma classe de tarefa assíncrona para realizar a tarefa em segundo plano.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(SanduicheDatabase instance) {
            Dao dao = instance.Dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}