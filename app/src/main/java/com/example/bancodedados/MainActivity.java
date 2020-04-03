package com.example.bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);
            //criar tabelas
            bancoDados.execSQL("create table if not exists pessoas(nome VARCHAR(50), idade INT(3))");
            // inserir dados

            /*
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Carlos', 21)");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Fabiano', 21)");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Lucas', 20)");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Maycon', 23)");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Nathan', 20)");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Henrrique', 23)");
            */

            //Recuperar dados
            String consulta = "SELECT nome, idade FROM pessoas"; // guardando consulta em uma variavel

            /*
            String consulta = "SELECT nome, idade  FROM pessoas WHERE nome IN ('Fabiano', 'Henrique')";
            String consulta = "SELECT nome, idade  FROM pessoas WHERE idade BETWEEN 30 and 35";// no intervalo
            String consulta = "SELECT nome, idade  FROM pessoas WHERE nome LIKE '%ar%'";
            String consulta = "SELECT nome, idade  FROM pessoas WHERE nome IN ('Fabiano', 'Henrique')";
            */

            Cursor cursor = bancoDados.rawQuery(consulta, null);
            //percorrer dados
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");
            cursor.moveToFirst(); // volta para o primeiro
            while (cursor!=null){
                // salvando valores em uma variavel
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);
                // mostrar no logcat
                Log.i("Resultado - nome: ", nome);
                Log.i("Resultado - idade: ", idade);
                cursor.moveToNext(); // vai para o proximo
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
