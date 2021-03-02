package com.example.flexnote.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flexnote.R;
import com.example.flexnote.adapter.ProfesorAdapter;
import com.example.flexnote.adapter.TareaAdapter;
import com.example.flexnote.pojo.Profesor;
import com.example.flexnote.pojo.Tarea;
import com.example.flexnote.viewmodel.ViewModelActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class TareasFragment extends Fragment {
ViewModelActivity viewModelActivity;

    private List<Tarea> listaTareas = new ArrayList<>();

    private RecyclerView recyclerView;

    public TareasFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tareas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelActivity = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);

        FloatingActionButton addTarea = view.findViewById(R.id.floatingActionButton2);

        viewModelActivity.obtenerTareas().observe(getActivity(), new Observer<List<Tarea>>() {
            @Override
            public void onChanged(List<Tarea> tareas) {

                listaTareas.clear();
                listaTareas.addAll(tareas);

                recyclerView = view.findViewById(R.id.recyclerTareas);
                TareaAdapter adapter = new TareaAdapter(getActivity(),listaTareas);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



            }
        });


        addTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_tareasFragment_to_addTareaFragment);
            }
        });


    }
}