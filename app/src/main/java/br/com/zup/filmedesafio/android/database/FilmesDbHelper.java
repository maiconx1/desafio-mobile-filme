package br.com.zup.filmedesafio.android.database;

import android.content.Context;
import android.database.sqlite.*;

import static br.com.zup.filmedesafio.android.database.FilmesReaderContract.SQL_CREATE_ENTRIES;
import static br.com.zup.filmedesafio.android.database.FilmesReaderContract.SQL_DELETE_ENTRIES;

/**
 * Criado por Maicon Dias Castro em 10/11/2018.
 */
public class FilmesDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Filme.db";

    public FilmesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
