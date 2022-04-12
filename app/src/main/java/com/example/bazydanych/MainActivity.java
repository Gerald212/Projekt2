package com.example.bazydanych;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

//Na androidzie 11+ trzeba do manifestu dodac
// <queries>
//<package android:name="nazwa.providera" />
//</queries>

public class MainActivity extends AppCompatActivity {

    EditText NameInput, DescriptionInput, IdToDeleteInput;
    TextView ResultTextView;
    private Snackbar mySnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NameInput = (EditText) findViewById(R.id.editTextTextName);
        DescriptionInput = (EditText) findViewById(R.id.editTextTextDescription);
        IdToDeleteInput = (EditText) findViewById(R.id.editTextTextDeleteId);
        ResultTextView = (TextView) findViewById(R.id.textViewData);

    }

    public void buttonInsertDataOnClick(View view){
        ContentValues values = new ContentValues();

        // fetching text from user
        values.put(MyContentProvider.name, (NameInput.getText().toString()));
        values.put(MyContentProvider.description, (DescriptionInput.getText().toString()));

        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

        mySnackbar = Snackbar.make(view, "Dodano użytkownika", 2000);
        mySnackbar.show();

        displayDataFromDB();
        NameInput.setText("");
        DescriptionInput.setText("");
    }

//    public void buttonDeleteDataOnClick(View view){
//        int id = Integer.parseInt(IdToDeleteInput.getText().toString());
//        getContentResolver().delete(MyContentProvider.CONTENT_URI);
//    }

    @SuppressLint("Range")
    public void displayDataFromDB(){
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.bazydanych.user.provider/users"), null, null, null, null);

        // iteration of the cursor
        // to print whole table
        if(cursor.moveToFirst()) {
            StringBuilder strBuild = new StringBuilder();
            strBuild.append("Id | Nazwa | Opis\n");
            while (!cursor.isAfterLast()) {
                //strBuild.append("\n" + cursor.getString(cursor.getColumnIndex("id")) + "-" + cursor.getString(cursor.getColumnIndex("name")));
                //strBuild.append("Id: " + cursor.getString(cursor.getColumnIndex("id")) + " Nazwa: " + cursor.getString(cursor.getColumnIndex("name")) + " Opis: " + cursor.getString(cursor.getColumnIndex("description")) + "\n");
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