package br.com.zup.filmedesafio.android.view.inicio.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.com.zup.filmedesafio.android.database.FilmesDbHelper;
import br.com.zup.filmedesafio.android.database.FilmesReaderContract;
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
        view.populaLista(filmes);
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void pegaFilmesSalvos() {
        FilmesDbHelper dbHelper = new FilmesDbHelper(view.getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {
                FilmesReaderContract.Filme.imdbid,
                FilmesReaderContract.Filme.title,
                FilmesReaderContract.Filme.type,
                FilmesReaderContract.Filme.director,
                FilmesReaderContract.Filme.year,
                FilmesReaderContract.Filme.runtime,
                FilmesReaderContract.Filme.poster
        };
        String sort = FilmesReaderContract.Filme.title + " ASC";
        Cursor cursor = db.query(
                FilmesReaderContract.Filme.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sort
        );
        ArrayList<FilmeModel> filmes = new ArrayList<>();
        while (cursor.moveToNext()) {
            filmes.add(new FilmeModel(cursor));//cursor.getInt(cursor.getColumnIndexOrThrow(FilmesReaderContract.Filme.COLUMN_ID)),
            //cursor.getString(cursor.getColumnIndexOrThrow(FilmesReaderContract.Filme.COLUMN_NOME))));
        }
        view.populaImagens(filmes);
        cursor.close();
        dbHelper.close();
    }

    @Override
    public void addFilme(FilmeModel filme, Context context) {
        view.addFilme(filme);
        Log.d("MAINPRESENTER", filme.getPoster());
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
    public void remFilmeShow(FilmeModel filme, Context context) {
        view.remFilme(filme);
        FilmesDbHelper dbHelper = new FilmesDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String where = FilmesReaderContract.Filme.imdbid + " = ?";
        String[] args = {String.valueOf(filme.getImdbID())};
        db.delete(FilmesReaderContract.Filme.TABLE_NAME, where, args);
    }
}
