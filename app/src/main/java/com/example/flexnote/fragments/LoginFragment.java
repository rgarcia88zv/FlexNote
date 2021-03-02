package com.example.flexnote.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.flexnote.R;
import com.example.flexnote.viewmodel.ViewModelActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginFragment extends Fragment {
        ViewModelActivity viewModelActivity;
    public LoginFragment() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelActivity = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);


        Button btLogin = view.findViewById(R.id.btLogin);
        EditText etEmail = view.findViewById(R.id.etUser);
        EditText etPass = view.findViewById(R.id.etPass);


        final NavController navController = Navigation.findNavController(view);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();

                viewModelActivity.inicioSesion(email,pass).observe(getActivity(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean){

                            navController.navigate(R.id.action_loginFragment_to_menuFragment);
                            Snackbar.make(getActivity(),v,"Hola, Bienvenido de nuevo!", BaseTransientBottomBar.LENGTH_SHORT ).show();

                        }else{

                            Snackbar.make(getActivity(),v,"Ha habido un error, inténtalo de nuevo", BaseTransientBottomBar.LENGTH_SHORT ).setBackgroundTint(Color.WHITE).setTextColor(Color.RED).show();

                        }
                    }
                });

            }
        });
    }
}