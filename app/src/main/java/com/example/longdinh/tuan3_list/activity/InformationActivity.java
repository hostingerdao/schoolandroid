package com.example.longdinh.tuan3_list.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.longdinh.tuan3_list.R;

/**
 * Created by long dinh on 04/04/2016.
 */
public class InformationActivity extends Activity{
    private EditText etId;
    private EditText etName;
    private EditText etEmail;
    private Button btnCancel;
    private Button btnOk;
    private String id_old;
    private String name_old;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnOk = (Button) findViewById(R.id.btnOk);
        Intent main = getIntent();
        if(main.getIntExtra("requestCode",0) == 2){
            id_old = main.getStringExtra("id");
            name_old = main.getStringExtra("name");
            etId.setText(id_old);
            etName.setText(name_old);
        }
    }

    public void btnOkEvent(View v){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("id", etId.getText().toString());
        returnIntent.putExtra("name", etName.getText().toString());
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void btnCancelEvent(View v){
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }
}
