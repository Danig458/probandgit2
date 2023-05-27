package com.example.ejercicio1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ejercicio1.databinding.ActivityClienteBinding;

public class ClienteActivity extends AppCompatActivity {

    EditText jetidentificacion,jetnombre,jetcorreo;
    CheckBox jcbactivo;
    String identificacion,nombre,correo;
    ClsOpenHelper admin=new ClsOpenHelper(this,"Concesionario.db",null,1);
    long respuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        //Ocultar la barra de titulo por defecto y asociar objetos
        //Java con objetos Xml
        getSupportActionBar().hide();
        jetidentificacion=findViewById(R.id.etidentificacion);
        jetnombre=findViewById(R.id.etnombre);
        jetcorreo=findViewById(R.id.etcorreo);
        jcbactivo=findViewById(R.id.cbactivo);
    }

    public void Guardar(View view){
        identificacion=jetidentificacion.getText().toString();
        nombre=jetnombre.getText().toString();
        correo=jetcorreo.getText().toString();
        if (identificacion.isEmpty() || nombre.isEmpty() || correo.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetidentificacion.requestFocus();
        }else{
            SQLiteDatabase db=admin.getWritableDatabase();
            ContentValues registro=new ContentValues();
            registro.put("identificacion",identificacion);
            registro.put("nombre",nombre);
            registro.put("correo",correo);
            respuesta=db.insert("TblCliente",null,registro);
            if (respuesta == 0){
                Toast.makeText(this, "Error guardando registro", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
                Limpiar_campos();
            }
            db.close();
        }
    }//fin metodo guardar

    private void Limpiar_campos(){
        jetidentificacion.setText("");
        jetnombre.setText("");
        jetcorreo.setText("");
        jcbactivo.setChecked(false);
        jetidentificacion.requestFocus();
    }
}
