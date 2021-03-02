package com.example.flexnote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flexnote.R;
import com.example.flexnote.pojo.Profesor;
import com.example.flexnote.viewmodel.ViewModelActivity;

import java.util.List;

public class ChooseProfAdapter extends RecyclerView.Adapter<ChooseProfAdapter.ViewHolder> {
    private Context context;
    private List<Profesor> listaProfs;
    ViewModelActivity viewModelActivity;
    private EditText etNombreProf;
    private View v;

    public ChooseProfAdapter(Context context, List<Profesor> listaProfs, View v) {
        this.context = context;
        this.listaProfs = listaProfs;
        this.v = v;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chooseprof,parent,false);

        viewModelActivity = new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModelActivity.class);
        ViewHolder holder = new ViewHolder(vista);
        etNombreProf= v.findViewById(R.id.etProfT);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.nombre.setText(listaProfs.get(position).getNombre());
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    etNombreProf.setText(String.valueOf(listaProfs.get(position).getNombre()));
                }
            });
    }

    @Override
    public int getItemCount() {
        return listaProfs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.tvNombreProfI);
            constraintLayout = itemView.findViewById(R.id.itemchoose);
        }
    }
}
