package br.com.zup.filmedesafio.android.view.detalhe.presenter;

import android.content.Context;

import java.util.ArrayList;

import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.model.SearchModel;
import br.com.zup.filmedesafio.android.view.detalhe.contract.DetalheContract;
import br.com.zup.filmedesafio.android.view.detalhe.interactor.DetalheInteractor;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class DetalhePresenter implements DetalheContract.presenter, DetalheContract.interactor.OnFinishedListener {
    private DetalheInteractor interactor;
    private Context context;

    public DetalhePresenter(Context context, DetalheInteractor interactor) {
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
