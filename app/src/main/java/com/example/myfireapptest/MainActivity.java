package com.example.myfireapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name,age;
    private Button button,loadDatabtn;
    private EditText editText1,editText2;
    private Button addButton,subButton,divbutton,mulbutton;
    private TextView resultTextView;
    String h1,h2,h3;





    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference= FirebaseDatabase.getInstance().getReference("Students");





        editText1 =(EditText) findViewById(R.id.editText1Id);
        editText2 =(EditText) findViewById(R.id.editText2Id);

        loadDatabtn =(Button) findViewById(R.id.loadDataButtonId);
        addButton =(Button) findViewById(R.id.addButtonid);
        subButton =(Button) findViewById(R.id.subButtonid);
        mulbutton =(Button) findViewById(R.id.mulButtonid);
        divbutton =(Button) findViewById(R.id.divButtonid);

        resultTextView =(TextView) findViewById(R.id.resultTextViewId);

        addButton.setOnClickListener(this);
        subButton.setOnClickListener(this);
        divbutton.setOnClickListener(this);
        mulbutton.setOnClickListener(this);


        loadDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, DETAILACTIVITY.class);
                startActivity(intent);

            }
        });




    }







    @Override
    public void onClick(View v ) {

        try{
            String num1 =editText1.getText().toString();
            String num2 =editText2.getText().toString();

            double numb1=Double.parseDouble(num1);
            double numb2=Double.parseDouble(num2);

            if(v.getId()==R.id.addButtonid)
            {
                double res=numb1+numb2;
                resultTextView.setText("result = "+res);
                String result=Double.toString(res);
                String ss="+";
                savedata(ss,result);



            }
            else if(v.getId()==R.id.subButtonid)
            {

                double res=numb1-numb2;
                resultTextView.setText("result = "+res);
                String result=Double.toString(res);
                savedata("-",result);

            }
            else if(v.getId()==R.id.mulButtonid)
            {

                double res=numb1*numb2;
                resultTextView.setText("result = "+res);
                String result=Double.toString(res);
                savedata("X",result);

            }
            else if(v.getId()==R.id.divButtonid)
            {

                double res=numb1/numb2;
                resultTextView.setText("result = "+res);
                String result=Double.toString(res);
                 savedata("/",result);

            }


        }
        catch (Exception e){
            Toast.makeText(MainActivity.this,"please enter numbers",Toast.LENGTH_SHORT).show();
        }

    }



    public void savedata(String S,String res) {
        String name1=editText1.getText().toString().trim();
        String age1=editText2.getText().toString().trim();

        String Si=name1+" "+S+" "+age1+" = "+res;

        String key=databaseReference.push().getKey();

        Student student =new Student(Si);

        databaseReference.child(key).setValue(student);

        Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_SHORT).show();
    }


}
