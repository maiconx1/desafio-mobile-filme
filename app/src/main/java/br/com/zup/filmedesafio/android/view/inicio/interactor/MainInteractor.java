package br.com.zup.filmedesafio.android.view.inicio.interactor;

import android.support.annotation.NonNull;


import br.com.zup.filmedesafio.android.model.SearchModel;
import br.com.zup.filmedesafio.android.network.RetrofitInstance;
import br.com.zup.filmedesafio.android.service.FilmesService;
import br.com.zup.filmedesafio.android.view.inicio.contract.MainContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class MainInteractor implements MainContract.interactor {

    @Override
    public void getFilmes(final OnFinishedListener onFinishedListener, String filtro) {
        FilmesService filmesService = RetrofitInstance.getRetrofitInstance().create(FilmesService.class);
        Call<SearchModel> call = filmesService.getFilmes(RetrofitInstance.API_KEY, filtro);

        call.enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(@NonNull Call<SearchModel> call, @NonNull Response<SearchModel> response) {
                if (response.code() == 200) {
                    onFinishedListener.onFinished(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchModel> call, @NonNull Throwable t) {
                t.printStackTrace();
                onFinishedListener.onFailure(t);
            }
        });
    }
}
