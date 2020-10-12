package com.example.diabetescarelink.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diabetescarelink.Models.NursesModel;
import com.example.diabetescarelink.Models.PharmaciesModel;
import com.example.diabetescarelink.R;

import java.util.List;

public class PharmaciesAdapter extends RecyclerView.Adapter<PharmaciesAdapter.PharmaciesViewHolder>{
    Context context;
    List<PharmaciesModel> mData;


    public PharmaciesAdapter (Context context,List<PharmaciesModel> mData){
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PharmaciesAdapter.PharmaciesViewHolder onCreateViewHolder(@NonNull ViewGroup nurse, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_pharmacies,null,false);
        return new PharmaciesAdapter.PharmaciesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmaciesAdapter.PharmaciesViewHolder holder, int position) {
        PharmaciesModel pharmaciesModel = mData.get(position);
        holder.fullnamesholder.setText(mData.get(position).getFullnames());
        holder.contactholder.setText(mData.get(position).getContact());
        holder.emailholder.setText(mData.get(position).getEmail());
        holder.locationholder.setText(mData.get(position).getLocation());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PharmaciesViewHolder extends RecyclerView.ViewHolder {

        TextView fullnamesholder,contactholder, emailholder, locationholder;
        TextView fullnames,contact, email,location;
        CardView patient_card;

        public PharmaciesViewHolder(@NonNull View itemView) {
            super(itemView);
            patient_card = itemView.findViewById(R.id.patient_card);
            fullnamesholder = itemView.findViewById(R.id.fullnamesholder);
            contactholder = itemView.findViewById(R.id.contactholder);
            emailholder = itemView.findViewById(R.id.emailholder);
            locationholder = itemView.findViewById(R.id.locationholder);

            fullnames = itemView.findViewById(R.id.fullnames);
            contact = itemView.findViewById(R.id.contact);
            email = itemView.findViewById(R.id.email);
            location = itemView.findViewById(R.id.location);

        }

    }
}
