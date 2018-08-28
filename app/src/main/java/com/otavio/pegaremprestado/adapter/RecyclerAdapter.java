package com.otavio.pegaremprestado.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.otavio.pegaremprestado.R;
import com.otavio.pegaremprestado.bd.EmprestimoDAO;
import com.otavio.pegaremprestado.model.Emprestimo;

import org.w3c.dom.Text;

import java.util.List;

import br.com.thindroid.annotations.AlarmTask;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by OtavioAugusto on 25/04/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Emprestimo> list;
    private Context context;





    public RecyclerAdapter(List<Emprestimo> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);

        return new ViewHolder(v);
    }

    public void setMovieList(List<Emprestimo> list) {
        this.list = list;
    }



    @Override
    public void onBindViewHolder(final RecyclerAdapter.ViewHolder holder, int position) {
        Emprestimo listaEmprestimo = list.get(position);
        holder.nome.setText(listaEmprestimo.getNome());
        holder.objeto.setText(listaEmprestimo.getObjeto());
        holder.qtdDias.setText(String.valueOf(listaEmprestimo.getQtdDias()));
        holder.qtdstatus.setText(String.valueOf(listaEmprestimo.getStatus()));





        if (listaEmprestimo.getStatus() == 1){
            holder.qtdstatus.setText(String.valueOf("Emprestado"));

        }



        holder.nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ItemClicado",""+holder.nome.getText());
                foo(5);
                holder.qtdstatus.setText(String.valueOf("Devolvido"));

            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nome, objeto, qtdDias, qtdstatus;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.txtnome);
            objeto = (TextView) itemView.findViewById(R.id.txtObjeto);
            qtdDias = (TextView) itemView.findViewById(R.id.txtQtdDias);
            qtdstatus = (TextView) itemView.findViewById(R.id.situacao);

        }
    }



    //TesteAlarme
    @br.com.thindroid.annotations.AlarmTask(interval = br.com.thindroid.annotations.AlarmTask.MINUTE, wakeUp = true)
    public void foo(int seg){
        try {

            //Converter dias em segundos
            Thread.sleep(seg * 1000);

            //Criar notificacao
            Log.i("Otavio", "chamar notificacao"+seg);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
