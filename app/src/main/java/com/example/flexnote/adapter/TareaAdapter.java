package com.example.flexnote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flexnote.R;
import com.example.flexnote.pojo.Tarea;
import com.example.flexnote.viewmodel.ViewModelActivity;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.ViewHolder> {

    private Context contexto;
    private List<Tarea> listaTareas;
    ViewModelActivity viewModelActivity;

    public TareaAdapter(Context contexto, List<Tarea> listaTareas) {
        this.contexto = contexto;
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tareas,parent,false);

        viewModelActivity = new ViewModelProvider((ViewModelStoreOwner) contexto).get(ViewModelActivity.class);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

                holder.name.setText(listaTareas.get(position).getTitulo());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NavController navController = Navigation.findNavController(v);
                 navController.navigate(R.id.editTareaFragment);
                viewModelActivity.setCurrentTarea(listaTareas.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ConstraintLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvNombreTItem);
            parent = itemView.findViewById(R.id.contstraintTareaitem);

        }
    }
}
