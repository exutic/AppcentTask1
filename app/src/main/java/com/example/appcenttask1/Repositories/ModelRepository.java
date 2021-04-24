package com.example.appcenttask1.Repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.appcenttask1.Model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton Pattern
 */
public class ModelRepository {
    private static ModelRepository instance;
    private ArrayList<Model> dataSet = new ArrayList<>();

    public static ModelRepository getInstance()
    {
        if (instance==null)
            instance = new ModelRepository();

        return instance;
    }

    //Get Data From a webservice or online source
    public MutableLiveData<List<Model>> getModels()
    {
        setModels();

        MutableLiveData<List<Model>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setModels()
    {
        //filling Model model = new Model();
        //from webservice
        //filling dataSet from webservice or offline repo
    }
}
