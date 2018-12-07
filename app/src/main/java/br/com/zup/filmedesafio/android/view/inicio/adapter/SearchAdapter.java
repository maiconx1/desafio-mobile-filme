package br.com.zup.filmedesafio.android.view.inicio.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.zup.filmedesafio.android.R;
import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.view.inicio.contract.MainContract;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Criado por Maicon Dias Castro em 06/12/2018.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private final ArrayList<FilmeModel> filmes;
    private final ArrayList<FilmeModel> salvos;
    private final MainContract.view.OnListFragmentInteractionListener listener;
    private Context context;

    public SearchAdapter(ArrayList<FilmeModel> items, MainContract.view.OnListFragmentInteractionListener listener, ArrayList<FilmeModel> salvos) {
        filmes = items;
        this.salvos = salvos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_list_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.filme = filmes.get(position);
        Picasso.get().load(holder.filme.getPoster()).into(holder.imgItem);
        holder.txtItem.setText(holder.filme.getTitle());

        holder.imgAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onListFragmentAction(holder.filme, SearchAdapter.this);
                    notifyDataSetChanged();
                }
            }
        });

        holder.clFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onListFragmentInteraction(holder.filme, holder.saved);
                }
            }
        });

        holder.imgAction.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_save_white_24dp));
        holder.saved = false;
        for (FilmeModel filme : salvos) {
            if (holder.filme.getImdbID().equals(filme.getImdbID()) && holder.filme.getTitle().equals(filme.getTitle())) {
                holder.imgAction.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_delete_white_24dp));
                holder.saved = true;
            }
        }
    }

    @Override
    public int getItemCount() {
        if(filmes == null) return 0;
        return filmes.size();
    }

    public ArrayList<FilmeModel> getFilmes() {
        return filmes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cl_filme)
        ConstraintLayout clFilme;
        @BindView(R.id.img_item)
        ImageView imgItem;
        @BindView(R.id.txt_item)
        TextView txtItem;
        @BindView(R.id.img_action)
        ImageView imgAction;

        boolean saved = false;

        FilmeModel filme;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + txtItem.getText() + "'";
        }
    }
}
