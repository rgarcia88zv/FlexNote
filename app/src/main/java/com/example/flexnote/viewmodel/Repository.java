package com.example.flexnote.viewmodel;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.flexnote.pojo.Profesor;
import com.example.flexnote.pojo.Tarea;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Repository {

        Context context;
        private FirebaseFirestore db;
        private FirebaseUser currentUser;
        private FirebaseAuth firebaseAuth;
        private Profesor currentProfesor;
        private Tarea currentTarea;


    public Repository(Context context) {
    this.context= context;
    db = FirebaseFirestore.getInstance();

    }


    public MutableLiveData<Boolean> inicioSesion(String email, String password){

        MutableLiveData<Boolean> result = new MutableLiveData<>();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
               currentUser = firebaseAuth.getCurrentUser();
                result.setValue(true);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                    currentUser = null;
                    result.setValue(false);
            }
        });


        return result;
    }


    public MutableLiveData<List<Tarea>> obtenerTareas(){
        MutableLiveData<List<Tarea>> listaTareas = new MutableLiveData<>();

        db.collection("user/" + getCurrentUser().getUid() + "/tareas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    listaTareas.setValue(task.getResult().toObjects(Tarea.class));
                }
            }
        });

        return listaTareas;
    }

    public void insertarTarea(Tarea tarea){
        db.collection("user/" +getCurrentUser().getUid() + "/tareas").document(String.valueOf(tarea.getNumTarea())).set(tarea).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void borrarTarea(Tarea tarea){

        db.collection("user/" + getCurrentUser().getUid() + "/tareas").document(String.valueOf(tarea.getNumTarea())).delete();

    }

    public void insertarProfesor(Profesor profesor, View v){
        db.collection("user/" +getCurrentUser().getUid() + "/profesores").document(String.valueOf(profesor.getDni())).set(profesor).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void borrarProfesor(Profesor profesor){

        db.collection("user/" + getCurrentUser().getUid() + "/profesores").document(String.valueOf(profesor.getDni())).delete();

    }



    public MutableLiveData<List<Profesor>> obtenerProfesores(){
        MutableLiveData<List<Profesor>> listaProfesores = new MutableLiveData<>();

        db.collection("user/" + getCurrentUser().getUid() + "/profesores").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
           if(task.isSuccessful()){
               listaProfesores.setValue(task.getResult().toObjects(Profesor.class));
           }
            }
        });


        return listaProfesores;
    }

    public void actualizaProf(Profesor profesor){

        db.collection("user/" + getCurrentUser().getUid() + "/profesores").document(profesor.getDni()).update("nombre",profesor.getNombre(),"apellido",profesor.getApellido(),
                "imagenUrl",profesor.getImagenUrl(),"fecNac",profesor.getFecNac(),"dni",profesor.getDni(),"asignatura",profesor.getAsignatura());

    }

    public void actualizarTarea(Tarea tarea){

        db.collection("user/" + getCurrentUser().getUid() + "/tareas").document(String.valueOf(tarea.getNumTarea())).update("numTarea",tarea.getNumTarea(),"titulo",tarea.getTitulo(),"asignatura",tarea.getAsignatura(),"profesor",tarea.getProfesor(),"descripcion",tarea.getDescripcion());

    }



    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(FirebaseUser currentUser) {
        this.currentUser = currentUser;
    }

    public Profesor getCurrentProfesor() {
        return currentProfesor;
    }

    public void setCurrentProfesor(Profesor currentProfesor) {
        this.currentProfesor = currentProfesor;
    }

    public Tarea getCurrentTarea() {
        return currentTarea;
    }

    public void setCurrentTarea(Tarea currentTarea) {
        this.currentTarea = currentTarea;
    }

}
