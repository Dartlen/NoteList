package by.project.dartlen.notelist.base;

public interface BasePresenter<T> {
    void takeView(T view);
    void dropView();
}
