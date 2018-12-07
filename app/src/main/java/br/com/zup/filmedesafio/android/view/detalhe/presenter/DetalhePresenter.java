package br.com.zup.filmedesafio.android.view.detalhe.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.zup.filmedesafio.android.database.FilmesDbHelper;
import br.com.zup.filmedesafio.android.database.FilmesReaderContract;
import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.view.detalhe.contract.DetalheContract;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class DetalhePresenter implements DetalheContract.presenter, DetalheContract.interactor.OnFinishedListener {
    private final DetalheContract.interactor interactor;
    private final DetalheContract.view view;

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

    @Override
    public void addFilme(FilmeModel filme, Context context) {
        FilmesDbHelper dbHelper = new FilmesDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FilmesReaderContract.Filme.imdbid, filme.getImdbID());
        values.put(FilmesReaderContract.Filme.title, filme.getTitle());
        values.put(FilmesReaderContract.Filme.type, filme.getType());
        values.put(FilmesReaderContract.Filme.director, filme.getDirector());
        values.put(FilmesReaderContract.Filme.poster, filme.getPoster());
        values.put(FilmesReaderContract.Filme.year, filme.getYear());
        values.put(FilmesReaderContract.Filme.runtime, filme.getRuntime());
        db.insert(FilmesReaderContract.Filme.TABLE_NAME, null, values);
    }

    @Override
    public void remFilme(FilmeModel filme, Context context) {
        FilmesDbHelper dbHelper = new FilmesDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String where = FilmesReaderContract.Filme.imdbid + " = ?";
        String[] args = {String.valueOf(filme.getImdbID())};
        db.delete(FilmesReaderContract.Filme.TABLE_NAME, where, args);
    }
}
