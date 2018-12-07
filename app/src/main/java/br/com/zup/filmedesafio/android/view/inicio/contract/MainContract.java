package br.com.zup.filmedesafio.android.view.inicio.contract;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.model.SearchModel;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class MainContract {
    public interface view {

        void populaImagens(ArrayList<FilmeModel> filmes);

        void atualizaInformacoes(FilmeModel filme);

        Context getContext();

        void populaLista(SearchModel filmes);

        void addFilme(FilmeModel filme);

        void remFilme(FilmeModel filme);

        interface OnListFragmentInteractionListener {
            void onListFragmentAction(FilmeModel filme, RecyclerView.Adapter adapter);

            void onListFragmentInteraction(FilmeModel filme);
        }
    }

    public interface presenter {

        void getFilmes(String filtro);

        void pegaFilmesSalvos();

        void addFilme(FilmeModel filme, Context context);

        void remFilmeShow(FilmeModel filme, Context context);
    }

    public interface interactor {
        void getFilmes(OnFinishedListener listener, String filtro);

        interface OnFinishedListener {
            void onFinished(SearchModel showModels);
            void onFailure(Throwable t);
        }

    }
}
