package com.otavio.pegaremprestado;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.otavio.pegaremprestado.adapter.RecyclerAdapter;
import com.otavio.pegaremprestado.bd.EmprestimoDAO;
import com.otavio.pegaremprestado.model.Emprestimo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button adicionar;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<Emprestimo> lista;

    EmprestimoDAO emprestimoDAO = new EmprestimoDAO(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = new ArrayList<>();
        adicionar = (Button) findViewById(R.id.adicionar);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));




        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEmprestimo.class);

                startActivity(intent);



            }
        });

        emprestimoDAO.open();


        for (Emprestimo e : emprestimoDAO.getAll()){

            if (e.getStatus() == 1){
                // editar banco de dados update
                Log.e("Dias:",""+e.getQtdDias());

//                Alarme(e.getQtdDias());
//                Log.e("Setar:","Mudar ");



            }
        }




    }



    public void onResume() {
        super.onResume();

        emprestimoDAO.open();


        recyclerAdapter = new RecyclerAdapter(emprestimoDAO.getAll(), this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        recyclerAdapter.notifyDataSetChanged();




    }


    public void Alarme(int dias){
        int time = Integer.parseInt(String.valueOf(dias));
        Intent intent=new Intent(MainActivity.this, Alarm.class);
        PendingIntent p1=PendingIntent.getBroadcast(getApplicationContext(),0, intent,0);
        AlarmManager a=(AlarmManager)getSystemService(ALARM_SERVICE);
        a.set(AlarmManager.RTC,System.currentTimeMillis() + time*1000,p1);
        Toast.makeText(getApplicationContext(),"Alarm set in "+time+"seconds",Toast.LENGTH_LONG).show();
    }


    //TesteAlarme
    @br.com.thindroid.annotations.AlarmTask(interval = br.com.thindroid.annotations.AlarmTask.MINUTE, wakeUp = true)
    public void foo(int dias){
        try {
            //Converter dias em segundos
            Thread.sleep(dias * 1000);

            //Criar notificacao
           Log.i("Alarmeeeee", "chamar notificacao"+dias);


//            //Chama whatsapp ja com mensagem pre definido e usuario só escolhe contato para enviar mensagem..
//            Intent sendIntent = new Intent();
//            sendIntent.setAction(Intent.ACTION_SEND);
//            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hora de devolver");
//            sendIntent.setPackage("com.whatsapp");
//            sendIntent.setType("text/plain");
//            startActivity(sendIntent);



        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

