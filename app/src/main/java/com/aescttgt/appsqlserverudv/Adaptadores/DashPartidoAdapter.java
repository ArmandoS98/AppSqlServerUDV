package com.aescttgt.appsqlserverudv.Adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aescttgt.appsqlserverudv.Interfaz.IPartidos;
import com.aescttgt.appsqlserverudv.Pojos.DashPartido;
import com.aescttgt.appsqlserverudv.Pojos.Jugador;
import com.aescttgt.appsqlserverudv.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DashPartidoAdapter extends RecyclerView.Adapter<DashPartidoAdapter.ViewHolder> {
    private static final String TAG = "DashPartidoAdapter";
    private ArrayList<DashPartido> valores;
    private Context context;
    private IPartidos mIPartidos;
    private int mSelectedPartidoIndex;


    public DashPartidoAdapter(Context context, ArrayList<DashPartido> valores) {
        this.context = context;
        this.valores = valores;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_partido, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.mteTextViewNombreEquipoLocal.setText(valores.get(i).getEquipo_Local());
        viewHolder.mteTextViewNombreEquipoVisitante.setText(valores.get(i).getEquipo_Visitante());
        viewHolder.mteTextViewGolesEquipoLocal.setText(String.valueOf(valores.get(i).getGoles_Local()));
        viewHolder.mteTextViewGolesEquipoVisitante.setText(String.valueOf(valores.get(i).getGoles_Visitante()));

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = dateFormat.format(valores.get(i).getJornada());
            viewHolder.mTextViewNombreCampeonato.setText(valores.get(i).getNombre_campeonato() + "   " + strDate);
        } catch (Exception ex) {
            Log.e(TAG, "onBindViewHolder: Error date " + ex.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return valores.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mIPartidos = (IPartidos) context;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mteTextViewNombreEquipoLocal;
        private TextView mteTextViewNombreEquipoVisitante;
        private TextView mteTextViewGolesEquipoVisitante;
        private TextView mteTextViewGolesEquipoLocal;
        private TextView mTextViewNombreCampeonato;
        private CardView mCardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cartita);

            mteTextViewNombreEquipoLocal = itemView.findViewById(R.id.tv_nombre_equipo_local);
            mteTextViewNombreEquipoVisitante = itemView.findViewById(R.id.tv_nombre_equipo_visitante);
            mteTextViewGolesEquipoVisitante = itemView.findViewById(R.id.tv_goles_visitante);
            mteTextViewGolesEquipoLocal = itemView.findViewById(R.id.tv_goles_local);
            mTextViewNombreCampeonato = itemView.findViewById(R.id.tv_nombre_campeonato);


            mCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mSelectedPartidoIndex = getAdapterPosition();
            mIPartidos.onDashPartido(valores.get(mSelectedPartidoIndex));
        }
    }
}
