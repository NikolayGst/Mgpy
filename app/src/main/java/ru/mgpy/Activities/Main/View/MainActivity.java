package ru.mgpy.Activities.Main.View;

import android.app.ProgressDialog;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.mgpy.Activities.Lesson.View.LessonActivity_;
import ru.mgpy.Activities.Main.Presenter.MainPresenter;
import ru.mgpy.Activities.Main.Presenter.MainPresenterImpl;
import ru.mgpy.Adapter.GroupAdapter;
import ru.mgpy.Model.DB.Group;
import ru.mgpy.R;

import static ru.mgpy.R.id.lesson;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MainView {

    private String group;

    @ViewById
    TextView txtGroupSave;

    @ViewById
    android.support.v7.widget.AppCompatSpinner spinSelectFac;

    @ViewById
    android.support.v7.widget.AppCompatSpinner spinSelectChair;

    @ViewById
    android.support.v7.widget.AppCompatSpinner spinSelectGroup;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById
    RecyclerView recycler;

    private GroupAdapter mGroupAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayAdapter<String> facAdapter;
    private ArrayAdapter<String> chairAdapter;
    private ArrayAdapter<String> groupAdapter;

    private MainPresenter mMainPresenter;
    private ProgressDialog mProgressDialog;

    @AfterViews
    void init() {

        initProgressDialog();

        initAdapter();

        initRecycler();

        mMainPresenter = new MainPresenterImpl(this);

        mMainPresenter.getFac();

    }

    private void initRecycler() {
        mLayoutManager = new LinearLayoutManager(this);
        mGroupAdapter = new GroupAdapter();
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(mGroupAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGroupAdapter.clear();
        RealmResults<Group> groupRealmList = Realm.getDefaultInstance().where(Group.class).findAll();
        if (groupRealmList.size() != 0) {
            for (Group group : groupRealmList) {
                mGroupAdapter.addGroup(group.getGroup());
            }
        }
    }

    @Click(R.id.txtGroupSave)
    void click() {
        if (txtGroupSave.getText().length() != 0)
            LessonActivity_.intent(this).group(txtGroupSave.getText().toString()).week("green").start();
    }

    @Click(R.id.nav)
    void openMenu() {
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
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
            mMainPresenter.loadGroupLesson(group);
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
    public void onLoadFac(List<String> facList) {
        facAdapter.addAll(facList);
        facAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadChair(List<String> chairList) {
        chairAdapter.addAll(chairList);
        chairAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadGroup(List<String> groupList) {
        groupAdapter.addAll(groupList);
        groupAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadLesson(String group) {
        System.out.println(lesson);
        LessonActivity_.intent(this).group(group).week("green").start();
    }

    @Override
    public void onErrorLoaded(Throwable t) {
        Toast.makeText(this, "Помилка, перевірте доступ до інтернету.", Toast.LENGTH_SHORT).show();
    }
}
