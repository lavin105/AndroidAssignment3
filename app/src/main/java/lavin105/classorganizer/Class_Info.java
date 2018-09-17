package lavin105.classorganizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Class_Info extends Activity {
    TextView theclass,theclassnumber;
    Button toClassList, editCName, editCNumber, delete;
    ListView students;
    ArrayAdapter<String> allStudents;
    String cName,cNumber;
    EditText alert1input;
    EditText alert2input;
    int pos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info_layout);
        delete=findViewById(R.id.deleteClass);
        editCName=findViewById(R.id.editName);
        editCNumber=findViewById(R.id.editNumber);
        students=findViewById(R.id.students_list);
        toClassList=findViewById(R.id.toClasses);
         theclass =findViewById(R.id.className);
         theclassnumber=findViewById(R.id.classNumber);

        AlertDialog.Builder alert= new AlertDialog.Builder(Class_Info.this);
        alert.setTitle("Edit Class Name");
        alert.setMessage("Please enter the new class name");
        alert1input=new EditText(Class_Info.this);
        alert2input=new EditText(Class_Info.this);

        alert.setView(alert1input);
        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newClassName=alert1input.getText().toString();
                theclass.setText(newClassName);
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog theAlert=alert.create();



        AlertDialog.Builder alert2= new AlertDialog.Builder(Class_Info.this);
        alert2.setTitle("Edit Class Number");
        alert2.setMessage("Please enter the new class number");
        alert2input=new EditText(Class_Info.this);
        alert2.setView(alert2input);
        alert2.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newClassNumber=alert2input.getText().toString();
                theclassnumber.setText(newClassNumber);
            }
        });
        alert2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog theAlert2=alert2.create();







         editCName.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                theAlert.show();

             }
         });

         editCNumber.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                theAlert2.show();
             }
         });







       Bundle bundle=getIntent().getExtras();
       if (bundle!=null){
           theclass.setText(bundle.getString("cName"));
           theclassnumber.setText(bundle.getString("cNumber"));
          pos=bundle.getInt("item_position");
           allStudents=new ArrayAdapter<>(Class_Info.this,android.R.layout.simple_list_item_1,bundle.getStringArrayList("stuList"));
           students.setAdapter(allStudents);
       }

       toClassList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               cName=theclass.getText().toString();
               cNumber=theclassnumber.getText().toString();
               Intent i = new Intent();
               i.putExtra("classNme",cName);
               i.putExtra("classNum",cNumber);
               i.putExtra("key",Integer.toString(pos));
               setResult(RESULT_OK,i);
               finish();
           }
       });
       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i =new Intent();
               i.putExtra("deleteKey", Integer.toString(pos));
               setResult(RESULT_CANCELED,i);
               finish();
           }
       });

    }
}
