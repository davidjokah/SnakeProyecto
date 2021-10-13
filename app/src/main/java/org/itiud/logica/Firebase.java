package org.itiud.logica;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {

    private final static Firebase INSTANCE = new Firebase();
    private static DatabaseReference databaseR;

    public static DatabaseReference getDatabaseR(){
        return  databaseR;
    }

    public static Firebase getInstance(){
        return  INSTANCE;};

    public Firebase(){
        databaseR = FirebaseDatabase.getInstance().getReference();
    }

    public static void addUser(Ususario user){
        databaseR.child("Usuario").child(user.getNombre()).setValue(user);
    }

}
