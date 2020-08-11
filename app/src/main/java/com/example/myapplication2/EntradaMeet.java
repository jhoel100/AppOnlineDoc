package com.example.myapplication2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EntradaMeet extends AppCompatActivity {

    private EditText meetdni,meetcita;
    private EditText et_especialidad,et_doctor,et_descripcion,et_fecha,et_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        meetdni=(EditText)findViewById(R.id.meet_dni);
        meetcita=(EditText)findViewById(R.id.meet_cita);

        et_especialidad=(EditText)findViewById(R.id.meet_especialidad);
        et_doctor=(EditText)findViewById(R.id.meet_doctor);
        et_descripcion=(EditText)findViewById(R.id.meet_descripcion);
        et_fecha=(EditText)findViewById(R.id.meet_fecha);

    }

    public  void Iniciar_meet(View view) {
        Intent cambiar = new Intent(this, IniciarMeet.class);
        startActivity(cambiar);

    }

    public void Buscar(View view){

        AdminSQLiteOpenHelper admi=new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatabase=admi.getWritableDatabase();

        String codigo=meetcita.getText().toString();

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

    public void Entrar_Meet(View view) {
        Intent cambiar = new Intent(this, Opciones.class);
        startActivity(cambiar);
    }

}
