package br.com.zup.filmedesafio.android.view.detalhe.contract;

import java.util.ArrayList;

import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.model.SearchModel;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class DetalheContract {
    public interface view {
        void atualizaInformacoes(FilmeModel filme);
    }

    public interface presenter {
        void getFilmeId(String imdbId);

        void getFilmeTitle(String title);
    }

    public interface interactor {

        void getFilmeId(OnFinishedListener onFinishedListener, String imdbId);

        void getFilmeTitle(OnFinishedListener onFinishedListener, String title);

        interface OnFinishedListener {
            void onFinished(FilmeModel filmeModel);
            void onFailure(Throwable t);
        }

    }
}
