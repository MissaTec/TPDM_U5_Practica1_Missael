package com.example.tpdm_u5_practica1_missael;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Mensajes(Palabra Varchar(50) NOT NULL PRIMARY KEY,Mensaje VARCHAR(200))");
        db.execSQL("INSERT INTO Mensajes VALUES('Android','Android es un sistema operativo móvil desarrollado por Google')");
        db.execSQL("INSERT INTO Mensajes VALUES('iOS','iOS es un sistema operativo móvil de la multinacional Apple Inc.')");
        db.execSQL("INSERT INTO Mensajes VALUES('Symbian','Symbian es un sistema operativo propiedad de Nokia')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
