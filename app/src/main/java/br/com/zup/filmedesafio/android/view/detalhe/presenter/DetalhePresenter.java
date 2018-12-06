package br.com.zup.filmedesafio.android.view.detalhe.presenter;

import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.view.detalhe.contract.DetalheContract;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class DetalhePresenter implements DetalheContract.presenter, DetalheContract.interactor.OnFinishedListener {
    private DetalheContract.interactor interactor;
    private DetalheContract.view view;

    public DetalhePresenter(DetalheContract.view view, DetalheContract.interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getFilmeId(String imdbId) {
        //TODO: VERIFICAR INTERNET
        interactor.getFilmeId(this, imdbId);
    }

    @Override
    public void getFilmeTitle(String title) {
        //TODO: VERIFICAR INTERNET
        interactor.getFilmeTitle(this, title);
    }


    @Override
    public void onFinished(FilmeModel filmeModel) {
        view.atualizaInformacoes(filmeModel);
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
    }
}
