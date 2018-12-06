package br.com.zup.filmedesafio.android.model;

import android.widget.RelativeLayout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Criado por Maicon Dias Castro em 05/12/2018.
 */
public class FilmeModel {

    @Expose
    @SerializedName("Response")
    private String Response;
    @Expose
    @SerializedName("Website")
    private String Website;
    @Expose
    @SerializedName("Production")
    private String Production;
    @Expose
    @SerializedName("BoxOffice")
    private String BoxOffice;
    @Expose
    @SerializedName("DVD")
    private String DVD;
    @Expose
    @SerializedName("Type")
    private String Type;
    @Expose
    @SerializedName("imdbID")
    private String imdbID;
    @Expose
    @SerializedName("imdbVotes")
    private String imdbVotes;
    @Expose
    @SerializedName("imdbRating")
    private String imdbRating;
    @Expose
    @SerializedName("Metascore")
    private String Metascore;
    @Expose
    @SerializedName("Ratings")
    private List<Ratings> Ratings;
    @Expose
    @SerializedName("Poster")
    private String Poster;
    @Expose
    @SerializedName("Awards")
    private String Awards;
    @Expose
    @SerializedName("Country")
    private String Country;
    @Expose
    @SerializedName("Language")
    private String Language;
    @Expose
    @SerializedName("Plot")
    private String Plot;
    @Expose
    @SerializedName("Actors")
    private String Actors;
    @Expose
    @SerializedName("Writer")
    private String Writer;
    @Expose
    @SerializedName("Director")
    private String Director;
    @Expose
    @SerializedName("Genre")
    private String Genre;
    @Expose
    @SerializedName("Runtime")
    private String Runtime;
    @Expose
    @SerializedName("Released")
    private String Released;
    @Expose
    @SerializedName("Rated")
    private String Rated;
    @Expose
    @SerializedName("Year")
    private String Year;
    @Expose
    @SerializedName("Title")
    private String Title;

    public FilmeModel() {

    }

    public FilmeModel(String title, String director, String released, String runtime, String type) {
        Title = title;
        Director = director;
        Released = released;
        Runtime = runtime;
        Type = type;
    }

    public String getResponse() {
        return Response;
    }

    public String getWebsite() {
        return Website;
    }

    public String getProduction() {
        return Production;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public String getDVD() {
        return DVD;
    }

    public String getType() {
        return Type;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getMetascore() {
        return Metascore;
    }

    public List<Ratings> getRatings() {
        return Ratings;
    }

    public String getPoster() {
        return Poster;
    }

    public String getAwards() {
        return Awards;
    }

    public String getCountry() {
        return Country;
    }

    public String getLanguage() {
        return Language;
    }

    public String getPlot() {
        return Plot;
    }

    public String getActors() {
        return Actors;
    }

    public String getWriter() {
        return Writer;
    }

    public String getDirector() {
        return Director;
    }

    public String getGenre() {
        return Genre;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getReleased() {
        return Released;
    }

    public String getRated() {
        return Rated;
    }

    public String getYear() {
        return Year;
    }

    public String getTitle() {
        return Title;
    }

    public static class Ratings {
        @Expose
        @SerializedName("Value")
        private String Value;
        @Expose
        @SerializedName("Source")
        private String Source;

        public String getValue() {
            return Value;
        }

        public String getSource() {
            return Source;
        }
    }

    public String getTitulo() {
        return String.format("%s (%s)", getTitle(), getType());
    }
}
