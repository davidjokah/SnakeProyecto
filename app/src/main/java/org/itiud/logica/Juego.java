package org.itiud.logica;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.itiud.snakeproyecto.MainActivity;

import java.util.Observable;

public class Juego extends Observable {


    private Ususario user = new Ususario("player1");

    public void setUser(Ususario user) {
        Firebase.addUser(user);
        this.user = user;
    }

    public static Ususario user2 = new Ususario("player2");

    /* private EditText editText;
    private Button button;

    String playerName = "";

    FirebaseDatabase database;
    DatabaseReference playerRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.txtPlyr);
        button = findViewById(R.id.btnPly);

        database = FirebaseDatabase.getInstance();

        //Verificar si el jugador existe y obtener la referencia
        SharedPreferences preferences = getSharedPreferences("PREGS",0);
        playerName = preferences.getString("playerName","");
        if(!playerName.equals("")){
            playerRef = database.getReference("players/"+playerName);
            addEventListener();
            playerRef.setValue("");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerName = editText.getText().toString();
                editText.setText("");
                if(!playerName.equals("")){
                    button.setText("LOGGING IN");
                    button.setEnabled(false);
                    playerRef = database.getReference("players/"+playerName);
                    addEventListener();
                    playerRef.setValue("");
                }
            }
        });
    }

    private void addEventListener(){
        //Leer datos de Firebase
        playerRef.addValueEventListener(new ValueEventListener() {
            @Override
            // Lee los datos y guarda el nombre del jugador, continua a la siguiente pantalla.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!playerName.equals("")){
                    SharedPreferences preferences = getSharedPreferences("PREFS",0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("playerName", playerName);
                    editor.apply();

                    startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                    finish();
                }
            }

            @Override
            //error al iniciar sesion
            public void onCancelled(@NonNull DatabaseError error) {
                button.setText("LOG IN");
                button.setEnabled(true);
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

            ListView listView;
    Button btnCrear;

    List<String> roomsList;

    String playerName = "";
    String roomName = "";

    FirebaseDatabase database;
    DatabaseReference roomRef;
    DatabaseReference roomsRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        database = FirebaseDatabase.getInstance();

        //Obtenemos el nombbre del jugador y le asignamos una sala de juego
        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        playerName = preferences.getString("playerName", "");
        roomName = playerName;

        listView = findViewById(R.id.listView);
        btnCrear = findViewById(R.id.btnCrearJuego);

        //Muestra las salas de juego existentes
        roomsList = new ArrayList<>();

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            //Creación de sala y primer jugador
            public void onClick(View v) {
                btnCrear.setText("CREATING ROOM");
                btnCrear.setEnabled(false);
                roomName = playerName;
                roomRef = database.getReference("rooms/" + roomName+ "/player1");
                addRoomEventListener();
                roomRef.setValue(playerName);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //El segundo jugador se une al juego y se nombra como jugador 2
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                roomName = roomsList.get(position);
                roomRef = database.getReference("rooms/" +roomName+ "/player2");
                addRoomEventListener();
                roomRef.setValue(playerName);

            }
        });

        //addRoomsEventListener();
    }

    private void addRoomEventListener(){
        roomRef.addValueEventListener(new ValueEventListener() {
            @Override
            //Crear sala
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                btnCrear.setText("CREATE ROOM");
                btnCrear.setEnabled(true);
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("roomName", roomName);
                startActivity(intent);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                btnCrear.setText("CREATE ROOM");
                btnCrear.setEnabled(true);
                Toast.makeText(MainActivity2.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  void addRoomsEventListener(){
        roomsRef = database.getReference("rooms");
        roomsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Muestra la lista de salas de juego
                System.out.println("Hola");
                roomsList.clear();
                Iterable<DataSnapshot> rooms = snapshot.getChildren();
                for(DataSnapshot dataSnapshot : rooms){
                    roomsList.add(dataSnapshot.getKey());

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity2.this,
                            android.R.layout.simple_list_item_1);
                    listView.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error no pasa nada
            }
        });
    }
    }*/

    private void asignarPartida(Ususario userx) {

        if ((this.user.getContrincante() == null ||
                this.user.getContrincante().equals("") && userx.getContrincante() == null ||
                userx.getContrincante().equals("") || userx.getContrincante().equals(this.user.getNombre())) && !this.user.getNombre().equals("player1") && !this.user.getModo().equals("standby")
                && !userx.getModo().equals("standby") &&
                userx.getNombre().equals(this.user.getNombre())) {
            this.user.setContrincante(userx.getNombre());
            Firebase.addUser(this.user);
            this.setChanged();
            this.notifyObservers(userx);
            user2 = userx;
        }
    }

    public void Iniciar() {
        ValueEventListener nuevoUsuario = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot usuarionv = null;

                for (DataSnapshot snapshotx : snapshot.getChildren()) {
                    usuarionv = snapshotx;

                    Ususario nuevo = new Ususario();

                    nuevo.setNombre(usuarionv.child("nombreUsuario").getValue().toString());
                    nuevo.setModo(usuarionv.child("modo").getValue().toString());
                    nuevo.setX(Boolean.parseBoolean(usuarionv.child("preparado").getValue().toString()));
                    nuevo.setPuntaje(Integer.parseInt(String.valueOf(usuarionv.child("puntaje").getValue())));

                    if (!nuevo.getContrincante().equals("") && user2.getNombre().equals(nuevo.getNombre()) && user2 != null) {
                        user2.setNombre(nuevo.getNombre());
                        user2.setPuntaje(nuevo.getPuntaje());
                        user2.setContrincante(nuevo.getContrincante());
                        user2.setX(nuevo.isX());
                        user2.setModo(nuevo.getModo());
                    }
                    asignarPartida(nuevo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Error", "loadPost:onCancelled", error.toException());
            }
        };
        Firebase.getDatabaseR().child("ususario").addValueEventListener(nuevoUsuario);

    }


}
