package com.example.longdinh.tuan3_list.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by long dinh on 04/04/2016.
 */
public class Student implements Parcelable {
    private String id;
    private String name;

    public Student(String id, String name){
        this.id = id;
        this.name = name;
    }
    public Student(JSONObject data) throws JSONException {
        id = data.getString("id");
        name = data.getString("name");
    };

    protected Student(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getId(){        return this.id;    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){return this.name;}

    @Override
    public String toString() {
        return id + " - " + name;
    }

    public JSONObject toJSon(){
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("name", name);
            return jsonObject;
        }catch(JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Student> fromJson(JSONArray array) throws JSONException {
        ArrayList<Student> students = new ArrayList<Student>();
        for(int i = 0; i < array.length(); i++){
            Student student = new Student((JSONObject)array.get(i));
            students.add(student);
        };
        return students;
    }

    public static JSONArray toJson(List<Student> Students){
        JSONArray array = new JSONArray();
        for(Student student:Students){
            JSONObject item = student.toJSon();
            array.put(item);
        }
        return array;
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }

}
