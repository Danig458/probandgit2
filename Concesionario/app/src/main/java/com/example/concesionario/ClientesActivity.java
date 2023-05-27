package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ClientesActivity extends AppCompatActivity {

    ClsOpenHelper admin=new ClsOpenHelper(this,"Concesionario.db",null,1);
    EditText jetidentificacion,jetnombre,jetcorreo;
    CheckBox jcbactivo;
    String identificacion,nombre,correo;
    long respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        //Ocultar la barra de título por defecto y asociar los objetos Xml con los objetos Java
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
        if(identificacion.isEmpty() || nombre.isEmpty() || correo.isEmpty()){
            Toast.makeText(this, "Los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetidentificacion.requestFocus();
        }else{
            SQLiteDatabase db=admin.getWritableDatabase();
            ContentValues registro=new ContentValues();
            registro.put("Identificacion",identificacion);
            registro.put("nombre",nombre);
            registro.put("correo",correo);
            respuesta=db.insert("TblCliente",null,registro);
            if(respuesta > 0){

            }else{

            }

            db.close();
        }
    }
}