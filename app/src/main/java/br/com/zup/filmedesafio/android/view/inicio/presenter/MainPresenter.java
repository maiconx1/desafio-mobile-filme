package br.com.zup.filmedesafio.android.view.inicio.presenter;

import java.util.ArrayList;

import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.model.SearchModel;
import br.com.zup.filmedesafio.android.view.inicio.contract.MainContract;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class MainPresenter implements MainContract.presenter, MainContract.interactor.OnFinishedListener {
    private MainContract.interactor interactor;
    private MainContract.view view;

    public MainPresenter(MainContract.view view, MainContract.interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }
    @Override
    public void getFilmes(String filtro) {
        interactor.getFilmes(this, filtro);
    }

    @Override
    public void onFinished(SearchModel filmes) {
        view.populaImagens(filmes);
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
    }
}
