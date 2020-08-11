package com.example.myapplication2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

public class Musica extends AppCompatActivity {

    private CheckBox adveturelifetime,clocks,getlucky,thislove,somewhere,boogie,paradise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adveturelifetime=(CheckBox)findViewById(R.id.AOLT);
        clocks=(CheckBox)findViewById(R.id.C);
        getlucky=(CheckBox)findViewById(R.id.GL);
        thislove=(CheckBox)findViewById(R.id.TL);
        somewhere=(CheckBox)findViewById(R.id.S);
        boogie=(CheckBox)findViewById(R.id.B);
        paradise=(CheckBox)findViewById(R.id.P);

    }

    public void Reproducir(View view){
        if(adveturelifetime.isChecked()==true){
            MediaPlayer mp=MediaPlayer.create(this,R.raw.adventurelifetime);
            mp.start();
        }
        if(clocks.isChecked()==true){
            MediaPlayer mp=MediaPlayer.create(this,R.raw.clocks);
            mp.start();
        }
        if(getlucky.isChecked()==true){
            MediaPlayer mp=MediaPlayer.create(this,R.raw.getlucky);
            mp.start();
        }
        if(thislove.isChecked()==true){
            MediaPlayer mp=MediaPlayer.create(this,R.raw.thislove);
            mp.start();
        }
        if(somewhere.isChecked()==true){
            MediaPlayer mp=MediaPlayer.create(this,R.raw.somewhere);
            mp.start();
        }
        if(boogie.isChecked()==true){
            MediaPlayer mp=MediaPlayer.create(this,R.raw.boogie);
            mp.start();
        }
        if(paradise.isChecked()==true){
            MediaPlayer mp=MediaPlayer.create(this,R.raw.paradise);
            mp.start();
        }


    }

    public  void CModelador(View view) {
        Intent cambiar = new Intent(this, Opciones.class);
        startActivity(cambiar);

    }

}
