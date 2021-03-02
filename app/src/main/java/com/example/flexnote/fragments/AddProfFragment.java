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

public class AddProfFragment extends Fragment {
ViewModelActivity viewModelActivity;


    public AddProfFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_prof, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelActivity = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);

        EditText etNombre = view.findViewById(R.id.etNombreP);
        EditText etApellido = view.findViewById(R.id.etApellidoP);
        EditText etImagen = view.findViewById(R.id.etImagenUrl);
        EditText etFecha = view.findViewById(R.id.etFechaNac);
        EditText etdninum = view.findViewById(R.id.etdninum);
        EditText etAsignatura = view.findViewById(R.id.etAsignatura);

        Button btAgregar = view.findViewById(R.id.btAddProf);

        btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profesor profesor = new Profesor(etNombre.getText().toString(),etApellido.getText().toString(), etImagen.getText().toString()
                        ,etFecha.getText().toString(),etdninum.getText().toString(),etAsignatura.getText().toString());

                viewModelActivity.insertarProfesor(profesor,v);
                Snackbar.make(getActivity(),v,"Tarea añadida!", BaseTransientBottomBar.LENGTH_SHORT).show();
                NavHostFragment.findNavController(AddProfFragment.this).popBackStack();
            }
        });
    }
}