/*Brandon Lavinsky
* lavin105@mail.chapman.edu
* Class_List.java*/

package lavin105.classorganizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/*this class displays the list of classes as well as a button to add new classes
an array adapter and array list is used to store and display classes into the listview
If the user clicks on a class it will direct them to the Class info activity where they can interact with the class*/
public class Class_List extends Activity {
    ListView classList;
    ArrayList<String> listOfClasses, listOfClassNumbers;
    ArrayList<ArrayList<String>>listOfStudentsList;
    ArrayAdapter<String> adapter2;
    final int REQUEST_CODE_1=1;
    final int REQUEST_CODE_2=2;


    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_list_layout);
            listOfStudentsList=new ArrayList<>();
            listOfClasses=new ArrayList<String>();
            listOfClassNumbers=new ArrayList<String>();
            classList=findViewById(R.id.class_list);
            //displays the list of classes
            adapter2=new ArrayAdapter<>(Class_List.this,android.R.layout.simple_list_item_1,listOfClasses);
            classList.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();

        Button newClass=findViewById(R.id.new_class);
        //ridirects to add class activity
        newClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Class_List.this,Add_Class.class);
                startActivityForResult(i,REQUEST_CODE_1);
            }
        });


    }
    //recieves multipe data results from many activities
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //data from adding a class
        if(requestCode==REQUEST_CODE_1){
            if(resultCode==RESULT_OK){
                String classToAdd=data.getStringExtra("className");
                String classNumber=data.getStringExtra("classNumber");
                ArrayList<String> studentList=data.getStringArrayListExtra("studentList");
                listOfStudentsList.add(studentList);
                listOfClasses.add(classToAdd);
                listOfClassNumbers.add(classNumber);
                classList.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();

            }
        }
        //data from editing a class
        if(requestCode==REQUEST_CODE_2){
            if(resultCode==RESULT_OK){
               System.out.println(data.getStringExtra("classNme"));
               System.out.println(data.getStringExtra("classNum"));
               System.out.println(data.getStringExtra("key"));
               String p = data.getStringExtra("key");
               int key=Integer.parseInt(p);
               listOfClasses.set(key,data.getStringExtra("classNme"));
               classList.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
                listOfClassNumbers.set(key,data.getStringExtra("classNum"));
                listOfStudentsList.set(key,data.getStringArrayListExtra("listy"));
                System.out.println(listOfClassNumbers);

            }
            //data from deleting a class
            if (resultCode==RESULT_CANCELED){
                String pp = data.getStringExtra("deleteKey");
                int delkey=Integer.parseInt(pp);
                listOfClasses.remove(delkey);
                listOfClassNumbers.remove(delkey);
                listOfStudentsList.remove(delkey);
                classList.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
            }
        }
        //clicking on a class takes you to class info activity
        classList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String classNameInfo=classList.getItemAtPosition(position).toString();
                String classNumberInfo=listOfClassNumbers.get(position);
                ArrayList<String> theStudents=listOfStudentsList.get(position);
                System.out.println(theStudents);
                Intent infoIntent = new Intent(Class_List.this,Class_Info.class);
                infoIntent.putExtra("cName",classNameInfo);
                infoIntent.putExtra("cNumber",classNumberInfo);
                infoIntent.putStringArrayListExtra("stuList", theStudents);
                infoIntent.putExtra("item_position", position);
                startActivityForResult(infoIntent,REQUEST_CODE_2);
            }
        });
    }
}
