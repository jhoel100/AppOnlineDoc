# AppOnlineDoc para android

Es una aplicacion mas directa y compacta en comparacion con la pagina web, dado que buscamos ser mas rapidos y directos con la atencion medica por celular.

## Proposito del Proyecto ⚙️

El proposito del proyecto es crear una linea de medicos online que puedan ayudar a pacientes enfermos en todas sus ubicaciones y condiciones.

Partes del proyecto:

- Pagina Web  Erick Gutiérrez Enríquez https://github.com/erick-GeGe/IS
- Conecciones Base de Datos Luis Felipe Vilcapaza https://github.com/luisfe12/Ingenieria-de-Sofware

## Funcionalidades 📋

Las funcionalidades de la app son las siguientes:

-  Comunicacion usuario con la base de datos y los doctores en linea.
-  Efectuar registros de citas, pagos y atenciones
-  Guardar un control o historial de todas las atenciones que se tubo

## Practicas de codigo legible 📋

- Comentar y Documentar: Se han comentado y explicado las funcionalidades de cada parte del codigo.
- Espaciado Correspondiente: Se han usado las indentaciones aun cuando no son necesarias, esto para mantener legible el codigo.
- Agrupado de Codigo: Se han usado la agrupacion que mantiene Android Studio al separar la interfaz, parte logica y manejo de dependencias.
- DRY: no se estan repitiendo partes de codigo que ya fueron definidas.
- Poca Anidacion: no se han usado anidaciones profundas, porque no fueron necesarias.
- Organizacion de Archivos: Se ha mantenido una estructuracion definida en todo el proyecto.
- Nombres Consistentes: Se ha tratado de expresar la funcionalidad de cada variable en su nombre asignado.
- Capitalizacion de sentencias SQL: Se ha manejado las mayusculas en sentencias SQL de la base de datos.
- Separado de datos y codigo: Se ha separado los datos del codigo, la base de datos se mantiene separada del codigo de la interfaz.

## Comenzando 🚀

Para iniciar puedes descargar el proyecto y ubicarlo en una carpeta de tu preferencia, solo necesitas descomprimirlo, nada mas.

### Pre-requisitos 📋

Para correr el proyecto necesitaras Android Studio 
tambien necesitaras la SDK completa de Android Studio, verifica haberla instalado correctamente

### Instalación 🔧

La instalacion es simple:
- Carga el proyecto en Android Studio, llendo a File - Open File or Project y seleccionando el archivo del proyecto, con icono de android
- Si te hace falta alguna dependencia, android studio la descarga por ti automaticamente.
- Construye la aplicacion y ya puedes correrla en cualquier dispositivo android

## Ejecutando las pruebas ⚙️

Para hacer pruebas de la aplicacion, vas a necesitar lo siguiente:
- Un dispositivo android virtual o fisico

Puedes usar los dispositivos virtuales que te da Android Studio, los cuales los instalas de manera sencilla con el AVD Manager
Tambien puede usar tu dispositivo fisico de preferencia, conectandolo con un cable USB, para que al correr la prueba selecciones 
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
## Principios DDD 📌

## Definicion del Dominio 

El dominio de la aplicacion de celular es toda una ciudad, para la cual se da cobertura del proyecto, en esta ciudad se puede atender y llamar a 
los pacientes a que puedan ser atendidos, esta definido en el documento de requisitos y de arquitectura del proyecto, el punto donde estos dominios se juntan es
el dominio de la base de datos, la cual es el punto de llegada y salida de todas las consultas de la aplicacion.

## Layers del DDD

Android studio nos separa el codigo en partes, desde su arbol de organizacion, estos ya estan formando capas, las cuales son:

a) manifest: almacena la estructura de la aplicacion, asi como los nombres de las activitys que la conforman.

b) java: almacena la parte logica del aplicativo, aca se definen todas las funcionalidades del proyecto.

c) res: almacena los layout de la aplicacion, los cuales son varios xml que describen como esta armada nuestra interfaz, tambien tiene documentos que usaremos como imagenes.

d) gradle scripts: cuenta con todos los documentos y dependencias necesarias para poder construir el aplicativo, en caso de no tener la dependencia, la descarga de internet.

## Lenguaje comun

Debido a que contamos con 3 desarrolladores de especialidades distintas, aplicacion de celular, pagina web y base de datos, se han nombrado y etiquetado las partes
del modelo global para que todos podamos tener un lenguaje unificado y podamos implementar con los nombres respectivos.

## Entidades

La applicacion de android trabaja con varios activity, los cuales son nuestros objetos, tienen atributos distintos, pero cada una de las activities tienen funcionalidad
distinta y una identidad propia en el modelo.

## Construido con 🛠️

* [Android Studio](https://developer.android.com/studio) - Aplicacion

Android Studio usa lo siguiente para el manejo de dependencias: 

* [Gradle](https://gradle.org/) - Manejador de dependencias

## Version 📌

Esta es la version 1.00

## Autores ✒️

* **Jhoel Salomon Tapara Quispe** - *App Celular* - [jhoel100](https://github.com/jhoel100)
* **Erick Estefano Gutierrez Enriquez** - *Pagina Web* - [erick-GeGe](https://github.com/erick-GeGe)
* **Luis Felipe Vilzapaza** - *Base de Datos y conecciones* - [luisfe12](https://github.com/luisfe12)

## Licencia 📄

Este proyecto está bajo la Licencia [LICENSE.md](LICENSE.md) revisala para mas detalles

## Expresiones de Gratitud 🎁

* Comenta a otros sobre este proyecto 📢
* Da las gracias públicamente 🤓.

---
⌨️ con ❤️ por [jhoel100](https://github.com/jhoel100) 😊
