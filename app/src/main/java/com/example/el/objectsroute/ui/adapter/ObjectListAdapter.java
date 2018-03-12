package com.example.el.objectsroute.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.el.objectsroute.R;
import com.example.el.objectsroute.dataclass.ObjectVisitation;

import java.util.List;

/**
 * Created by el on 01.03.2018.
 */

public class ObjectListAdapter extends RecyclerView.Adapter<ObjectListAdapter.ObjectViewHolder> {

    private List<ObjectVisitation> objects;

    public void setObjects(List<ObjectVisitation> objects) {
        this.objects = objects;
    }

    @Override
    public ObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ObjectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_object, parent, false));
    }

    @Override
    public void onBindViewHolder(final ObjectViewHolder holder, int position) {
        final ObjectVisitation object = objects.get(position);

        holder.addressTextView.setText(object.getAddress());
        holder.nameTextView.setText(object.getName());
        holder.workTextView.setText(object.getWork());
        holder.instrumentsTextView.setText(object.getInstruments());
        holder.priorityTextView.setText(object.getPriority());
        holder.visitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setVisited(true);
               // presenter.OnVisitTextViewClicked(object);
               // presenter.getVisitedObjects();
                view.setEnabled(false);
                holder.visitTextView.setText("Посещено");
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects == null ? 0 : objects.size();
    }

    class ObjectViewHolder extends RecyclerView.ViewHolder {

        private final TextView addressTextView;
        private final TextView nameTextView;
        private final TextView workTextView;
        private final TextView instrumentsTextView;
        private final TextView priorityTextView;
        private final TextView visitTextView;

        public ObjectViewHolder(View itemView) {
            super(itemView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            nameTextView = itemView.findViewById(R.id.objectNameTextView);
            workTextView = itemView.findViewById(R.id.workTextView);
            instrumentsTextView = itemView.findViewById(R.id.instrumentsTextView);
            priorityTextView = itemView.findViewById(R.id.priorityTextView);
            visitTextView = itemView.findViewById(R.id.visitTextView);
        }
    }
}
