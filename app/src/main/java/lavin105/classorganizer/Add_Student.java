/*Brandon Lavinsky
 * lavin105@mail.chapman.edu
 * Add_Student.java*/

package lavin105.classorganizer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/*This class is where students can be added, a first name last name and id number along with a picture are available,
* by clicking add student it will direct you to either the add class activity or the class info
* activity depending in which activity you clicked on add student*/

public class Add_Student extends Activity {

    Button cancel,add, takePhoto;
    EditText firstNme, lastNme, stuId;
    ImageView studentPhoto;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student_layout);
        cancel=findViewById(R.id.cancelAddStudent);
        add=findViewById(R.id.addStudent);
        firstNme=findViewById(R.id.firstName);
        lastNme=findViewById(R.id.lastName);
        stuId=findViewById(R.id.studentId);
        takePhoto=findViewById(R.id.photo);
        studentPhoto=findViewById(R.id.pic);

        //adds a student to the student list for a particular class
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first=firstNme.getText().toString();
                String last=lastNme.getText().toString();
                String fullName=first+" " + last;
                String id=stuId.getText().toString();

                if (first.equals("")||last.equals("")||id.equals("")){
                    Toast t= Toast.makeText(Add_Student.this,"Field Missing",Toast.LENGTH_SHORT);
                    t.show();
                }else{
                    Intent i = new Intent();
                    i.putExtra("name",fullName);
                    i.putExtra("id", id);
                    setResult(RESULT_OK,i);
                    finish();
                }



            }
        });
        //cancels adding a student
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //starts the camera to take a photo
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,0);
            }
        });

    }
    //retrieves the camera photo and set the image view to that picture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap=(Bitmap)data.getExtras().get("data");
        studentPhoto.setImageBitmap(bitmap);

    }
}
