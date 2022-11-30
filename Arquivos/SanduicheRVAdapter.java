package com.example.sanduicheria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SanduicheRVAdapter extends ListAdapter<SanduicheModal, SanduicheRVAdapter.ViewHolder> {

    // criando uma variável para o ouvinte de clique no item.
    private OnItemClickListener listener;

    // criando uma classe de construtor para nossa classe de adaptador.
    SanduicheRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // criando um retorno de chamada para o item da visualização do reciclador.
    private static final DiffUtil.ItemCallback<SanduicheModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<SanduicheModal>() {
        @Override
        public boolean areItemsTheSame(SanduicheModal oldItem, SanduicheModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(SanduicheModal oldItem, SanduicheModal newItem) {
            // a linha abaixo é para verificar o nome do sanduíche, descrição e tipo de sanduíche.
            return oldItem.getSanduicheName().equals(newItem.getSanduicheName()) && oldItem.getSanduicheDescription().equals(newItem.getSanduicheDescription()) && oldItem.getSanduicheType().equals(newItem.getSanduicheType());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // a linha abaixo é usada para aumentar nosso arquivo de layout
        // para cada item de nossa visualização do reciclador.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sanduiche_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // a linha de código abaixo é usada para
        // definir dados para cada item de nossa visão do reciclador.
        SanduicheModal model = getSanduicheAt(position);
        holder.sanduicheNameTV.setText(model.getSanduicheName());
        holder.sanduicheDescTV.setText(model.getSanduicheDescription());
        holder.sanduicheTypeTV.setText(model.getSanduicheType());
    }

    // criando um método para obter o modal do sanduíche para uma posição específica.
    public SanduicheModal getSanduicheAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // classe portadora de visão para criar uma variável para cada visão.
        TextView sanduicheNameTV, sanduicheDescTV, sanduicheTypeTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // inicializando cada visão de nossa visão recicladora.
            sanduicheNameTV = itemView.findViewById(R.id.idTVNomeSanduiches);
            sanduicheDescTV = itemView.findViewById(R.id.idTVDescricaoSanduiche);
            sanduicheTypeTV = itemView.findViewById(R.id.idTVTipoSanduiche);

            // adicionando ouvinte de clique para cada item da visualização do reciclador.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // dentro do listener do clique, estamos passando a posição para
                    // o nosso item de visualização do reciclador.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SanduicheModal model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}