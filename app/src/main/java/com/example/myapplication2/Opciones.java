package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class Opciones extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.modelador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent cambiar=new Intent(this,Videos.class);
            startActivity(cambiar);
        } else if (id == R.id.nav_gallery) {
            Intent cambiar=new Intent(this,Fotografias.class);
            startActivity(cambiar);

        } else if (id == R.id.nav_slideshow) {
            Intent cambiar=new Intent(this,Manual.class);
            startActivity(cambiar);
        } else if (id == R.id.nav_tools) {
            Intent cambiar=new Intent(this,Ajustes.class);
            startActivity(cambiar);
        } else if (id == R.id.nav_share) {
            Intent cambiar=new Intent(this,Compartir.class);
            startActivity(cambiar);
        } else if (id == R.id.nav_send) {
            Intent cambiar=new Intent(this,Referencias.class);
            startActivity(cambiar);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void CManual(View view) {
        Intent cambiar = new Intent(this, EntradaMeet.class);
        startActivity(cambiar);

    }

    public  void CMusica(View view) {
        Intent cambiar = new Intent(this, Musica.class);
        startActivity(cambiar);

    }
    public  void CFarmacia(View view) {
        Intent cambiar = new Intent(this, Farmacia.class);
        startActivity(cambiar);

    }

    public  void CRecepcion(View view) {
        Intent cambiar = new Intent(this, Recepcion.class);
        startActivity(cambiar);

    }

}
