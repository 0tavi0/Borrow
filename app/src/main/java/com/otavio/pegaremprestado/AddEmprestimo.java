package com.otavio.pegaremprestado;

import android.app.DatePickerDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.otavio.pegaremprestado.bd.EmprestimoDAO;
import com.otavio.pegaremprestado.model.Emprestimo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AddEmprestimo extends AppCompatActivity {

    EditText nome, objeto, qtdDias;
    Button salvar;
    ToggleButton toggle;
    int statusfora = 0;

    Calendar c;
    DatePickerDialog pickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emprestimo);

        nome = (EditText) findViewById(R.id.nome);
        objeto = (EditText) findViewById(R.id.objeto);
        qtdDias = (EditText) findViewById(R.id.qtdDias);
        salvar = (Button) findViewById(R.id.salvar);
        toggle = (ToggleButton) findViewById(R.id.toggleButton);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    int status = 1;
//                    statusfora =  status;
//
//                    EmprestimoDAO emprestimoDAO = new EmprestimoDAO(AddEmprestimo.this);
//                    emprestimoDAO.open();
//                    emprestimoDAO.create(new Emprestimo(nome.getText().toString(), objeto.getText().toString(),
//                            Integer.parseInt(qtdDias.getText().toString()), statusfora));
//                    emprestimoDAO.closed();
//                    finish();

                    JobScheduler scheduler =  (JobScheduler)  getBaseContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
                        scheduler.cancelAll();
                        Log.e("Botao cancelado","Precionado");

                }
            }
        });


        salvar.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                int status = 1;
                int JobsID  = (int) (Math.random()*1000);
                Log.e("Numero Random",""+JobsID);

                if (nome.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(), "Digite um nome", Toast.LENGTH_LONG).show();
                }
                else if (objeto.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(), "Digite um objeto", Toast.LENGTH_LONG).show();
                }else if (qtdDias.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(), "Digite a qtd dias", Toast.LENGTH_LONG).show();
                }
                else {
                    EmprestimoDAO emprestimoDAO = new EmprestimoDAO(AddEmprestimo.this);
                    emprestimoDAO.open();

                    long mili = TimeUnit.MINUTES.toMillis(Integer.parseInt(qtdDias.getText().toString()));

                    emprestimoDAO.create(new Emprestimo(nome.getText().toString(), objeto.getText().toString(), Integer.parseInt(qtdDias.getText().toString()), status, JobsID));

                    JobTeste(JobsID, mili);
                    emprestimoDAO.closed();
                    finish();
                }

//                JobTeste(0,4000);

//                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
//
//                long now = System.currentTimeMillis();
//
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTimeInMillis(now);
//
//                Log.e("Now"+now," = " + formatter.format(calendar.getTime()));


                //parei aqui.. usar o timeunit para converter quantidade de dias em milissegundos
//                c = Calendar.getInstance();
//
//                int dia = c.get(Calendar.DAY_OF_MONTH);
//                int ano = c.get(Calendar.YEAR);
//                final int mes = c.get(Calendar.MONTH);
//
//                pickerDialog = new DatePickerDialog(AddEmprestimo.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        Log.e("Ano "+ year, "Mes " + month + "Dia" + dayOfMonth );
//                        long mili = TimeUnit.DAYS.toMillis(dayOfMonth);
//                        Log.e("MIli",""+mili);
//
//                    }
//                },ano, mes, dia);
//
//                pickerDialog.show();




            }
        });



    }

    //TesteAlarme
    @br.com.thindroid.annotations.AlarmTask(interval = br.com.thindroid.annotations.AlarmTask.MINUTE, wakeUp = true)
    public void foo(int seg){
        try {

            //Converter dias em segundos
            Thread.sleep(seg * 1000);

            //Criar notificacao
            Log.i("Otavio", "chamar notificacao"+seg);

            Toast.makeText(this, "Alarmar em "+seg, Toast.LENGTH_LONG).show();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void JobTeste(int idJobs, long segundosJobs){

        ComponentName serviceName =  new ComponentName(getBaseContext(),  AlarmJobScheduler.class);
        JobInfo jobInfo =  new  JobInfo.Builder(idJobs, serviceName)
//                .setMinimumLatency(4000)
                .setPeriodic(segundosJobs)
                .setRequiresCharging(false)
                .build();
        JobScheduler scheduler =  (JobScheduler)  getBaseContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int result=  scheduler.schedule(jobInfo);
        if(result==  JobScheduler.RESULT_SUCCESS)

            Log.e("MainActivity",  "Serviço  agendado! id:" + idJobs);

    }
}
