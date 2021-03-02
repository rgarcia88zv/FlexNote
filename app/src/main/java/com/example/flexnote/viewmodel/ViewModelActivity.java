package com.example.flexnote.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.flexnote.pojo.Profesor;
import com.example.flexnote.pojo.Tarea;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class ViewModelActivity extends AndroidViewModel {

    private Repository repository;

    public ViewModelActivity(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<Boolean> inicioSesion(String email, String password) {
        return repository.inicioSesion(email, password);
    }


    public Profesor getCurrentProfesor() {
        return repository.getCurrentProfesor();
    }

    public void setCurrentProfesor(Profesor currentProfesor) {
        repository.setCurrentProfesor(currentProfesor);
    }

    public Tarea getCurrentTarea() {
        return repository.getCurrentTarea();
    }

    public void setCurrentTarea(Tarea currentTarea) {
        repository.setCurrentTarea(currentTarea);
    }


    public MutableLiveData<List<Tarea>> obtenerTareas() {
        return repository.obtenerTareas();
    }

    public void insertarTarea(Tarea tarea, View v) {
        repository.insertarTarea(tarea);
    }

    public void borrarTarea(Tarea tarea) {
        repository.borrarTarea(tarea);
    }

    public void insertarProfesor(Profesor profesor, View v) {
        repository.insertarProfesor(profesor, v);
    }

    public void borrarProfesor(Profesor profesor) {
        repository.borrarProfesor(profesor);
    }

    public MutableLiveData<List<Profesor>> obtenerProfesores() {
        return repository.obtenerProfesores();
    }

    public FirebaseUser getCurrentUser() {
        return repository.getCurrentUser();
    }

    public void setCurrentUser(FirebaseUser currentUser) {
        repository.setCurrentUser(currentUser);
    }

    public void actualizaProf(Profesor profesor) {
        repository.actualizaProf(profesor);
    }

    public void actualizarTarea(Tarea tarea) {
        repository.actualizarTarea(tarea);
    }
}
