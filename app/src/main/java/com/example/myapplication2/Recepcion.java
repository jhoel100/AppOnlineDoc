package com.example.myapplication2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Recepcion extends AppCompatActivity {

    private EditText et_especialidad,et_doctor,et_descripcion,et_fecha,et_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepcion);

        et_codigo=(EditText)findViewById(R.id.txt_codigor);
        et_especialidad=(EditText)findViewById(R.id.txt_especialidad);
        et_doctor=(EditText)findViewById(R.id.txt_doctor);
        et_descripcion=(EditText)findViewById(R.id.txt_descripcion);
        et_fecha=(EditText)findViewById(R.id.txt_fecha);

    }

    public void Registrar(View view){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"administracion",null,1);

        SQLiteDatabase BaseDeDatos =admin.getWritableDatabase();

        String codigo=et_codigo.getText().toString();
        String especialidad=et_especialidad.getText().toString();
        String doctor=et_doctor.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String fecha = et_fecha.getText().toString();

        if(!codigo.isEmpty() && !especialidad.isEmpty()  &&!doctor.isEmpty() && !descripcion.isEmpty()&& !fecha.isEmpty()){
            ContentValues registro =new ContentValues();

            registro.put("codigo",codigo);
            registro.put("especialidad",especialidad);
            registro.put("doctor",doctor);
            registro.put("problema",descripcion);
            registro.put("fecha",fecha);

            BaseDeDatos.insert("cita",null,registro);

            BaseDeDatos.close();
            et_codigo.setText("");
            et_especialidad.setText("");
            et_doctor.setText("");
            et_descripcion.setText("");
            et_fecha.setText("");

            Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this,"Debes llenar todos los campos",Toast.LENGTH_SHORT).show();
        }

    }


    public void Buscar(View view){

        AdminSQLiteOpenHelper admi=new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatabase=admi.getWritableDatabase();

        String codigo=et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            Cursor fila=BaseDeDatabase.rawQuery("select especialidad,doctor,problema,fecha from articulos where codigo ="+codigo,null);
            if(fila.moveToFirst()){
                et_especialidad.setText(fila.getString(0));
                et_doctor.setText(fila.getString(1));
                et_descripcion.setText(fila.getString(2));
                et_fecha.setText(fila.getString(3));
                BaseDeDatabase.close();
            }else{
                Toast.makeText(this,"No existe el registro",Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }
        }else{
            Toast.makeText(this,"Debes introducir tu codigo",Toast.LENGTH_SHORT).show();
        }

    }


    public  void CModelador(View view) {
        Intent cambiar = new Intent(this, Opciones.class);
        startActivity(cambiar);

    }



}