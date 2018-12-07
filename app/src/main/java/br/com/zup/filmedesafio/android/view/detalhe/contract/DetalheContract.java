package br.com.zup.filmedesafio.android.view.detalhe.contract;

import android.content.Context;

import br.com.zup.filmedesafio.android.model.FilmeModel;

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

        void addFilme(FilmeModel filme, Context context);

        void remFilme(FilmeModel filme, Context context);
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
