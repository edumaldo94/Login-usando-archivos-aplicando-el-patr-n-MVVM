package com.softulp.lab3tp2.ui.registro;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.lab3tp2.R;
import com.softulp.lab3tp2.databinding.ActivityRegistroBinding;
import com.softulp.lab3tp2.model.Usuario;
import com.softulp.lab3tp2.ui.login.LoginActivity;
import com.softulp.lab3tp2.ui.login.ViewModelLogin;

public class RegistroActivity extends AppCompatActivity {
    private ActivityRegistroBinding binding;
    private ViewModelRegistro vmr;
    private ViewModelLogin vml;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vmr = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelRegistro.class);
        vmr.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if (usuario != null) {
                    binding.etDni.setText(String.valueOf(usuario.getDni()));
                    binding.etApellido.setText(usuario.getApellido());
                    binding.etNombre.setText(usuario.getNombre());
                    binding.etMail.setText(usuario.getMail());
                    binding.etPass.setText(usuario.getPassword());
                    vmr.LeerFoto(usuario.getFoto());
                }
            }
        });

        Intent intent = getIntent();
        int i = (int) intent.getIntExtra("flag", 0);
        if (i == 1) {
            vmr.LeerUsuario();
        }
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vmr.GuardarUsuario(
                        binding.etDni.getText().toString(),
                        binding.etApellido.getText().toString(),
                        binding.etNombre.getText().toString(),
                        binding.etMail.getText().toString(),
                        binding.etPass.getText().toString())
                ;
            }
        });

        vmr.getMFoto().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                binding.ivFoto.setImageBitmap(bitmap);
            }
        });

        binding.btSacarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("salida", "Saco foto");
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, 1);
                //}
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("salida", requestCode + " " + resultCode + " " + data.toString());
        vmr.respuestaCamara(requestCode, resultCode, data, 1);
    }
}