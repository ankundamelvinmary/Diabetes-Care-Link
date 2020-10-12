package com.example.diabetescarelink.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diabetescarelink.Models.DrugsModel;
import com.example.diabetescarelink.R;
import com.example.diabetescarelink.Shop;

import java.util.List;

public class DrugsAdapter extends RecyclerView.Adapter<DrugsAdapter.PatientsViewHolder> {
    Context context;
    List<DrugsModel> mData;


    public DrugsAdapter(Context context, List<DrugsModel> mData){
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public DrugsAdapter.PatientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_drugs,null,false);
        return new PatientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrugsAdapter.PatientsViewHolder holder, final int position) {
        DrugsModel drugsModel = mData.get(position);
        holder.drugholder.setText(mData.get(position).getDrug());
        holder.pharmacyholder.setText(mData.get(position).getPlace());
        holder.costholder.setText(mData.get(position).getCost());
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pha = new Intent(context, Shop.class);
                pha.putExtra("drug",mData.get(position).getDrug());
                pha.putExtra("cost",mData.get(position).getCost());
                pha.putExtra("name",mData.get(position).getPlace());
                    context.startActivity(pha);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PatientsViewHolder extends RecyclerView.ViewHolder {

        TextView drugholder,costholder,pharmacyholder;
        TextView cost,drug,pharmacy;
        CardView patient_card;
        Button submit;
        public PatientsViewHolder(@NonNull View itemView) {
            super(itemView);

            patient_card = itemView.findViewById(R.id.patient_card);
            drugholder = itemView.findViewById(R.id.drugholder);
            pharmacyholder = itemView.findViewById(R.id.pharmacyholder);
            costholder = itemView.findViewById(R.id.costholder);
            submit = itemView.findViewById(R.id.submit);

            drug = itemView.findViewById(R.id.drug);
            pharmacy = itemView.findViewById(R.id.pharmacy);
            cost = itemView.findViewById(R.id.cost);

//            submit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//
//
//                }
//            });

        }
    }
}
