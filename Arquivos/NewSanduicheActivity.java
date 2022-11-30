package com.example.sanduicheria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewSanduicheActivity extends AppCompatActivity {

    // criando as variáveis para o botão e para os edittext.
    private EditText sanduicheNameEdt, sanduicheDescEdt, sanduicheTypeEdt;
    private Button sanduicheBtn;

    // criando uma variável de string constante para
    // nome do sanduíche, descrição e tipo.
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_SANDUICHE_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_SANDUICHE_NAME";
    public static final String EXTRA_DESCRIPTION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_SANDUICHE_DESCRIPTION";
    public static final String EXTRA_TYPE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_SANDUICHE_DURATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sanduiche);

        // inicializando nossas variáveis para cada visão.
        sanduicheNameEdt = findViewById(R.id.idEdtNomeSanduiche);
        sanduicheDescEdt = findViewById(R.id.idEdtDescricaoSanduiche);
        sanduicheTypeEdt = findViewById(R.id.idEdtTipoSanduiche);
        sanduicheBtn = findViewById(R.id.idBtnSaveSanduiche);

        // a linha abaixo é para obter o intent,
        // pois estamos obtendo dados por meio de um intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            //se obtivermos id para nossos dados,
            // estaremos definindo valores para nossos campos de edição de texto.
            sanduicheNameEdt.setText(intent.getStringExtra(EXTRA_SANDUICHE_NAME));
            sanduicheDescEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            sanduicheTypeEdt.setText(intent.getStringExtra(EXTRA_TYPE));
        }

        // adicionando ouvinte de clique para nosso botão Salvar.
        sanduicheBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // obtendo o valor do texto de edittext e validando
                // se os campos de texto estão vazios ou não.
                String sanduicheName = sanduicheNameEdt.getText().toString();
                String sanduicheDesc = sanduicheDescEdt.getText().toString();
                String sanduicheType = sanduicheTypeEdt.getText().toString();

                if (sanduicheName.isEmpty() || sanduicheDesc.isEmpty() || sanduicheType.isEmpty()) {
                    Toast.makeText(NewSanduicheActivity.this, "Entre com os detalhes do sanduíche.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // chamando um método para salvar nosso sanduíche.
                saveSanduiche(sanduicheName, sanduicheDesc, sanduicheType);
            }
        });
    }

    private void saveSanduiche(String sanduicheName, String sanduicheDescription, String sanduicheType) {
        // dentro desse método, passamos todos os dados por meio de um intent.
        Intent data = new Intent();

        // na linha abaixo estamos passando todos os detalhes do nosso sanduíche.
        data.putExtra(EXTRA_SANDUICHE_NAME, sanduicheName);
        data.putExtra(EXTRA_DESCRIPTION, sanduicheDescription);
        data.putExtra(EXTRA_TYPE, sanduicheType);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (id != -1) {
            // na linha abaixo estamos passando nosso id.
            data.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // exibindo uma mensagem toast após adicionar os dados
        Toast.makeText(this, "Sanduíche salvo no banco de dados Room.", Toast.LENGTH_SHORT).show();
    }
}