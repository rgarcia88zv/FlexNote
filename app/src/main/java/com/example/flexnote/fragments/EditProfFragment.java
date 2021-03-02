package com.example.flexnote.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.flexnote.R;
import com.example.flexnote.pojo.Profesor;
import com.example.flexnote.viewmodel.ViewModelActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class EditProfFragment extends Fragment {
    ViewModelActivity viewModelActivity;


    public EditProfFragment() {
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
        return inflater.inflate(R.layout.fragment_edit_prof, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelActivity = new ViewModelProvider(getActivity()).get(ViewModelActivity .class);
        Profesor profesor = viewModelActivity.getCurrentProfesor();

        EditText etNombre = view.findViewById(R.id.etNombreP);
        EditText etApellido = view.findViewById(R.id.etApellidoP);
        EditText etImagen = view.findViewById(R.id.etImagenUrl);
        EditText etFecha = view.findViewById(R.id.etFechaNac);
        EditText etdninum = view.findViewById(R.id.etdninum);
        EditText etAsignatura = view.findViewById(R.id.etAsignatura);

        etNombre.setText(profesor.getNombre());
        etApellido.setText(profesor.getApellido());
        etImagen.setText(profesor.getImagenUrl());
        etFecha.setText(profesor.getFecNac());
        etdninum.setText(profesor.getDni());
        etAsignatura.setText(profesor.getAsignatura());

        Button btAgregar = view.findViewById(R.id.btEditProf);

        btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Profesor p = new Profesor(etNombre.getText().toString(),etApellido.getText().toString(),etImagen.getText().toString(),
                        etFecha.getText().toString(),etdninum.getText().toString(),etAsignatura.getText().toString());

                viewModelActivity.actualizaProf(p);
                Snackbar.make(getActivity(),v,"Profesor Actualizado!", BaseTransientBottomBar.LENGTH_SHORT).show();
                NavHostFragment.findNavController(EditProfFragment.this).popBackStack();

            }
        });

        Button btBorrar = view.findViewById(R.id.btDeleteProf);

        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelActivity.borrarProfesor(profesor);
                Snackbar.make(getActivity(),v,"Tarea eliminada", BaseTransientBottomBar.LENGTH_SHORT).show();
                NavHostFragment.findNavController(EditProfFragment.this).popBackStack();
            }
        });

    }
}