package com.example.spacex.ui.viewholder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.example.spacex.data.display.FlightItemDisplayModel;
import com.example.spacex.databinding.ItemFlightBinding;
import com.example.spacex.utils.listener.OnFlightClickListener;

public class FlightViewHolder extends RecyclerView.ViewHolder {

    private final ItemFlightBinding binding;
    private FlightItemDisplayModel model;

    public FlightViewHolder(@NonNull ItemFlightBinding binding,
                            @Nullable OnFlightClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        setupListener(listener);
    }

    public void bind(@NonNull FlightItemDisplayModel model) {
        this.model = model;
        binding.setModel(model);
    }

    private void setupListener(@Nullable OnFlightClickListener listener) {
        if (listener != null) {
            binding.getRoot().setOnClickListener((view) -> {
                if (model != null) {
                    listener.onFlightClick(model.getFlight());
                }
            });
        }
    }
}
