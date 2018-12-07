package br.com.zup.filmedesafio.android.database;

import android.provider.BaseColumns;

/**
 * Criado por Maicon Dias Castro em 10/11/2018.
 */
public final class FilmesReaderContract {
    private FilmesReaderContract() {
    }

    public static class Filme implements BaseColumns {
        public static final String TABLE_NAME = "filme";
        public static final String title = "title";
        public static final String type = "type";
        public static final String director = "director";
        public static final String year = "year";
        public static final String runtime = "runtime";
        public static final String imdbid = "imdbid";
        public static final String poster = "poster";
    }

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Filme.TABLE_NAME + " (" +
                    Filme.imdbid + " TEXT, " +
                    Filme.title + " TEXT, " +
                    Filme.type + " TEXT, " +
                    Filme.director + " TEXT, " +
                    Filme.runtime + " TEXT, " +
                    Filme.year + " TEXT, " +
                    Filme.poster + " TEXT)";

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Filme.TABLE_NAME;
}
