package br.com.zup.filmedesafio.android.view.detalhe.interactor;

import java.util.ArrayList;

import android.support.annotation.NonNull;
import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.network.RetrofitInstance;
import br.com.zup.filmedesafio.android.service.FilmesService;
import br.com.zup.filmedesafio.android.view.detalhe.contract.DetalheContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class DetalheInteractor implements DetalheContract.interactor {

    @Override
    public void getFilmeId(final OnFinishedListener onFinishedListener, String imdbId) {
        FilmesService filmesService = RetrofitInstance.getRetrofitInstance().create(FilmesService.class);
        Call<FilmeModel> call = filmesService.getFilmeId(RetrofitInstance.API_KEY, imdbId);
        call.enqueue(new Callback<FilmeModel>() {
            @Override
            public void onResponse(@NonNull Call<FilmeModel> call, @NonNull Response<FilmeModel> response) {
                if (response.code() == 200) {
                    onFinishedListener.onFinished(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<FilmeModel> call, @NonNull Throwable t) {
                t.printStackTrace();
                onFinishedListener.onFailure(t);
            }
        });
    }

    @Override
    public void getFilmeTitle(final OnFinishedListener onFinishedListener, String title) {
        FilmesService filmesService = RetrofitInstance.getRetrofitInstance().create(FilmesService.class);
        Call<FilmeModel> call = filmesService.getFilmeTitle(RetrofitInstance.API_KEY, title);
        call.enqueue(new Callback<FilmeModel>() {
            @Override
            public void onResponse(@NonNull Call<FilmeModel> call, @NonNull Response<FilmeModel> response) {
                if (response.code() == 200) {
                    onFinishedListener.onFinished(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<FilmeModel> call, @NonNull Throwable t) {
                t.printStackTrace();
                onFinishedListener.onFailure(t);
            }
        });
    }
}
