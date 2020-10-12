package com.example.diabetescarelink.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diabetescarelink.Models.OrdersModel;
import com.example.diabetescarelink.R;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.PatientsViewHolder> {
    Context context;
    List<OrdersModel> mData;


    public OrdersAdapter(Context context, List<OrdersModel> mData){
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public OrdersAdapter.PatientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.orders,null,false);
        return new PatientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.PatientsViewHolder holder, final int position) {
        OrdersModel ordersModel = mData.get(position);
        holder.drugholder.setText(mData.get(position).getDrugname());
        holder.quantityholder.setText(mData.get(position).getQuantity());
        holder.totalprice.setText(mData.get(position).getCost());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PatientsViewHolder extends RecyclerView.ViewHolder {

        TextView drugholder,patientholder,contactholder,locationholder,quantityholder,totalprice;
        TextView drug,Patientname,contact,location,quantity;
        CardView patient_card;
        Button submit;
        public PatientsViewHolder(@NonNull View itemView) {
            super(itemView);

            patient_card = itemView.findViewById(R.id.patient_card);
            drugholder = itemView.findViewById(R.id.drugholder);
            patientholder = itemView.findViewById(R.id.patientholder);
            contactholder = itemView.findViewById(R.id.contactholder);
            locationholder = itemView.findViewById(R.id.locationholder);
            quantityholder = itemView.findViewById(R.id.quantityholder);
            totalprice = itemView.findViewById(R.id.totalprice);

            drug = itemView.findViewById(R.id.drug);
            Patientname = itemView.findViewById(R.id.Patientname);
            contact = itemView.findViewById(R.id.contact);
            location = itemView.findViewById(R.id.location);
            quantity = itemView.findViewById(R.id.quantity);

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
