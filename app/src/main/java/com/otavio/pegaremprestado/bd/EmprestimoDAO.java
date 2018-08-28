package com.otavio.pegaremprestado.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.otavio.pegaremprestado.model.Emprestimo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OtavioAugusto on 25/04/18.
 */

public class EmprestimoDAO {
    private SQLiteDatabase database;
    private String[] colunas = {Database.COLUNA_NOME, Database.COLUNA_OBJETO, Database.COLUNA_DIAS, Database.COLUNA_STATUS, Database.COLUNA_ID};
    private Database databaseOpenHelper;

    public EmprestimoDAO(Context context){
        databaseOpenHelper = new Database(context);
    }

    public void open(){
        database = databaseOpenHelper.getWritableDatabase();

    }

    public void closed(){

        databaseOpenHelper.close();
    }

    public void create(Emprestimo emprestimo){
        ContentValues values = new ContentValues();
        values.put(Database.COLUNA_NOME, emprestimo.getNome());
        values.put(Database.COLUNA_OBJETO, emprestimo.getObjeto());
        values.put(Database.COLUNA_DIAS, emprestimo.getQtdDias());
        values.put(Database.COLUNA_STATUS, emprestimo.getStatus());
        values.put(Database.COLUNA_ID, emprestimo.getIDJobs());

        database.insert(Database.NOME_TABELA, null, values);
        database.close();


    }

    public List<Emprestimo> getAll(){
        List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
       // Cursor cursor = database.query(Database.NOME_TABELA,null,null,null,null,null,null);

//        while (cursor.moveToNext()){
//            Emprestimo e;
//            e = new Emprestimo(cursor.getString(0),cursor.getString(1),cursor.getInt(2));
//            emprestimos.add(e);
//        }

        Cursor cursor = database.query(Database.NOME_TABELA, colunas, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){

            emprestimos.add(new Emprestimo(cursor.getString(0),cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4)));
            cursor.moveToNext();
        }
        cursor.close();
        return emprestimos;
    }



    public List<Emprestimo> retornarTodos(){
        List<Emprestimo> emprestimos = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM emprestimo", null);
        while(cursor.moveToNext()){
            //int id = cursor.getInt(cursor.getColumnIndex("ID"));

            String nome = cursor.getString(cursor.getColumnIndex(Database.COLUNA_NOME));
            String objeto = cursor.getString(cursor.getColumnIndex(Database.COLUNA_OBJETO));
            int Qtddias = cursor.getInt(cursor.getColumnIndex(Database.COLUNA_DIAS));
            int status = cursor.getInt(cursor.getColumnIndex(Database.COLUNA_STATUS));
            int id = cursor.getInt(cursor.getColumnIndex(Database.COLUNA_ID));

            emprestimos.add(new Emprestimo(nome, objeto, Qtddias,status,id));
        }
        cursor.close();
        return emprestimos;
    }

}
