package com.example.expensetracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.TransitionAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {
    Context context;
    ArrayList<TransactionModel> transactionModelArrayList;

    public TransactionAdapter(Context context, ArrayList<TransactionModel> transactionModelArrayList) {
        this.context = context;
        this.transactionModelArrayList = transactionModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.one_recycler_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
     TransactionModel model=transactionModelArrayList.get(position);
      String priority=model.getType();
      if(priority.equals("Expense")){
          holder.priority.setBackgroundResource(R.drawable.red_shape);
      }
      else{
          holder.priority.setBackgroundResource(R.drawable.green_shape);
      }
     holder.amount.setText(model.getAmount());
     holder.date.setText(model.getDate());
     holder.description.setText(model.getDescription());

     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent=new Intent(context,UpdateActivity.class);
             intent.putExtra("id",transactionModelArrayList.get(position).getId());
             intent.putExtra("amount",transactionModelArrayList.get(position).getAmount());
             intent.putExtra("description",transactionModelArrayList.get(position).getDescription());
             intent.putExtra("type",transactionModelArrayList.get(position).getType());
             context.startActivity(intent);
         }
     });
    }
    @Override
    public int getItemCount() {
        return transactionModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView description,amount,date;
        View priority;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            description=itemView.findViewById(R.id.description_one);
            amount=itemView.findViewById(R.id.amount_one);
            date=itemView.findViewById(R.id.date_one);
            priority=itemView.findViewById(R.id.priority_one);
        }
    }
}
