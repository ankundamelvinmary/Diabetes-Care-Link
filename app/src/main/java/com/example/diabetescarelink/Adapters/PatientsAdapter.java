package com.example.diabetescarelink.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.diabetescarelink.Models.PatientsModel;
import com.example.diabetescarelink.R;

import java.util.List;

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder> {
    Context context;
    List<PatientsModel> mData;


    public PatientsAdapter (Context context,List<PatientsModel> mData){
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PatientsAdapter.PatientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_patients,null,false);
        return new PatientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientsAdapter.PatientsViewHolder holder, int position) {
        PatientsModel patientsModel = mData.get(position);
        holder.fullnamesholder.setText(mData.get(position).getFullnames());
        holder.ageholder.setText(mData.get(position).getAge());
        holder.sexholder.setText(mData.get(position).getSex());
        holder.contactholder.setText(mData.get(position).getContact());
        holder.villageholder.setText(mData.get(position).getVillage());
        holder.districtholder.setText(mData.get(position).getDistrict());
        holder.maritalstatusholder.setText(mData.get(position).getMaritalstatus());
        holder.occupationholder.setText(mData.get(position).getOccupation());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PatientsViewHolder extends RecyclerView.ViewHolder {

        TextView fullnamesholder,ageholder,sexholder,contactholder,villageholder,districtholder,maritalstatusholder,occupationholder;
        TextView fullnames,age,sex,contact,village,district,maritalstatus,occupation;
        CardView patient_card;

        public PatientsViewHolder(@NonNull View itemView) {
            super(itemView);

            patient_card = itemView.findViewById(R.id.patient_card);
            fullnamesholder = itemView.findViewById(R.id.fullnamesholder);
            ageholder = itemView.findViewById(R.id.ageholder);
            sexholder = itemView.findViewById(R.id.sexholder);
            contactholder = itemView.findViewById(R.id.contactholder);
            villageholder = itemView.findViewById(R.id.villageholder);
            districtholder = itemView.findViewById(R.id.districtholder);
            maritalstatusholder = itemView.findViewById(R.id.maritalstatus);
            occupationholder = itemView.findViewById(R.id.occupationholder);

            fullnames = itemView.findViewById(R.id.fullnames);
            age = itemView.findViewById(R.id.age);
            sex = itemView.findViewById(R.id.sex);
            contact = itemView.findViewById(R.id.contact);
            village = itemView.findViewById(R.id.village);
            district = itemView.findViewById(R.id.district);
            maritalstatus = itemView.findViewById(R.id.maritalstatus);
            occupation = itemView.findViewById(R.id.occupation);
        }
    }
}
