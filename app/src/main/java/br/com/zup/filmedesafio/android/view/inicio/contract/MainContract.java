package br.com.zup.filmedesafio.android.view.inicio.contract;

import java.util.ArrayList;

import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.model.SearchModel;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class MainContract {
    public interface view {

        void populaImagens(SearchModel filmes);

        void atualizaInformacoes(FilmeModel filme);
    }

    public interface presenter {

        void getFilmes(String filtro);
    }

    public interface interactor {
        void getFilmes(OnFinishedListener listener, String filtro);

        interface OnFinishedListener {
            void onFinished(SearchModel showModels);
            void onFailure(Throwable t);
        }

    }
}
