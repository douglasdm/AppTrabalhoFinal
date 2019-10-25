package com.example.apptrabalhofinal.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.apptrabalhofinal.R;
import com.example.apptrabalhofinal.data.dao.AtividadeDAO;
import com.example.apptrabalhofinal.data.dao.AtividadeDBMemoriaDAO;
import com.example.apptrabalhofinal.data.model.Atividade;
import com.example.apptrabalhofinal.ui.adapter.MinhaAtividadeAdapter;

import java.util.ArrayList;

public class BuscarAtividadeListaActivity extends AppCompatActivity {

    AtividadeDAO atividadeDAO = AtividadeDBMemoriaDAO.getInstance();
    private Toolbar myToolbar;
    private ListView listViewMinhasAtividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_atividade_lista);

        myToolbar = findViewById(R.id.minhaToolbar);

        listViewMinhasAtividades = findViewById(R.id.lista_view_todas_atividades);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Busca");

        listViewMinhasAtividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                abriItem(i);
            }
        });
    }


    public void abriItem(int itemSelecionado) {
        Bundle bundle = getIntent().getExtras();
        String email = "a";
        if(bundle!=null) {
            email = bundle.getString("email");
        }
        if (listarTodasAtividades().size() > 0) {
            Intent intent = new Intent(this, ParticiparAtividadeActivity.class);
            Atividade atividade = listarTodasAtividades().get(itemSelecionado);
            intent.putExtra("id", atividade.getId());
            intent.putExtra("email", email);
            startActivity(intent);
        }
    }

    public ArrayList<Atividade> listarTodasAtividades(){
        return atividadeDAO.listarAtividadesTodos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listViewMinhasAtividades.setAdapter(new MinhaAtividadeAdapter(this,listarTodasAtividades()));
    }


}
