package com.example.myfireapptest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Custom_adapter  extends ArrayAdapter<Student> {

    private Activity context;
    private List<Student> studentList;

    public Custom_adapter(Activity context,List<Student> studentList) {
        super(context, R.layout.layout, studentList);
        this.context =context;
        this.studentList =studentList;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.layout,null,true);

        Student student=studentList.get(position);

        TextView T1 = view.findViewById(R.id.historyID);

        T1.setText("History " + student.getHistory());

        return view;
    }
}
