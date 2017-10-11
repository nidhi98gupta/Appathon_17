package com.example.delli5547.appathon17.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.delli5547.appathon17.R;
import com.example.delli5547.appathon17.model.event;

import java.util.List;


/**
 * Created by rajan on 9/9/17.
 */

public class adapter_event extends RecyclerView.Adapter<adapter_event.MyViewHolder> {


    private List<event> eventList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView event_name, start_time, date, location, end_time, day, organizer;
//        Button btndate;

        public MyViewHolder(View view) {
            super(view);
//            btndate = (Button) view.findViewById(R.id.date);
            event_name = (TextView) view.findViewById(R.id.event_name);
            date = (TextView) view.findViewById(R.id.event_date);
            start_time = (TextView) view.findViewById(R.id.start_time);
            location = (TextView) view.findViewById(R.id.event_location);
            end_time = (TextView) view.findViewById(R.id.end_time);
            organizer = (TextView) view.findViewById(R.id.organizer);
            day = (TextView) view.findViewById(R.id.event_day);
        }
    }

    public adapter_event(List<event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_event_cardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        event event = eventList.get(position);
        holder.event_name.setText(event.getEvent_name());
        holder.date.setText(event.getDate());
        holder.start_time.setText(event.getStart_time());
        holder.end_time.setText(event.getEnd_time());
        holder.organizer.setText(event.getOrganizer());
        holder.day.setText(event.getDay());
        holder.location.setText(event.getLocation());



    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}


