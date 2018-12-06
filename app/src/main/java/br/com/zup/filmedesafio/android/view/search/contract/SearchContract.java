package br.com.zup.filmedesafio.android.view.search.contract;

import br.com.zup.filmedesafio.android.model.SearchModel;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class SearchContract {
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
