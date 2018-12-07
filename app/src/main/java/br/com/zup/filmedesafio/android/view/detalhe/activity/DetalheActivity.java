package br.com.zup.filmedesafio.android.view.detalhe.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.zup.filmedesafio.android.R;
import br.com.zup.filmedesafio.android.databinding.ActivityDetalheBinding;
import br.com.zup.filmedesafio.android.model.FilmeModel;
import br.com.zup.filmedesafio.android.view.detalhe.contract.DetalheContract;
import br.com.zup.filmedesafio.android.view.detalhe.interactor.DetalheInteractor;
import br.com.zup.filmedesafio.android.view.detalhe.presenter.DetalhePresenter;

public class DetalheActivity extends AppCompatActivity implements DetalheContract.view {
    private ActivityDetalheBinding binding;
    private DetalheContract.presenter presenter;
    private boolean salvo = false;
    private Menu menu;
    private FilmeModel filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhe);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.tit_detalhes);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        presenter = new DetalhePresenter(this, new DetalheInteractor());
        Intent intent = getIntent();
        String id = "";
        String title = "";
        if (intent != null) {
            id = intent.getStringExtra("IMDB");
            title = intent.getStringExtra("TITLE");
            salvo = intent.getBooleanExtra("SALVO", false);
        }
        if (!id.isEmpty()) {
            presenter.getFilmeId(id);
        } else {
            presenter.getFilmeTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detalhe_menu, menu);
        if (salvo) {
            menu.getItem(0).setIcon(R.drawable.ic_delete_white_24dp);
        } else {
            menu.getItem(0).setIcon(R.drawable.ic_save_white_24dp);
        }
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            case R.id.menu_salvar:
                if (salvo) {
                    presenter.remFilme(filme, DetalheActivity.this);
                    menu.getItem(0).setIcon(R.drawable.ic_save_white_24dp);
                    salvo = false;
                    break;
                }
                presenter.addFilme(filme, DetalheActivity.this);
                salvo = true;
                menu.getItem(0).setIcon(R.drawable.ic_delete_white_24dp);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void atualizaInformacoes(FilmeModel filme) {
        this.filme = filme;
        binding.setFilme(filme);
        Picasso.get().load(filme.getPoster()).into((ImageView) findViewById(R.id.img_filme));
    }
}