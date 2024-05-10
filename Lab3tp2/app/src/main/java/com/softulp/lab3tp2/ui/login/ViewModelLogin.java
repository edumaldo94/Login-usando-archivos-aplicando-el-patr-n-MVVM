package com.softulp.lab3tp2.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.lab3tp2.model.Usuario;
import com.softulp.lab3tp2.request.ApiClient;
import com.softulp.lab3tp2.ui.registro.RegistroActivity;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ViewModelLogin extends AndroidViewModel {
private ApiClient api;
    private Context context;
    public ViewModelLogin(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public void Login(String email, String password){
        Usuario usuario = ApiClient.login(context, email, password);
        if( usuario != null ){
            Intent intent = new Intent(context, RegistroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("flag", 1);
            context.startActivity(intent);
        }  else {
            Toast.makeText(context, "Mail o Password incorrecto.", Toast.LENGTH_LONG).show();
        }
    }

    public void Registro(){
        Intent intent = new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
