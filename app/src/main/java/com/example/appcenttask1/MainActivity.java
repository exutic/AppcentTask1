package com.example.appcenttask1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcenttask1.Adapters.RecyclerAdapter;
import com.example.appcenttask1.Model.Model;
import com.example.appcenttask1.ViewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    boolean net;
    EditText edt_search;
    ImageView img_clear_text;
    ArrayList<Model> ModelArrayList;
    ProgressBar mProgressBar;
    RecyclerAdapter adapter;
    RecyclerView recyclerView;

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsAndInitialize();
        internetCheck();


    }

    public void findViewsAndInitialize() {
        ModelArrayList = new ArrayList<>();
        mProgressBar = findViewById(R.id.progress_bar);
        edt_search = findViewById(R.id.edt_search);
        img_clear_text = findViewById(R.id.img_clear_text);

        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_SEARCH ||
                        event != null &&
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {

                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                        // the user is done typing.
                        Toast.makeText(MainActivity.this, edt_search.getText(), Toast.LENGTH_SHORT).show();
                        return true; // consume.
                    }
                }
                return false;
            }
        });

        img_clear_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_search.getText().clear();
            }
        });


        viewModelActions();

        initRecyclerView();

    }

    public boolean NetworkEnabled(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public void internetCheck() {
        //Check after webservice call... its to better to handle it after that
        net = NetworkEnabled(this);
        if (net) {
            Toast.makeText(this, "Internet Connected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: Done");
        recyclerView = findViewById(R.id.rcl_main_1);
        adapter = new RecyclerAdapter(this, viewModel.getModels().getValue());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    public void viewModelActions() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
//        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewModel.init();
        viewModel.getModels().observe(this, new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> models) {
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showProgressBar();
                } else {
                    hideProgressBar();
                    recyclerView.smoothScrollToPosition(viewModel.getModels().getValue().size() - 1);
                }
            }
        });
    }


}