package com.example.bazydanychslave;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private Snackbar mySnackbar;
    TextView ResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ResultTextView = (TextView) findViewById(R.id.textViewData);
    }

    @SuppressLint("Range")
    public void displayDataFromDB(){
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.bazydanych.user.provider/users"), null, null, null, null);

        if(cursor.moveToFirst()) {
            StringBuilder strBuild = new StringBuilder();
            strBuild.append("Id | Nazwa | Opis\n");
            while (!cursor.isAfterLast()) {
                strBuild.append(cursor.getString(cursor.getColumnIndex("id")) + " " + cursor.getString(cursor.getColumnIndex("name")) + " " + cursor.getString(cursor.getColumnIndex("description")) + "\n");

                cursor.moveToNext();
            }
            ResultTextView.setText(strBuild);
        }
        else {
            ResultTextView.setText("Brak wpisów w bazie!");
        }
    }

    public void buttonDisplayDataOnClick(View view){
        displayDataFromDB();
        mySnackbar = Snackbar.make(view, "Wyświetlono dane z bazy", 2000);
        mySnackbar.show();
    }

}