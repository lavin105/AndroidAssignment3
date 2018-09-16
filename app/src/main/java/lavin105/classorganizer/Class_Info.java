package lavin105.classorganizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Class_Info extends Activity {
    TextView theclass,theclassnumber;
    Button toClassList;
    ListView students;
    ArrayAdapter<String> allStudents;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info_layout);
        students=findViewById(R.id.students_list);
        toClassList=findViewById(R.id.toClasses);
         theclass =findViewById(R.id.className);
         theclassnumber=findViewById(R.id.classNumber);
       Bundle bundle=getIntent().getExtras();
       if (bundle!=null){
           theclass.setText(bundle.getString("cName"));
           theclassnumber.setText(bundle.getString("cNumber"));
           allStudents=new ArrayAdapter<>(Class_Info.this,android.R.layout.simple_list_item_1,bundle.getStringArrayList("stuList"));
           students.setAdapter(allStudents);
       }

       toClassList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(Class_Info.this,Class_List.class);
               startActivity(i);
           }
       });

    }
}
