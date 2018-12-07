package br.com.zup.filmedesafio.android.view.inicio.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.zup.filmedesafio.android.R;
import br.com.zup.filmedesafio.android.databinding.ActivityMainBinding;
import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.model.SearchModel;
import br.com.zup.filmedesafio.android.view.detalhe.activity.DetalheActivity;
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

    //private DiscreteScrollView scrImages;
    private CarouselView carouselView;
    private RecyclerView lstFilmes;
    private TextView txtInfoFilmes;
    private ConstraintLayout clFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new MainPresenter(this, new MainInteractor());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.tit_filmes_salvos);
        }
        carouselView = findViewById(R.id.carousel);
        txtInfoFilmes = findViewById(R.id.txt_info_filmes);
        lstFilmes = findViewById(R.id.lst_filmes);
        clFilmes = findViewById(R.id.cl_filmes);

        findViewById(R.id.img_rem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.remFilme(filme, MainActivity.this);
                presenter.pegaFilmesSalvos();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carouselView.setImageListener(null);
        presenter.pegaFilmesSalvos();
        if (searchView == null || searchView.isIconified()) {

            mostraImagens();
        } else {
            mostraLista();
            if(searchView.getQuery().length() >= 3) presenter.getFilmes(searchView.getQuery().toString());
        }
    }

    @Override
    public void populaImagens(ArrayList<FilmeModel> filmes) {
        this.filmes = filmes;

        mostraImagens();
        if(filmes.size() > 0) {
            atualizaLista();
            carouselView.setPageCount(filmes.size());
            atualizaInformacoes(filmes.get(0));
            carouselView.setImageClickListener(new ImageClickListener() {
                @Override
                public void onClick(int position) {
                    Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
                    intent.putExtra("IMDB", filme.getImdbID());
                    intent.putExtra("TITLE", filme.getTitle());
                    intent.putExtra("SALVO", true);
                    startActivity(intent);
                }
            });
            carouselView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    if(filmes.size() > i)
                        atualizaInformacoes(filmes.get(i));
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
        }
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
            mostraLista();
            if (getSupportActionBar() != null) getSupportActionBar().setTitle("");
        });

        searchView.setOnCloseListener(() -> {
            //setContentView(R.layout.activity_main);
            lstFilmes.setAdapter(null);
            presenter.pegaFilmesSalvos();
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

    private void mostraImagens() {
        lstFilmes.setVisibility(View.GONE);

        if(filmes.size() > 0) {
            clFilmes.setVisibility(View.VISIBLE);
            txtInfoFilmes.setVisibility(View.GONE);
        } else {
            clFilmes.setVisibility(View.GONE);
            txtInfoFilmes.setVisibility(View.VISIBLE);
        }

    }

    private void mostraLista() {
        lstFilmes.setVisibility(View.VISIBLE);
        txtInfoFilmes.setVisibility(View.GONE);
        clFilmes.setVisibility(View.GONE);
    }

    @Override
    public void atualizaInformacoes(FilmeModel filme) {

        this.filme = filme;
        binding.setFilme(filme);

    }

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
                presenter.remFilme(f, MainActivity.this);
                adapter.notifyDataSetChanged();
                return;
            }
        }
        presenter.addFilme(filme, MainActivity.this);
    }

    @Override
    public void onListFragmentInteraction(FilmeModel filme, boolean saved) {
        Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
        intent.putExtra("IMDB", filme.getImdbID());
        intent.putExtra("TITLE", filme.getTitle());
        intent.putExtra("SALVO", saved);
        startActivity(intent);
    }

    @Override
    public void addFilme(FilmeModel filme) {
        filmes.add(filme);
    }

    @Override
    public void remFilme(FilmeModel filme) {
        filmes.remove(filme);
    }

    private void atualizaLista() {
        carouselView.setImageListener(null);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                if (filmes.get(position).getPoster().isEmpty())
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_placeholder));
                Picasso.get().load(filmes.get(position).getPoster()).into(imageView);
            }
        });
    }
}
