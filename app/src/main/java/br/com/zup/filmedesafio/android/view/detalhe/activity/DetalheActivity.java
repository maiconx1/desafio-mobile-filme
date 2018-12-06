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

public class DetalheActivity extends AppCompatActivity implements DetalheContract.view{
    private ActivityDetalheBinding binding;
    private DetalheContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhe);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.tit_detalhes);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        presenter = new DetalhePresenter(this, new DetalheInteractor());
        Intent intent = getIntent();
        String id = "";
        String title = "";
        if(intent != null) {
            id = intent.getStringExtra("IMDB");
            title = intent.getStringExtra("TITLE");
        }
        if(!id.isEmpty()) {
            presenter.getFilmeId(id);
        }
        else {
            presenter.getFilmeTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detalhe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void atualizaInformacoes(FilmeModel filme) {
        binding.setFilme(filme);
        Picasso.get().load(filme.getPoster()).into((ImageView)findViewById(R.id.img_filme));
    }
}
