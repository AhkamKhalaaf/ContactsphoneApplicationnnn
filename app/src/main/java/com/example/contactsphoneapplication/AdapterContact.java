package com.example.contactsphoneapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsphoneapplication.beanContact.Contactbean;

import java.util.ArrayList;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.Myviewholder> {
    ArrayList<Contactbean>arrayList;
    Context context;

    public AdapterContact(ArrayList<Contactbean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contatctitemm,parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        Contactbean contactbean = arrayList.get(position);
        holder.textViewphone.setText(contactbean.getPhone().toString());
        holder.textViewname.setText(contactbean.getName().toString());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView textViewname,textViewphone;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            textViewname = itemView.findViewById(R.id.name);
            textViewphone = itemView.findViewById(R.id.phone);
        }
    }
}
