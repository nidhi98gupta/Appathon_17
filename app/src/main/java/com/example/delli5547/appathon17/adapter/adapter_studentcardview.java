package com.example.delli5547.appathon17.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.delli5547.appathon17.R;
import com.example.delli5547.appathon17.model.student;

import java.util.List;

/**
 * Created by Nidhi on 09-09-2017.
 */

public class adapter_studentcardview extends RecyclerView.Adapter<adapter_studentcardview.MyViewHolder> {

    private List<student> studentList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView student_name, student_id, room_no, mobile, interests, expertise;
        ImageView call;


        public MyViewHolder(final View view) {
            super(view);
            call = (ImageView) view.findViewById(R.id.call);
            student_id = (TextView) view.findViewById(R.id.student_id);
            student_name = (TextView) view.findViewById(R.id.student_name);
            room_no = (TextView) view.findViewById(R.id.room_no);
            mobile = (TextView) view.findViewById(R.id.phone);
            interests = (TextView) view.findViewById(R.id.interest);
            expertise = (TextView) view.findViewById(R.id.expertise);

        }
    }


    public adapter_studentcardview(List<student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public adapter_studentcardview.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_cardview, parent, false);

        return new adapter_studentcardview.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(adapter_studentcardview.MyViewHolder holder, int position) {
        final student info = studentList.get(position);
        holder.student_id.setText(info.getStudent_id());
        holder.student_name.setText(info.getStudent_name());
        holder.interests.setText(info.getInterests());
        holder.interests.setText(info.getInterests());
        holder.mobile.setText(info.getMobile());
        holder.room_no.setText(info.getRoom_no());
        holder.expertise.setText(info.getResearch());


        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + Uri.encode(info.getMobile().trim())));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(callIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}

