package br.com.zup.filmedesafio.android.view.inicio.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

import android.os.Handler;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import br.com.zup.filmedesafio.android.R;
import br.com.zup.filmedesafio.android.databinding.ActivityMainBinding;
import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.model.SearchModel;
import br.com.zup.filmedesafio.android.view.detalhe.activity.DetalheActivity;
import br.com.zup.filmedesafio.android.view.inicio.adapter.ImageFilmesAdapter;
import br.com.zup.filmedesafio.android.view.inicio.adapter.SearchAdapter;
import br.com.zup.filmedesafio.android.view.inicio.contract.MainContract;
import br.com.zup.filmedesafio.android.view.inicio.interactor.MainInteractor;
import br.com.zup.filmedesafio.android.view.inicio.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.view, MainContract.view.OnListFragmentInteractionListener {

    private ArrayList<FilmeModel> filmes;
    public FilmeModel filme;
    private MainContract.presenter presenter;
    private SearchView searchView;
    private ActivityMainBinding binding;

    private DiscreteScrollView scrImages;
    private RecyclerView lstFilmes;
    private ImageFilmesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new MainPresenter(this, new MainInteractor());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.tit_filmes_salvos);
        }

        scrImages = findViewById(R.id.scr_images);

        //filme = new FilmeModel("tt871234", "Batman", "Christopher", "15 Jun 2005", "140 min", "movie");
        //binding.setFilme(filme);

        findViewById(R.id.img_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.remFilmeShow(filme, MainActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (searchView == null || searchView.isIconified()) {
            Log.d("MAINACTIVITY", "pegafilmessalvos");
            presenter.pegaFilmesSalvos();
        } else {
            searchView.setQuery(searchView.getQuery(), true);
        }
    }

    @Override
    public void populaImagens(ArrayList<FilmeModel> filmes) {
        Log.d("MAINACTIVITY", filmes.size() + "//");
        this.filmes = filmes;
        scrImages.addScrollStateChangeListener(listener);
        adapter = new ImageFilmesAdapter(filmes);
        scrImages.setAdapter(adapter);
        scrImages.setOffscreenItems(0);
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

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnSearchClickListener(v -> {
            setContentView(R.layout.activity_search);
            lstFilmes = findViewById(R.id.lst_filmes);
            if (getSupportActionBar() != null) getSupportActionBar().setTitle("");
        });

        searchView.setOnCloseListener(() -> {
            setContentView(R.layout.activity_main);
            lstFilmes.setAdapter(null);
            lstFilmes = null;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onResume();
                }
            }, 500);
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(getString(R.string.tit_filmes_salvos));
            return false;
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length() < 3) {
                    Toast.makeText(MainActivity.this, R.string.err_mais_de_tres, Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() >= 3) {
                    presenter.getFilmes(s);
                }
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
            viewHolder.getCrdItem().setScaleX(1.3f);
            viewHolder.getCrdItem().setScaleY(1.3f);
            atualizaInformacoes(filmes.get(position));
        }

        @Override
        public void onScroll(float v, int i, int i1, @Nullable ImageFilmesAdapter.ViewHolder viewHolder, @Nullable ImageFilmesAdapter.ViewHolder t1) {
        }
    };

    @Override
    public Context getContext() {
        return MainActivity.this;
    }

    @Override
    public void populaLista(SearchModel filmes) {
        SearchAdapter searchAdapter = new SearchAdapter(filmes.getSearch(), this, this.filmes);
        lstFilmes.setAdapter(searchAdapter);
    }

    @Override
    public void onListFragmentAction(FilmeModel filme, RecyclerView.Adapter adapter) {
        for (final FilmeModel f : filmes) {
            if (f.getImdbID().equals(filme.getImdbID()) && f.getTitle().equals(filme.getTitle())) {
                presenter.remFilmeShow(f, MainActivity.this);
                adapter.notifyDataSetChanged();
                return;
            }
        }
        presenter.addFilme(filme, MainActivity.this);
    }

    @Override
    public void onListFragmentInteraction(FilmeModel filme) {
        Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
        intent.putExtra("IMDB", filme.getImdbID());
        intent.putExtra("TITLE", filme.getTitle());
        startActivity(intent);
    }

    @Override
    public void addFilme(FilmeModel filme) {
        filmes.add(filme);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void remFilme(FilmeModel filme) {
        filmes.remove(filme);
        adapter.notifyDataSetChanged();
    }
}
