package com.example.bazydanych;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText NameInput, DescriptionInput;
    TextView DataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NameInput = (EditText) findViewById(R.id.editTextTextName);
        DescriptionInput = (EditText) findViewById(R.id.editTextTextDescription);
        DataView = (TextView) findViewById(R.id.textViewData);
    }

    public void butonInsertDataOnClick(View view){

    }

    public void butonDisplayDataOnClick(View view){

    }
}