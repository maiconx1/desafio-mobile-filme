package br.com.zup.filmedesafio.android.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Criado por Maicon Dias Castro em 10/11/2018.
 */
public class RetrofitInstance {
    private static Retrofit retrofit;
    public static final String API_KEY = "2a832517";
    public static final String PROTOCOL = "http://";
    public static final String PREFIX_IMG = "img.";
    public static final String BASE_URL = "omdbapi.com/?apikey="+API_KEY;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(String.format("%s%s", PROTOCOL, BASE_URL))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
