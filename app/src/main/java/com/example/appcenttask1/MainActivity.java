package com.example.appcenttask1;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt_search;
    ImageView img_clear_text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_search = findViewById(R.id.edt_search);


        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (actionId== EditorInfo.IME_ACTION_DONE ||
                        actionId== EditorInfo.IME_ACTION_SEARCH ||
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

        img_clear_text = findViewById(R.id.img_clear_text);
        img_clear_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_search.getText().clear();
            }
        });
    }



}