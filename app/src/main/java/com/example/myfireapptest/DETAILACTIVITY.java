package com.example.myfireapptest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DETAILACTIVITY extends AppCompatActivity {

    private ListView  list;
    private DatabaseReference databaseReference;
    private List<Student> studentList;
    private Custom_adapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);

        list=findViewById(R.id.listViewId);

        studentList=new ArrayList<>();
        customAdapter=new Custom_adapter(DETAILACTIVITY.this,studentList);

        databaseReference= FirebaseDatabase.getInstance().getReference("Students");


    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList.clear();
                for(DataSnapshot datasnapshot1:dataSnapshot.getChildren())
                {
                    Student student = datasnapshot1.getValue(Student.class);
                    studentList.add(student);
                }
                list.setAdapter(customAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }
}
