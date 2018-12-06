package br.com.zup.filmedesafio.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class FilmeModel {


    @Expose
    @SerializedName("Poster")
    private String Poster;
    @Expose
    @SerializedName("Type")
    private String Type;
    @Expose
    @SerializedName("imdbID")
    private String imdbID;
    @Expose
    @SerializedName("Year")
    private String Year;
    @Expose
    @SerializedName("Title")
    private String Title;

    public String getPoster() {
        return Poster;
    }

    public String getType() {
        return Type;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getYear() {
        return Year;
    }

    public String getTitle() {
        return Title;
    }
}
