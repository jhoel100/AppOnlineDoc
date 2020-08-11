# AppOnlineDoc para android

Es una aplicacion mas directa y compacta en comparacion con la web, dado que buscamos ser mas rapidos y directos con la atencion por celular.

## Comenzando 🚀

Para iniciar puedes descargar el proyecto y ubicarlo en una carpeta de tu preferencia, solo necesitas descomprimirlo, nada mas.

### Pre-requisitos 📋

Para correr el proyecto necesitaras Android Studio 
tambien necesitaras la SDK completa de Android Studio, verifica haberla instalado correctamente

### Instalación 🔧

La instalacion es simple:
- Carga el proyecto en Android Studio, llendo a File - Open File or Project y seleccionando el archivo del proyecto, con icono de android
- Si te hace falta alguna dependencia, android studio la descarga por ti automaticamente.

## Ejecutando las pruebas ⚙️

Para hacer pruebas de la aplicacion, vas a necesitar lo siguiente:
- Un dispositivo android virtual o fisico

Puedes usar los dispositivos virtuales que te da Android Studio, los cuales los instalas de manera sencilla con el AVD Manager
Tambien puede usar tudispositivo fisico de preferencia, conectandolo con un cable USB, para que al correr la prueba selecciones 
el dispositivo como fuente de prueba.

### Estilos de programacion ⌨️

Los estilos de programacion usados son los siguientes:

### Things

Dado que el modelado de toda la interfaz esta en base a objetos, todos los activity son un conjunto de objetos, los cuales dan a disposicion metodos para
interactuar.

```
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{


    public AdminSQLiteOpenHelper(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table articulos(codigo int primary key, descripcion text, precio real)");
        BaseDeDatos.execSQL("create table receta(codigo int primary key, costo real, medicamento string)");
        BaseDeDatos.execSQL("create table cita(codigo int primary key, especialidad string,doctor string,problema string,fecha string)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
```

### Restful

Se espera una comunicacion entre la app, con la parte logica de la aplicacion, la cual envia las consultas al servidor, el mismo tiene la base de datos, con ella manda las respuestas a la solicutud.

```
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
```

### Persistent Tables

Los objetos creados en una activity o que son partes de ella pueden ser llamados desde otra activity, esto no siempre es util o tiene sentido, pero ello nos
habla de la clasificacion de objetos que se maneja en Android Studio.

```
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
```

### Principios SOLID usados ⌨️

Los ejemplos de principios SOLID usados son:

### Responsabilidad Unica

Cada objeto y cada activity solo hacen su trabajo y no pueden relacionarse con las otras, se relacionan solo mediante el paso de parametros o el cambio de activity. 

```
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{


    public AdminSQLiteOpenHelper(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table articulos(codigo int primary key, descripcion text, precio real)");
        BaseDeDatos.execSQL("create table receta(codigo int primary key, costo real, medicamento string)");
        BaseDeDatos.execSQL("create table cita(codigo int primary key, especialidad string,doctor string,problema string,fecha string)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
```

### Abierto Cerrado

Se ve como Android Studio te obliga a sobre escribir los metodos dados por herencia, de esta manera uno tampoco puede cambiar las clases padres, por ello estamos obligados a extender funcionalidades.

```
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{


    public AdminSQLiteOpenHelper(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table articulos(codigo int primary key, descripcion text, precio real)");
        BaseDeDatos.execSQL("create table receta(codigo int primary key, costo real, medicamento string)");
        BaseDeDatos.execSQL("create table cita(codigo int primary key, especialidad string,doctor string,problema string,fecha string)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
```

### Sustituible

Cualquier objeto de esta clase puede manipular la base de datos y puede hacer las consultas que se pidan.

```
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
```

### Interfaz Especifica

Se utiliza una interfaz distinta para cada una de las actividades y no una sola, como se puede ver en el codigo siguiente de la activity que direcciona a las demas.

```
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
```

### Inversion de dependencias

Los botones del proyecto y de las activitis dependen de la sobre escritura de metodos, si no se realiza esta misma o se deja en blanco el boton sigue funcionando
pero no realiza ninguna tarea.

```
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
```

## Construido con 🛠️

* [Android Studio](https://developer.android.com/studio) - Aplicacion

Android Studio usa lo siguiente para el manejo de dependencias: 

* [Gradle](https://gradle.org/) - Manejador de dependencias

## Version 📌

Esta es la version 1.00

## Autores ✒️

* **Jhoel Salomon Tapara Quispe** - *App Celular* - [jhoel100](https://github.com/jhoel100)
* **Fulanito Detal** - *Pagina Web* - [fulanitodetal](#fulanito-de-tal)
* **Fulanito Detal** - *Base de Datos y conecciones* - [fulanitodetal](#fulanito-de-tal)

## Licencia 📄

Este proyecto está bajo la Licencia [LICENSE.md](LICENSE.md) revisala para mas detalles

## Expresiones de Gratitud 🎁

* Comenta a otros sobre este proyecto 📢
* Da las gracias públicamente 🤓.

---
⌨️ con ❤️ por [jhoel100](https://github.com/jhoel100) 😊
