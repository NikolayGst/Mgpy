package ru.mgpy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.mgpy.Activities.Lesson.LessonActivity_;
import ru.mgpy.R;


public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private List<String> items;

    public GroupAdapter() {
        items = new ArrayList<>();
    }

    public void addGroup(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_item, parent, false);
        return new GroupAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GroupAdapter.ViewHolder holder, final int position) {
        String item = items.get(position);
        holder.txtGroup.setText(item);
        holder.txtGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LessonActivity_.intent(view.getContext()).group(items.get(position)).week("green").start();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public void clear() {
        if (items != null) {
            items.clear();
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtGroup;

        public ViewHolder(View itemView) {
            super(itemView);
            txtGroup = (TextView) itemView.findViewById(R.id.txtGroupSave);
        }
    }
}