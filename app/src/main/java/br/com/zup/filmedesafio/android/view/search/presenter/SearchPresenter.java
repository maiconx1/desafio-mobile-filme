package br.com.zup.filmedesafio.android.view.search.presenter;

import android.content.Context;

import br.com.zup.filmedesafio.android.model.SearchModel;
import br.com.zup.filmedesafio.android.view.search.contract.SearchContract;
import br.com.zup.filmedesafio.android.view.search.interactor.SearchInteractor;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class SearchPresenter implements SearchContract.presenter, SearchContract.interactor.OnFinishedListener {
    private SearchInteractor interactor;
    private Context context;

    public SearchPresenter(Context context, SearchInteractor interactor) {
        this.context = context;
        this.interactor = interactor;
    }


    @Override
    public void onFinished(SearchModel showModels) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
