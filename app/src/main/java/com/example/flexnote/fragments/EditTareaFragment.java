package com.example.flexnote.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.flexnote.R;
import com.example.flexnote.adapter.ChooseProfAdapter;
import com.example.flexnote.pojo.Profesor;
import com.example.flexnote.pojo.Tarea;
import com.example.flexnote.viewmodel.ViewModelActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class EditTareaFragment extends Fragment {
ViewModelActivity viewModelActivity;

    private List<Profesor> listaProfs = new ArrayList<>();

    private RecyclerView recyclerView;

    public EditTareaFragment() {
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
        return inflater.inflate(R.layout.fragment_edit_tarea, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelActivity = new ViewModelProvider(getActivity()).get(ViewModelActivity .class);

        Tarea t = viewModelActivity.getCurrentTarea();

        EditText etNumTarea = view.findViewById(R.id.etidentT);
        EditText etTitle = view.findViewById(R.id.etTitleT);
        EditText etSubject = view.findViewById(R.id.etSubjectT);
        EditText etProf = view.findViewById(R.id.etProfT);
        EditText etDescrip = view.findViewById(R.id.etDescripT);

        Button btBorrar = view.findViewById(R.id.btBorrarTarea);

        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelActivity.borrarTarea(t);
                Snackbar.make(getActivity(),v,"Tarea eliminada", BaseTransientBottomBar.LENGTH_SHORT).show();
                NavHostFragment.findNavController(EditTareaFragment.this).popBackStack();

            }
        });

        etNumTarea.setText(String.valueOf(t.getNumTarea()));
        etTitle.setText(t.getTitulo());
        etSubject.setText(t.getAsignatura());
        etProf.setText(t.getProfesor());
        etDescrip.setText(t.getDescripcion());

        viewModelActivity.obtenerProfesores().observe(getActivity(), new Observer<List<Profesor>>() {
            @Override
            public void onChanged(List<Profesor> profesors) {
                listaProfs.clear();
                listaProfs.addAll(profesors);

                recyclerView = view.findViewById(R.id.recyclerChooseProf);
                ChooseProfAdapter adapter = new ChooseProfAdapter(getContext(),listaProfs,view);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        });


        Button btActualizar = view.findViewById(R.id.btEditTarea);
        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarea tarea = new Tarea(Integer.parseInt(etNumTarea.getText().toString()),etTitle.getText().toString(),
                        etSubject.getText().toString(),etProf.getText().toString(),etDescrip.getText().toString());
                    viewModelActivity.actualizarTarea(tarea);
                Snackbar.make(getActivity(),v,"Tarea actualizada!", BaseTransientBottomBar.LENGTH_SHORT).show();
                NavHostFragment.findNavController(EditTareaFragment.this).popBackStack();
            }
        });

    }
}