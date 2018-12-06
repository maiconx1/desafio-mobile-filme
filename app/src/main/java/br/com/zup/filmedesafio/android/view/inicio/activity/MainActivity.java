package br.com.zup.filmedesafio.android.view.inicio.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import br.com.zup.filmedesafio.android.R;
import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.model.SearchModel;
import br.com.zup.filmedesafio.android.view.inicio.adapter.ImageFilmesAdapter;
import br.com.zup.filmedesafio.android.view.inicio.contract.MainContract;
import br.com.zup.filmedesafio.android.view.inicio.interactor.MainInteractor;
import br.com.zup.filmedesafio.android.view.inicio.presenter.MainPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.view {

    private ArrayList<FilmeModel> filmes;
    private MainContract.presenter presenter;
    private SearchView searchView;

    @BindView(R.id.scr_images)
    DiscreteScrollView srcImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this, new MainInteractor());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.tit_filmes_salvos);
        }

        presenter.getFilmes("batman");

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