package lavin105.classorganizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Student extends Activity {

    Button cancel,add;
    EditText firstNme, lastNme, stuId;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student_layout);
        cancel=findViewById(R.id.cancelAddStudent);
        add=findViewById(R.id.addStudent);
        firstNme=findViewById(R.id.firstName);
        lastNme=findViewById(R.id.lastName);
        stuId=findViewById(R.id.studentId);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first=firstNme.getText().toString();
                String last=lastNme.getText().toString();
                String fullName=first+" " + last;
                String id=stuId.getText().toString();
                Intent i = new Intent();
                i.putExtra("name",fullName);
                i.putExtra("id", id);
                setResult(RESULT_OK,i);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
