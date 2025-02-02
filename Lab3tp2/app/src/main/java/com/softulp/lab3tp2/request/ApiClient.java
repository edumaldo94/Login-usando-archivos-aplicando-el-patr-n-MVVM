package com.softulp.lab3tp2.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.softulp.lab3tp2.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {
    public static void guardar(Context context, Usuario usuario){
        Log.d("salida", usuario.toString());
        Log.d("salida", context.getFilesDir().toString());
        File archivo = new File(context.getFilesDir(), "fichero.dat");
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream ous = new ObjectOutputStream(bos);
            ous.writeObject(usuario);
            bos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(context,"Error al guardar", Toast.LENGTH_LONG).show();
        } catch (IOException io){
            io.printStackTrace();
            Toast.makeText(context,"Error de E/S "+io.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static Usuario leer(Context context){
        File archivo = new File(context.getFilesDir(), "fichero.dat");
        if(!archivo.exists()){
            return null;
        }
        Usuario usuario = null;
        try {
            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            usuario = (Usuario)ois.readObject();
            Log.d("salida", usuario.toString());
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(context,"Error al guardar",Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context,"Error de Clase",Toast.LENGTH_LONG).show();
        } catch (IOException io) {
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_LONG).show();
        }
        return usuario;
    }

    public static Usuario login(Context context, String email, String pass){
        Usuario usuario = leer(context);
        if( usuario.getMail().equals(email) && usuario.getPassword().equals(pass)){
            return usuario;
        }
        return null;
    }


}
