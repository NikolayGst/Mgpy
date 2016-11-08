package ru.mgpy.Activities.Lesson;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import ru.mgpy.Activities.Lesson.Presenter.LessonPresenter;
import ru.mgpy.Activities.Lesson.Presenter.LessonPresenterImpl;
import ru.mgpy.Activities.Lesson.View.LessonView;
import ru.mgpy.Adapter.ScheduleAdapter;
import ru.mgpy.Model.Schedule;
import ru.mgpy.R;

@EFragment(R.layout.fragment_lesson)
public class LessonListFragment extends Fragment implements LessonView {

    @ViewById
    RecyclerView recycler;

    private LessonPresenter mLessonPresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ScheduleAdapter mScheduleAdapter;

    public static LessonListFragment_ newInstance(int position, String group, String week) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("group", group);
        args.putString("week", week);
        LessonListFragment_ fragment = new LessonListFragment_();
        fragment.setArguments(args);
        return fragment;
    }

    @AfterViews
    void init() {

        initRecycler();

        Bundle bundle = getArguments();
        if (bundle != null) {
            mLessonPresenter = new LessonPresenterImpl(this);
            mLessonPresenter.getLesson(bundle.getInt("position"), bundle.getString("group"), bundle.getString("week"));
        }
    }

    private void initRecycler() {
        mScheduleAdapter = new ScheduleAdapter();
        mLayoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(mScheduleAdapter);
    }

    @Override
    public void onLessonLoaded(List<Schedule> schedules) {
        mScheduleAdapter.setItems(schedules);
    }


}
