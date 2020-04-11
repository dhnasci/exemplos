package br.manaus.dnconsultoria.novalista;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlanetaAdapter extends RecyclerView.Adapter<PlanetaAdapter.PlanetasViewHolder> {

    protected static final String TAG = "LIVRO_ANDROID";
    private final List<Planeta> planetas;
    private final Context context;
    private final PlanetaOnClickListener onClickListener;

    public interface PlanetaOnClickListener {
        public void onClickPlaneta(PlanetasViewHolder holder, int idx);
    }

    public PlanetaAdapter(Context context, List<Planeta> planetas, PlanetaOnClickListener onClickListener) {
        this.context = context;
        this.planetas = planetas;
        this.onClickListener = onClickListener;
    }


    @Override
    public PlanetasViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType) {
        // Este método cria uma subclasse de RecyclerView.ViewHolder
        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_planeta, viewGroup, false);
        // Cria a classe do ViewHolder
        PlanetasViewHolder holder = new PlanetasViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PlanetasViewHolder holder, final int position) {
        Planeta c = planetas.get(position);
        holder.tNome.setText(c.nome);
        holder.img.setImageResource(c.img);

        // Click
        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Chama o listener para informar que clicou no Planeta
                    onClickListener.onClickPlaneta(holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.planetas != null ? this.planetas.size() : 0;
    }

    public class PlanetasViewHolder extends RecyclerView.ViewHolder{

        private final View view;
        public TextView tNome;
        ImageView img;
        ProgressBar progress;

        public PlanetasViewHolder(@NonNull View view) {
            super(view);

            this.view = view;
            tNome = (TextView) view.findViewById(R.id.tNome);
            img = (ImageView) view.findViewById(R.id.img);
            progress = (ProgressBar) view.findViewById(R.id.progress);
        }

    }
}
