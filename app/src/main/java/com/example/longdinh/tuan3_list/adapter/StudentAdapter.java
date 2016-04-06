package com.example.longdinh.tuan3_list.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.longdinh.tuan3_list.R;
import com.example.longdinh.tuan3_list.activity.InformationActivity;
import com.example.longdinh.tuan3_list.activity.MainActivity;
import com.example.longdinh.tuan3_list.model.Student;

import java.util.List;

/**
 * Created by long dinh on 04/04/2016.
 */
public class StudentAdapter extends BaseAdapter {
    List<Student> listStudent;
    Context context;

    public StudentAdapter(Context context, List<Student> listStudent){
        this.listStudent = listStudent;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listStudent.size();
    }

    @Override
    public Object getItem(int position) {
        return listStudent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        convertView = View.inflate(context, R.layout.student_item, null);
        TextView tvId = (TextView) convertView.findViewById(R.id.tvId);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        Button btnDelete = (Button) convertView.findViewById(R.id.btnDelete);
        Button btnEdit = (Button) convertView.findViewById(R.id.btnEdit);
        final Context thiscontext = context;
        final String id = listStudent.get(position).getId();
        final String name = listStudent.get(position).getName();
        final int Position = position;
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editInfor = new Intent(thiscontext, InformationActivity.class);
                editInfor.putExtra("id", id);
                editInfor.putExtra("name", name);
                ((MainActivity)thiscontext).setPosition_current(Position);
                ((MainActivity)thiscontext).startActivityForResult(editInfor,2);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)thiscontext).removeOneItem(Position);
            }
        });
        Student student = listStudent.get(position);
        tvId.setText(student.getId());
        tvName.setText(student.getName());

        return convertView;
    }
}
