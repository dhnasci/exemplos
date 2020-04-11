package br.manaus.dnconsultoria.novalista;

import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Logger;

public class PlanetaActivity extends AppCompatActivity {

    private final static String LOG = "PlanetaActivity |";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println(LOG+"Inicio...");
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_planeta);
            System.out.println(LOG+"Executou o layout");
        } catch (Exception e) {
            Log.e(LOG, "Erro ao executar o layoute");
        }

        int imgPlaneta = 0;
        try {
            imgPlaneta = getIntent().getIntExtra("imgPlaneta", 0);
            System.out.println(LOG + "executou intent");
        } catch (Exception e) {
            Log.e(LOG, "Erro no intent > " + e.getMessage());
        }
        if (imgPlaneta > 0) {
            ImageView img = (ImageView) findViewById(R.id.img);
            img.setImageResource(imgPlaneta);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Up Navigation - voltando com animação
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
