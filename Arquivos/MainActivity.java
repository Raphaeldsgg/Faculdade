package com.example.sanduicheria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // criando variáveis para nossa visão recicladora.
    private RecyclerView sanduichesRV;
    private static final int ADD_SANDUICHE_REQUEST = 1;
    private static final int EDIT_SANDUICHE_REQUEST = 2;
    private ViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// inicializando nossa variável para nossa visão recicladora e fab.
    sanduichesRV = findViewById(R.id.idSanduiches);
    FloatingActionButton fab = findViewById(R.id.idBotaoAdd);

        // adicionando ouvinte ao clicar para botão de ação flutuante.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // iniciar uma nova atividade para adicionar um novo sanduíche e passar um valor constante nele.
                Intent intent = new Intent(MainActivity.this, NewSanduicheActivity.class);
                startActivityForResult(intent, ADD_SANDUICHE_REQUEST);
            }
        });

        // setting layout manager to our adapter class.
        sanduichesRV.setLayoutManager(new LinearLayoutManager(this));
        sanduichesRV.setHasFixedSize(true);

        // adaptador de inicialização para visualização do reciclador.
        final SanduicheRVAdapter adapter = new SanduicheRVAdapter();

        // configurando a classe do adaptador para visualização do reciclador.
        sanduichesRV.setAdapter(adapter);

        // passando um dado do modo de exibição.
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);

        // a linha abaixo é usada para obter todos os sanduíches do modal de exibição.
        viewmodal.getAllSanduiches().observe(this, new Observer<List<SanduicheModal>>() {

            @Override
            public void onChanged(List<SanduicheModal> models) {
                // quando os dados são alterados em nossos modelos,
                // estamos adicionando essa lista à nossa classe de adaptador.
                adapter.submitList(models);
            }
        });

        // o método abaixo é usado para adicionar o método de deslizar para excluir para o item da visualização do reciclador.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // na visualização do reciclador, o item deslocado, então estamos excluindo o item de nossa visualização do reciclador.
                viewmodal.delete(adapter.getSanduicheAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Sanduíche deletado.", Toast.LENGTH_SHORT).show();
            }
        }).

        // a linha abaixo é usada para anexar isso à visualização do reciclador.
        attachToRecyclerView(sanduichesRV);
        // a linha abaixo é usada para definir o ouvinte de clique de item para nosso item de visualização do reciclador.
        adapter.setOnItemClickListener(new SanduicheRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SanduicheModal model) {
                // após clicar no item da visualização do reciclador,
                // estamos abrindo uma nova atividade e passando dados para a nossa atividade.
                Intent intent = new Intent(MainActivity.this, NewSanduicheActivity.class);
                intent.putExtra(NewSanduicheActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewSanduicheActivity.EXTRA_SANDUICHE_NAME, model.getSanduicheName());
                intent.putExtra(NewSanduicheActivity.EXTRA_DESCRIPTION, model.getSanduicheDescription());
                intent.putExtra(NewSanduicheActivity.EXTRA_TYPE, model.getSanduicheType());

                // a linha abaixo é para iniciar uma nova atividade e adicionar uma constante de edição de sanduíche.
                startActivityForResult(intent, EDIT_SANDUICHE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_SANDUICHE_REQUEST && resultCode == RESULT_OK) {
            String sanduicheName = data.getStringExtra(NewSanduicheActivity.EXTRA_SANDUICHE_NAME);
            String sanduicheDescription = data.getStringExtra(NewSanduicheActivity.EXTRA_DESCRIPTION);
            String sanduicheType = data.getStringExtra(NewSanduicheActivity.EXTRA_TYPE);
            SanduicheModal model = new SanduicheModal(sanduicheName, sanduicheDescription, sanduicheType);
            viewmodal.insert(model);
            Toast.makeText(this, "Sanduíche salvo.", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_SANDUICHE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewSanduicheActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Sanduíche não atualizado.", Toast.LENGTH_SHORT).show();
                return;
            }
            String sanduicheName = data.getStringExtra(NewSanduicheActivity.EXTRA_SANDUICHE_NAME);
            String sanduicheDesc = data.getStringExtra(NewSanduicheActivity.EXTRA_DESCRIPTION);
            String sanduicheType = data.getStringExtra(NewSanduicheActivity.EXTRA_TYPE);
            SanduicheModal model = new SanduicheModal(sanduicheName, sanduicheDesc, sanduicheType);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Sanduíche atualizado.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sanduíche não salvo.", Toast.LENGTH_SHORT).show();
        }
    }
}
