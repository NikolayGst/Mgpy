package ru.mgpy.Activities.Main.Presenter;

import java.util.List;

import ru.mgpy.Activities.Main.Interactor.MainInteractor;
import ru.mgpy.Activities.Main.Interactor.OfflineMainInteractorImpl;
import ru.mgpy.Activities.Main.View.MainView;

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnLoadResponseListener {

    private MainView mMainView;
    private MainInteractor mMainInteractor;

    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
        mMainInteractor = new OfflineMainInteractorImpl(this);
    }

    @Override
    public void getFac() {
        mMainView.showProgressDialog("Завантаження факультетів...");
        mMainInteractor.loadFacData();
    }

    @Override
    public void getChair(int idFac) {
        mMainView.showProgressDialog("Завантаження кафедр...");
        mMainInteractor.loadChairData(idFac);
    }

    @Override
    public void getGroup(int idChair) {
        mMainView.showProgressDialog("Завантаження груп...");
        mMainInteractor.loadGroupData(idChair);
    }

    @Override
    public void loadGroupLesson(String group) {
        mMainView.showProgressDialog("Завантаження розкладу...");
        mMainInteractor.loadGroupLesson(group);
    }

    //Методы после удачной загрузки
    @Override
    public void OnLoadFac(List<String> facList) {
        mMainView.hideProgressDialog();
        mMainView.onLoadFac(facList);
    }

    @Override
    public void OnLoadChair(List<String> chairList) {
        mMainView.hideProgressDialog();
        mMainView.onLoadChair(chairList);
    }

    @Override
    public void OnLoadGroup(List<String> groupList) {
        mMainView.hideProgressDialog();
        mMainView.onLoadGroup(groupList);
    }

    @Override
    public void OnLoadLesson(String group) {
        mMainView.hideProgressDialog();
        mMainView.onLoadLesson(group);
    }

    @Override
    public void OnError(Throwable t) {
        mMainView.hideProgressDialog();
        mMainView.onErrorLoaded(t);
    }
}
