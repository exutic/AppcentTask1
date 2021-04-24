package com.example.appcenttask1.ViewModel;

import android.os.AsyncTask;
import android.view.Display;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appcenttask1.Model.Model;
import com.example.appcenttask1.Repositories.ModelRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<Model>> mModel;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();


    public void init()
    {
        if (mModel!=null)
        {
            return;
        }
        ModelRepository mRepo = ModelRepository.getInstance();
        mModel = mRepo.getModels();
    }

    public LiveData<List<Model>> getModels()
    {
        return mModel;
    }

    public  void addNewValue(final Model newModel)
    {
        mIsUpdating.setValue(true);

        new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<Model> currentModel = mModel.getValue();
                currentModel.add(newModel);
                mModel.postValue(currentModel);
                mIsUpdating.postValue(false);
            }
        }.execute();
    }

    public LiveData<Boolean> getIsUpdating()
    {
        return mIsUpdating;
    }
}
