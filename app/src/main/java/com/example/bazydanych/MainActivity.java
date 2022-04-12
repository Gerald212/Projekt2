package com.example.bazydanych;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText NameInput, DescriptionInput;
    TextView ResultTextView;
    private Snackbar mySnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NameInput = (EditText) findViewById(R.id.editTextTextName);
        DescriptionInput = (EditText) findViewById(R.id.editTextTextDescription);
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
    }

    public void buttonDisplayDataOnClick(View view){
        
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.user.provider/users"), null, null, null, null);

        // iteration of the cursor
        // to print whole table
        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n" + cursor.getString(cursor.getColumnIndex("id")) + "-" + cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
            ResultTextView.setText(strBuild);
        }
        else {
            ResultTextView.setText("No Records Found");
        }
    }
}