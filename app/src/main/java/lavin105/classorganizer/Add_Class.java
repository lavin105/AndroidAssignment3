package lavin105.classorganizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Add_Class extends Activity {
    EditText studentName,className,classNumber;
    Button addStudent, submit;
    ListView studentList, classes;
    ArrayList<String> students;
    ArrayAdapter<String> adapter;

    String name,number;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class_layout);
        className=findViewById(R.id.class_name);
        classNumber=findViewById(R.id.class_number);
        studentList=findViewById(R.id.student_list);
        addStudent=findViewById(R.id.addStu);
        studentName=findViewById(R.id.student);
        submit=findViewById(R.id.add_class);
        students=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(Add_Class.this,android.R.layout.simple_list_item_1,students);
        studentList.setAdapter(adapter);

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String student=studentName.getText().toString();
                students.add(student);
                adapter.notifyDataSetChanged();
                studentName.setText("");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=className.getText().toString();
                number=classNumber.getText().toString();
                if(name.equals("")||number.equals("")){
                   Toast t= Toast.makeText(Add_Class.this,"Field Missing",Toast.LENGTH_SHORT);
                   t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,200);
                   t.show();
                }else{
                    Intent i = new Intent();
                    i.putExtra("className",name);
                    i.putExtra("classNumber",number);
                    i.putExtra("studentList",students);
                    setResult(RESULT_OK,i);
                    finish();
                }





            }
        });



    }
}
