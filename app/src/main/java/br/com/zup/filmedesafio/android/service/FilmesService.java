package br.com.zup.filmedesafio.android.service;

import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.model.SearchModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public interface FilmesService {
    @GET("/")
    Call<SearchModel> getFilmes(@Query("apikey") String key, @Query("s") String filtro);

    @GET("/")
    Call<FilmeModel> getFilmeId(@Query("apikey") String key, @Query("i") String imdbId);

    @GET("/")
    Call<FilmeModel> getFilmeTitle(@Query("apikey") String key, @Query("t") String title);
}
