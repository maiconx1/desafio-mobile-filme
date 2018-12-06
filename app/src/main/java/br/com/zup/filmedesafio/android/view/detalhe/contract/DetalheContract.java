package br.com.zup.filmedesafio.android.view.detalhe.contract;

import java.util.ArrayList;

import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.model.SearchModel;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class DetalheContract {
    public interface view {
    }

    public interface presenter {
    }

    public interface interactor {

        interface OnFinishedListener {
            void onFinished(SearchModel showModels);
            void onFailure(Throwable t);
        }

    }
}
