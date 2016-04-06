package com.example.longdinh.tuan3_list.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.longdinh.tuan3_list.R;
import com.example.longdinh.tuan3_list.adapter.StudentAdapter;
import com.example.longdinh.tuan3_list.model.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    private static final String DATA_KEY = "students";
    private static final String PREF_KEY = "json_data";
    private Button btnAddnew;
    private ListView lvStudents;
    private ArrayList<Student> lStudents;
    private StudentAdapter adapter;
    private SharedPreferences preferences;
    private int position_current;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddnew = (Button)findViewById(R.id.btnAddnew);
        lvStudents = (ListView) findViewById(R.id.lvStudents);
        JSONObject json = new JSONObject();


//        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
//        String prefData = preferences.getString("json_data", null);
//
//        if (savedInstanceState != null) {
//            lStudents = savedInstanceState.getParcelableArrayList(DATA_KEY);
//        } else if (prefData != null) {
//            try {
//                JSONArray array = new JSONArray(prefData);
//                lStudents = Student.fromJson(array);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        } else {
            lStudents = new ArrayList<Student>();
            lStudents.add(new Student("1", "A"));
            lStudents.add(new Student("2", "B"));
            lStudents.add(new Student("3", "C"));
            lStudents.add(new Student("4", "D"));
            lStudents.add(new Student("5", "E"));
//        }


        adapter = new StudentAdapter(this,lStudents);
        lvStudents.setAdapter(adapter);

        lvStudents.setOnItemClickListener(this);
        lvStudents.setOnItemLongClickListener(this);

    }

    public void setPosition_current(int position_current){
        this.position_current = position_current;
    };

    public int getPosition_current(){
        return position_current;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            String id  = data.getStringExtra("id");
            String name = data.getStringExtra("name");
            lStudents.add(new Student(id, name));
            adapter.notifyDataSetChanged();
        }else if(requestCode == 2 && resultCode == RESULT_OK){
            lStudents.get(getPosition_current()).setId(data.getStringExtra("id"));
            lStudents.get(getPosition_current()).setName(data.getStringExtra("name"));
            adapter.notifyDataSetChanged();
        }
    }

    public void btnAddnewEvent(View v){
//        Intent information = new Intent(this, InformationActivity.class);
//        startActivityForResult(information, 1);
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
        String url="https://192.168.43:8000/api/v1/products/abc";
    }

    @Override
    public void    startActivityForResult(Intent intent, int requestCode){
        intent.putExtra("requestCode", requestCode);
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Student student = lStudents.get(position);
        String  message = "Choise: " + student.toString();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();


    }


    public void removeOneItem(int position0){
        if(showConfirm( position0)){

        }
    }

    public boolean showConfirm(int position){
//        final boolean[] ret = new boolean[1];
//        ret[0] = true;
//        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setMessage(message)
//                .setTitle("CONFIRM")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ret[0] = true;
//                    }
//                })
//                .setNegativeButton("No", null).show();
//        return ret[0];

        final boolean[] answer = new boolean[1];
        answer[0]= true;
        final int position_i = position;
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("Confirmation");
        dialog.setMessage("Choose Yes or No");
        dialog.setCancelable(false);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonId) {
                lStudents.remove(position_i);
                adapter.notifyDataSetChanged();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonId) {
                answer[0] = false;
            }
        });
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.show();
        return answer[0];
    };

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Student student = lStudents.get(position);
        String message = "Romove: " + student.toString();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        lStudents.remove(position);
        adapter.notifyDataSetChanged();
        return false;
    }


//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putParcelableArrayList(DATA_KEY, lStudents);
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        String data = Student.toJson(lStudents).toString();
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString(PREF_KEY, data).commit();
//    }
}
