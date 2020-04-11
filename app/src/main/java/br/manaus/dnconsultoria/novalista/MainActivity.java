package br.manaus.dnconsultoria.novalista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    protected List<Planeta> planetas;
    protected PlanetaAdapter adapter;
    private final static String LOG = "MainActivity |";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG,"Inicio...");
        setContentView(R.layout.activity_exemplo_recycler_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setHasFixedSize(true);

        planetas = Planeta.getPlanetas();
        try {
            recyclerView.setAdapter(adapter = new PlanetaAdapter(this, planetas, onClickPlaneta()));
            Log.i(LOG, "Executou o recycler ...");
        } catch (Exception e) {
            Log.e(LOG, "Erro ao executar o recycler > " + e.getMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(LOG, "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_example, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(LOG, "Selecionou item do tipo de layoute");
        int id = item.getItemId();
        if (id == R.id.action_linear_layout){
            recyclerView.setLayoutManager( new LinearLayoutManager(this));
            return  true;
        } else if (id== R.id.action_grid_layout) {
            recyclerView.setLayoutManager( new GridLayoutManager(this,2));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private PlanetaAdapter.PlanetaOnClickListener onClickPlaneta(){
        Log.i(LOG, "Inicio do onClickPlaneja");
        final Intent intent = new Intent(getBaseContext(), PlanetaActivity.class);

        System.out.println(LOG + "Executou o intent chamando o Planeta ");

        return new PlanetaAdapter.PlanetaOnClickListener() {
            @Override
            public void onClickPlaneta(PlanetaAdapter.PlanetasViewHolder holder, int idx) {
                Planeta p = planetas.get(idx);

                ImageView img = holder.img;
                intent.putExtra("imgPlaneta", p.img);
                String key = getString(R.string.transition_key);

                ActivityOptions opts = ActivityOptions.makeSceneTransitionAnimation(getActivity(), img, key);
                try {
                    startActivity(intent, opts.toBundle());
                    Log.i(LOG, "Executou activity chamando planeta");
                } catch (Exception e) {
                    Log.e(LOG, "Erro ao executar ao chamar o Planeta > " + e.getMessage());
                }
            }
        };
    }

    private Activity getActivity() {
        return this;
    }

}
