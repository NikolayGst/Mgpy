package ru.mgpy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.mgpy.Model.Schedule;
import ru.mgpy.R;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<Schedule> items;

    public void setItems(List<Schedule> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ScheduleAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {
        Schedule item = items.get(position);
        holder.lesson.setText(item.getLesson());
        if (item.getCab() != 1) holder.cab.setText("кб. " + item.getCab());
        else holder.cab.setText("");
        if (item.getCategory().equals("")) holder.category.setVisibility(View.GONE);
        holder.category.setText(item.getCategory());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lesson;
        TextView cab;
        TextView category;
        public ViewHolder(View itemView) {
            super(itemView);
            lesson = (TextView) itemView.findViewById(R.id.lesson);
            cab = (TextView) itemView.findViewById(R.id.cab);
            category = (TextView) itemView.findViewById(R.id.category);
        }
    }
}