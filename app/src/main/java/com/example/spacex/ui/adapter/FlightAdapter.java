package com.example.spacex.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.spacex.R;
import com.example.spacex.data.display.FlightItemDisplayModel;
import com.example.spacex.ui.viewholder.FlightViewHolder;

import java.util.ArrayList;
import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightViewHolder> {

    private final List<FlightItemDisplayModel> flightItems = new ArrayList<>();

    public void setItems(@NonNull List<FlightItemDisplayModel> items) {
        flightItems.clear();
        flightItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new FlightViewHolder(DataBindingUtil.inflate(inflater,
                R.layout.item_flight, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        holder.bind(flightItems.get(position));
    }

    @Override
    public int getItemCount() {
        return flightItems.size();
    }
}
