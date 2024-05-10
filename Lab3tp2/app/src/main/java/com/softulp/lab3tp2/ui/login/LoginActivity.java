package com.softulp.lab3tp2.ui.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.lab3tp2.R;
import com.softulp.lab3tp2.databinding.ActivityLoginBinding;
import com.softulp.lab3tp2.databinding.ActivityRegistroBinding;
import com.softulp.lab3tp2.model.Usuario;
import com.softulp.lab3tp2.ui.registro.RegistroActivity;
import com.softulp.lab3tp2.ui.registro.ViewModelRegistro;

public class LoginActivity extends AppCompatActivity {
private ActivityLoginBinding binding;
private ViewModelLogin vml;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vml = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelLogin.class);
        solicitarPermiso();
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vml.Login(
                        binding.etxtUser.getText().toString(),
                        binding.etxtPass.getText().toString()
                );
            }
        });
        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vml.Registro();
            }
        });
    }

    public void solicitarPermiso(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && (checkSelfPermission(Manifest.permission.CAMERA)  != PackageManager.PERMISSION_GRANTED) ||
                (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)  != PackageManager.PERMISSION_GRANTED)){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }

    }

}