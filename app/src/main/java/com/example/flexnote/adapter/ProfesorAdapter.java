package com.example.flexnote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flexnote.pojo.Profesor;
import com.example.flexnote.R;
import com.example.flexnote.viewmodel.ViewModelActivity;

import java.util.List;

public class ProfesorAdapter extends RecyclerView.Adapter<ProfesorAdapter.ViewHolder> {

    private Context context;
    private List<Profesor> listaProfs;
    ViewModelActivity viewModelActivity;

    public ProfesorAdapter(Context context, List<Profesor> listaProfs) {
        this.context = context;
        this.listaProfs = listaProfs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profesor,parent,false);

        viewModelActivity = new ViewModelProvider((ViewModelStoreOwner) context).get(ViewModelActivity.class);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.nombre.setText(listaProfs.get(position).getNombre());

         String URL = listaProfs.get(position).getImagenUrl();

            Glide.with(context).load(URL).into(holder.img);

            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_profesoresFragment_to_editProfFragment);
                    viewModelActivity.setCurrentProfesor(listaProfs.get(position));
                }
            });

    }

    @Override
    public int getItemCount() {
        return listaProfs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        ImageView img;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.tvNamePItem);
            img = itemView.findViewById(R.id.imgPItem);
            constraintLayout = itemView.findViewById(R.id.itemPConstraint);
        }
    }
}
