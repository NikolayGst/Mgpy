package ru.mgpy.Activities.Main;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Random;

import ru.mgpy.Activities.Lesson.LessonActivity_;
import ru.mgpy.R;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int[] image = {R.drawable.bg, R.drawable.mdpy1};
    private Random mRandom = new Random();
    private int group;

    @ViewById
    AppCompatRadioButton first;

    @ViewById
    AppCompatRadioButton second;

    @ViewById(R.id.bg)
    void setImage(ImageView background) {
        background.setImageResource(image[mRandom.nextInt(image.length)]);
    }

    @AfterViews
    void init() {
        first.setChecked(false);
        second.setChecked(false);
        first.setOnClickListener(this);
        second.setOnClickListener(this);
    }

    @Click(R.id.redWeek)
    void loadRedWeek() {
        if (group == 0) Toast.makeText(MainActivity.this, "Выберите подгруппу", Toast.LENGTH_SHORT).show();
        else LessonActivity_.intent(this).week("red").group(group).start();
    }

    @Click(R.id.greenWeek)
    void loadGreenWeek() {
        if (group == 0) Toast.makeText(MainActivity.this, "Выберите подгруппу", Toast.LENGTH_SHORT).show();
        else LessonActivity_.intent(this).week("green").group(group).start();
    }

    @Override
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
    }
}
