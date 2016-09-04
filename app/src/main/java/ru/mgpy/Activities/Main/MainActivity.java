package ru.mgpy.Activities.Main;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Random;

import ru.mgpy.Activities.Lesson.LessonActivity_;
import ru.mgpy.R;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private int[] image = {R.drawable.bg, R.drawable.mdpy1, R.drawable.stud, R.drawable.stud2, R.drawable.stud3};
    private Random mRandom = new Random();

    @ViewById(R.id.bg)
    void setImage(ImageView background) {
        background.setImageResource(image[mRandom.nextInt(image.length)]);
    }

    @Click(R.id.redWeek)
    void loadRedWeek(){
        LessonActivity_.intent(this).week("red").start();
    }

    @Click(R.id.greenWeek)
    void loadGreenWeek(){
        LessonActivity_.intent(this).week("green").start();
    }

}
