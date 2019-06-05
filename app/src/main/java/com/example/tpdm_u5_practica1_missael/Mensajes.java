package com.example.tpdm_u5_practica1_missael;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Mensajes {
    Database base;

    String msj,respuesta;


    public Mensajes(Context context){
        base=new Database(context,"Database",null,1);

    }

    public Mensajes(String a,String b){
        msj=a;respuesta=b;
    }

    public Mensajes[] select(){
        Mensajes[] res=null;
        try{

            SQLiteDatabase select=base.getReadableDatabase();
            Cursor c=select.rawQuery("SELECT * FROM Mensajes;",null);
            if(c.moveToFirst()){
                res=new Mensajes[c.getCount()];
                int pos=0;
                do{
                    res[pos]=new Mensajes(c.getString(0),c.getString(1));
                    pos++;
                }
                while(c.moveToNext());
            }

        }catch (SQLiteException e){

        }
        return res;
    }

    public String consultar(String palabra) {
        try {
            SQLiteDatabase selec= base.getWritableDatabase();
            Cursor c = selec.rawQuery("SELECT Mensaje FROM Mensajes WHERE Palabra = " + palabra , null);

            if (c.moveToFirst()==true){
                String cadena="";
                do {
                    String respuesta = c.getString(0);


                    cadena += respuesta;
                }while (c.moveToNext()==true);
                selec.close();
            }else {

            }
        }catch (SQLiteException e){

        }
        return respuesta;
    }
}
