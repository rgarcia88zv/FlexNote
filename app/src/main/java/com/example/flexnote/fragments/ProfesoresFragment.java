package com.example.flexnote.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flexnote.R;
import com.example.flexnote.adapter.ProfesorAdapter;
import com.example.flexnote.pojo.Profesor;
import com.example.flexnote.viewmodel.ViewModelActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProfesoresFragment extends Fragment {
ViewModelActivity viewModelActivity;

    private List<Profesor> listaProfs = new ArrayList<>();

    private RecyclerView recyclerView;



    public ProfesoresFragment() {
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
        return inflater.inflate(R.layout.fragment_profesores, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelActivity = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);




        FloatingActionButton addProf = view.findViewById(R.id.floatingProf);


        viewModelActivity.obtenerProfesores().observe(getActivity(), new Observer<List<Profesor>>() {
            @Override
            public void onChanged(List<Profesor> profesors) {
                listaProfs.clear();
                listaProfs.addAll(profesors);


                recyclerView = view.findViewById(R.id.recyclerProfs);
                ProfesorAdapter adapter = new ProfesorAdapter(getContext(),listaProfs);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        });

        addProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_profesoresFragment_to_addProfFragment);
            }
        });
    }
}