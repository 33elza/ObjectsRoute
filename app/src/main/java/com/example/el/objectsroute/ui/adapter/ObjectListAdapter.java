package com.example.el.objectsroute.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.el.objectsroute.App;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.PriorityType;

import java.util.List;

/**
 * Created by el on 01.03.2018.
 */

public class ObjectListAdapter extends RecyclerView.Adapter<ObjectListAdapter.ObjectViewHolder> {

    private List<ObjectVisitation> objects;

    private Listener listener;

    public ObjectListAdapter(@NonNull Listener listener) {
        this.listener = listener;
    }

    public void setObjects(List<ObjectVisitation> objects) {
        this.objects = objects;
    }

    @Override
    public ObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ObjectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_object, parent, false));
    }

    @Override
    public void onBindViewHolder(final ObjectViewHolder holder, int position) {
        holder.index = position;

        final ObjectVisitation object = objects.get(position);

        final String priority = App.getAppContext().getString(R.string.priority, App.getAppContext().getString(object.getPriority() == PriorityType.HIGH ? R.string.priority_high : R.string.priority_low));

        holder.addressTextView.setText(object.getAddress());
        holder.nameTextView.setText(object.getName());
        holder.workTextView.setText(object.getWork());
        holder.instrumentsTextView.setText(object.getInstruments());
        holder.priorityTextView.setText(priority);
        holder.visitTextView.setEnabled(!object.isVisited());
        holder.visitTextView.setText(object.isVisited() ? R.string.is_visited_text : R.string.visit_text);

        if (object.isVisited()) {
            holder.priorityLineView.setBackgroundColor(App.getAppContext().getResources().getColor(R.color.colorVisited));
            holder.priorityTextView.setTextColor(App.getAppContext().getResources().getColor(R.color.colorVisited));
        } else if (object.getPriority() == PriorityType.HIGH) {
            holder.priorityLineView.setBackgroundColor(App.getAppContext().getResources().getColor(R.color.colorPriorityHigh));
            holder.priorityTextView.setTextColor(App.getAppContext().getResources().getColor(R.color.colorPriorityHigh));
        } else {
            holder.priorityLineView.setBackgroundColor(App.getAppContext().getResources().getColor(R.color.colorPriorityLow));
            holder.priorityTextView.setTextColor(App.getAppContext().getResources().getColor(R.color.colorPriorityLow));
        }
    }

    @Override
    public int getItemCount() {
        return objects == null ? 0 : objects.size();
    }

    public interface Listener {
        void onVisitObjectClick(ObjectVisitation object, int index);
    }

    class ObjectViewHolder extends RecyclerView.ViewHolder {

        private final TextView addressTextView;
        private final TextView nameTextView;
        private final TextView workTextView;
        private final TextView instrumentsTextView;
        private final TextView priorityTextView;
        private final TextView visitTextView;
        private final View priorityLineView;
        private int index;

        ObjectViewHolder(View itemView) {
            super(itemView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            nameTextView = itemView.findViewById(R.id.objectNameTextView);
            workTextView = itemView.findViewById(R.id.workTextView);
            instrumentsTextView = itemView.findViewById(R.id.instrumentsTextView);
            priorityTextView = itemView.findViewById(R.id.priorityTextView);
            visitTextView = itemView.findViewById(R.id.visitTextView);

            visitTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onVisitObjectClick(objects.get(index), index);
                }
            });
            priorityLineView = itemView.findViewById(R.id.priorityLineView);
        }
    }
}
