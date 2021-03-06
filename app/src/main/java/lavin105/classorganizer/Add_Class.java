/*Brandon Lavinsky
 * lavin105@mail.chapman.edu
 * Add_Class.java*/

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

/* This activity is where you specify the class name and number as well as have the ability to add students
* If add students is selected you will be redirected to the add student activity where you will input the information
* Students cannot be deleted in this activity but rather in the class info activity*/

public class Add_Class extends Activity {
    EditText studentName,className,classNumber;
    Button addStudent, submit;
    ListView studentList, classes;
    ArrayList<String> students;
    ArrayAdapter<String> adapter;
    final int REQUEST_CODE_3=3;

    String name,number;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class_layout);
        className=findViewById(R.id.class_name);
        classNumber=findViewById(R.id.class_number);
        studentList=findViewById(R.id.student_list);
        addStudent=findViewById(R.id.addStu);
        submit=findViewById(R.id.add_class);
        students=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(Add_Class.this,android.R.layout.simple_list_item_1,students);
        studentList.setAdapter(adapter);

        //adding a student
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent newStudentIntent = new Intent(Add_Class.this,Add_Student.class);
               startActivityForResult(newStudentIntent,REQUEST_CODE_3);

            }
        });

        //adding a class to your class list
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
    //onActivityResult for retrieving data from the add student activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_3){
            if(resultCode==RESULT_OK){


                System.out.println("-------------------------------");
                System.out.println(data.getStringExtra("name"));
                System.out.println(data.getStringExtra("id"));
                students.add(data.getStringExtra("name"));
                studentList.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }
        }
    }
}
