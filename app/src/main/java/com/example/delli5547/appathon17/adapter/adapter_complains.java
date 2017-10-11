package com.example.delli5547.appathon17.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.delli5547.appathon17.R;
import com.example.delli5547.appathon17.model.complains;

import java.util.List;


/**
 * Created by rajan on 9/9/17.
 */

public class adapter_complains extends RecyclerView.Adapter<adapter_complains.MyViewHolder> {


    private List<complains> eventList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView std_id, complain_name, desc;
        Button solve;
        Button forward;

        public MyViewHolder(View view) {
            super(view);
            std_id = (TextView) view.findViewById(R.id.student_id);
            complain_name = (TextView) view.findViewById(R.id.complain_name_1);
            desc = (TextView) view.findViewById(R.id.description_main);

            solve = (Button) view.findViewById(R.id.solve);
            forward = (Button) view.findViewById(R.id.forward);
        }
    }

    public adapter_complains(List<complains> eventList) {
        this.eventList = eventList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_complains_cardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        complains complains = eventList.get(position);
        holder.std_id.setText(complains.getStd_id());
        holder.complain_name.setText(complains.getComplain_name());
        holder.desc.setText(complains.getDesc());

        holder.solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Solved ",Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}


