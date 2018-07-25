package com.example.spacex.ui.viewholder;

import android.support.v7.widget.RecyclerView;

import com.example.spacex.data.display.FlightItemDisplayModel;
import com.example.spacex.databinding.ItemFlightBinding;

public class FlightViewHolder extends RecyclerView.ViewHolder {

    private final ItemFlightBinding binding;

    public FlightViewHolder(ItemFlightBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(FlightItemDisplayModel model) {
        binding.setModel(model);
    }
}
