package ru.mgpy.Activities.Main;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;
import java.util.Random;

import ru.mgpy.Activities.Lesson.LessonActivity_;
import ru.mgpy.Activities.Main.Presenter.MainPresenter;
import ru.mgpy.Activities.Main.Presenter.MainPresenterImpl;
import ru.mgpy.Activities.Main.View.MainView;
import ru.mgpy.R;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MainView {

    //private int[] image = {R.drawable.bg, R.drawable.mdpy1};
    private Random mRandom = new Random();
    private String group;

    @ViewById
    Button btnShowLessons;

    @ViewById
    android.support.v7.widget.AppCompatSpinner spinSelectFac;

    @ViewById
    android.support.v7.widget.AppCompatSpinner spinSelectChair;

    @ViewById
    android.support.v7.widget.AppCompatSpinner spinSelectGroup;

   /* @ViewById
    AppCompatRadioButton first;

    @ViewById
    AppCompatRadioButton second;*/

  /*  @ViewById(R.id.bg)
    void setImage(ImageView background) {
        background.setImageResource(image[mRandom.nextInt(image.length)]);
    }*/

 /*   @AfterViews
    void init() {
        *//*first.setChecked(false);
        second.setChecked(false);
        first.setOnClickListener(this);
        second.setOnClickListener(this);*//*
    }*/

  /*  @Click(R.id.redWeek)
    void loadRedWeek() {
        if (group == 0) Toast.makeText(MainActivity.this, "Выберите подгруппу", Toast.LENGTH_SHORT).show();
        else LessonActivity_.intent(this).week("red").group(group).start();
    }

    @Click(R.id.greenWeek)
    void loadGreenWeek() {
        if (group == 0) Toast.makeText(MainActivity.this, "Выберите подгруппу", Toast.LENGTH_SHORT).show();
        else LessonActivity_.intent(this).week("green").group(group).start();
    }
*/
  /*  @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.first:
                second.setChecked(false);
                group = 1;
                break;
            case R.id.second:
                first.setChecked(false);
                group = 2;
                break;
        }
    }*/

    private ArrayAdapter<String> facAdapter;
    private ArrayAdapter<String> chairAdapter;
    private ArrayAdapter<String> groupAdapter;

    private MainPresenter mMainPresenter;
    private ProgressDialog mProgressDialog;

    @AfterViews
    void init() {

        initProgressDialog();

        initAdapter();

        mMainPresenter = new MainPresenterImpl(this);

        mMainPresenter.getFac();

    }

    private void initAdapter() {
        facAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        facAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSelectFac.setAdapter(facAdapter);

        chairAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        chairAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSelectChair.setAdapter(chairAdapter);

        groupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSelectGroup.setAdapter(groupAdapter);

        spinSelectChair.setEnabled(false);
        spinSelectGroup.setEnabled(false);

        spinSelectFac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                chairAdapter.clear();
                chairAdapter.notifyDataSetChanged();
                groupAdapter.clear();
                groupAdapter.notifyDataSetChanged();

                if (i != 0) {
                    mMainPresenter.getChair(i);
                    spinSelectChair.setEnabled(true);
                } else {
                    spinSelectChair.setEnabled(false);
                    spinSelectGroup.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinSelectChair.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                groupAdapter.clear();
                groupAdapter.notifyDataSetChanged();

                if (i != 0) {
                    mMainPresenter.getGroup(i);
                    spinSelectGroup.setEnabled(true);
                } else {
                    spinSelectGroup.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinSelectGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                   group = (String) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Click(R.id.btnShowLessons)
    void showLessons() {
        if (group != null && !group.equals("Оберіть вашу групу") && !group.equals(""))
        LessonActivity_.intent(this).group(group).week("red").start();
        else
            Toast.makeText(this, "Оберіть, будь-ласка, вашу групу", Toast.LENGTH_SHORT).show();
    } 

    public void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
    }


    @Override
    public void showProgressDialog(String text) {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(text);
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void OnLoadFac(List<String> facList) {
        facAdapter.addAll(facList);
        facAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnLoadChair(List<String> chairList) {
        chairAdapter.addAll(chairList);
        chairAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnLoadGroup(List<String> groupList) {
        groupAdapter.addAll(groupList);
        groupAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnErrorLoaded(Exception ex) {

    }
}
