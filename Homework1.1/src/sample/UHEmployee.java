package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UHEmployee implements Employee, Human
{
    public String name;
    public int id;
    private boolean isActive;
    private ObservableList<UHEmployee> list = FXCollections.observableArrayList();

    @Override
    public void hire()
    {
        isActive = true;
    }

    @Override
    public void fire()
    {
        isActive = false;
    }

    @Override
    public void die() {isActive = false;

    }

    @Override
    public String toString()
    {
        return name;
    }


    public ObservableList<UHEmployee> getList() {
        return list;
    }

    public void setList(ObservableList<UHEmployee> list) {
        this.list = list;
    }
}
