package br.com.zup.filmedesafio.android.view.inicio.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.zup.filmedesafio.android.R;
import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.network.RetrofitInstance;
import br.com.zup.filmedesafio.android.view.detalhe.activity.DetalheActivity;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class ImageFilmesAdapter extends RecyclerView.Adapter<ImageFilmesAdapter.ViewHolder> {

    private ArrayList<FilmeModel> filmes;

    public ImageFilmesAdapter(ArrayList<FilmeModel> filmes) {
        Log.d("IMAGEFILMESADAPTER", filmes.size() + " - TAMANHO");
        this.filmes = filmes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_image_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.filme = filmes.get(position);
        Log.d("IMAGEFILMESADAPTER", holder.filme.getPoster());
        //Picasso.get().load(holder.filme.getPoster()).into(holder.image);
        /*Glide.with(holder.itemView.getContext())
                .load(holder.filme.getPoster())
                .into(holder.image);*/
    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FilmeModel filme;
        private ImageView image;
        private CardView crdItem;

        ViewHolder(final View itemView) {
            super(itemView);
            crdItem = itemView.findViewById(R.id.crd_item);
            image = itemView.findViewById(R.id.image);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetalheActivity.class);
                    intent.putExtra("ID", filme.getImdbID());
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        public ImageView getImage() {
            return image;
        }

        public CardView getCrdItem() {
            return crdItem;
        }
    }
}
