package br.com.zup.filmedesafio.android.view.inicio.activity;

import android.app.SearchManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import br.com.zup.filmedesafio.android.R;
import br.com.zup.filmedesafio.android.databinding.ActivityMainBinding;
import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.model.SearchModel;
import br.com.zup.filmedesafio.android.view.inicio.adapter.ImageFilmesAdapter;
import br.com.zup.filmedesafio.android.view.inicio.contract.MainContract;
import br.com.zup.filmedesafio.android.view.inicio.interactor.MainInteractor;
import br.com.zup.filmedesafio.android.view.inicio.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.view {

    private ArrayList<FilmeModel> filmes;
    public FilmeModel filme;
    private MainContract.presenter presenter;
    private SearchView searchView;
    private ActivityMainBinding binding;

    private DiscreteScrollView srcImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new MainPresenter(this, new MainInteractor());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.tit_filmes_salvos);
        }

        srcImages = findViewById(R.id.scr_images);
        presenter.getFilmes("batman");

        filme = new FilmeModel("Batman", "Christopher", "15 Jun 2005", "140 min", "movie");
        binding.setFilme(filme);
    }

    @Override
    public void populaImagens(SearchModel filmes) {
        Log.d("MAINACTIVITY", "POPULAIMAGENS");
        this.filmes = filmes.getSearch();
        srcImages.addScrollStateChangeListener(listener);
        srcImages.setAdapter(new ImageFilmesAdapter(filmes.getSearch()));
        srcImages.setOffscreenItems(0);
    }

    @Override
    public void onBackPressed() {
        if (searchView.isIconified()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_search);
                if (getSupportActionBar() != null) getSupportActionBar().setTitle("");
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setContentView(R.layout.activity_main);
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle(getString(R.string.tit_filmes_salvos));
                return false;
            }
        });
        return true;
    }

    @Override
    public void atualizaInformacoes(FilmeModel filme) {
        Log.d("MAINACTIVITY", "ATUALIZA INFORMACOES");
        this.filme = filme;
        binding.setFilme(filme);
        Log.d("MAINACTIVITY", filme.toString());
    }

    DiscreteScrollView.ScrollStateChangeListener<ImageFilmesAdapter.ViewHolder> listener = new DiscreteScrollView.ScrollStateChangeListener<ImageFilmesAdapter.ViewHolder>() {
        @Override
        public void onScrollStart(@NonNull ImageFilmesAdapter.ViewHolder viewHolder, int i) {
            viewHolder.getCrdItem().setScaleX(1);
            viewHolder.getCrdItem().setScaleY(1);
        }

        @Override
        public void onScrollEnd(@NonNull ImageFilmesAdapter.ViewHolder viewHolder, int position) {
            viewHolder.getCrdItem().setScaleX(1.2f);
            viewHolder.getCrdItem().setScaleY(1.2f);
            atualizaInformacoes(filmes.get(position));
        }

        @Override
        public void onScroll(float v, int i, int i1, @Nullable ImageFilmesAdapter.ViewHolder viewHolder, @Nullable ImageFilmesAdapter.ViewHolder t1) {
        }
    };
}
