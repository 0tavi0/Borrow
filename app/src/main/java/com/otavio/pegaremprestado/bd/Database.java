package com.otavio.pegaremprestado.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by OtavioAugusto on 25/04/18.
 */

public class Database extends SQLiteOpenHelper {

    public static final String NOME_TABELA = "emprestimo";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_OBJETO = "objEmprestado";
    public static final String COLUNA_DIAS = "qtdDias";
    public static final String COLUNA_STATUS = "status";
    public static final String COLUNA_ID = "id";



    public static final String DATABASE_NAME = "banco.db";
    public static final int DATABASE_VERSION = 5;

    public static final String DATABASE_CREATE = "CREATE TABLE " + NOME_TABELA + " ( " + COLUNA_NOME + " TEXT, "
            + COLUNA_OBJETO + " TEXT, " + COLUNA_DIAS + " INTEGER, " + COLUNA_STATUS + " INTEGER, " + COLUNA_ID + " INTEGER);";

    public Database (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        String comando = "CREATE TABLE IF NOT EXISTS" + NOME_TABELA + "(" +
//                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "nome TEXT,"+
//                "objEmprestado TEXT,"+
//                "qtdDias INTEGER)";
        sqLiteDatabase.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
//        onCreate(sqLiteDatabase);


    }
}
