package br.com.zup.filmedesafio.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class SearchModel {

    @Expose
    @SerializedName("Response")
    private String Response;
    @Expose
    @SerializedName("totalResults")
    private String totalResults;
    @Expose
    @SerializedName("Search")
    private ArrayList<FilmeModel> Search;

    public String getResponse() {
        return Response;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public ArrayList<FilmeModel> getSearch() {
        return Search;
    }
}
