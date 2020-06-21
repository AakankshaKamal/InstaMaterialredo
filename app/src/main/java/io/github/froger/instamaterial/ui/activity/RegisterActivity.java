package io.github.froger.instamaterial.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.Model.User;
import io.github.froger.instamaterial.ui.data.UserDao;
import io.github.froger.instamaterial.ui.data.UserDataBase;

public class RegisterActivity extends AppCompatActivity {
    EditText editusername,editemail,editpasswrd;
    Button btnreg;
    private UserDao userDao,db;
    UserDataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dataBase= Room.databaseBuilder(this,UserDataBase.class,UserDataBase.DB_NAME).allowMainThreadQueries().build();
        db=dataBase.getUserDao();
        editusername=findViewById(R.id.name);
        editemail=findViewById(R.id.emailEditText);
        editpasswrd=findViewById(R.id.passwordEditText);
        btnreg=findViewById(R.id.reg);
       // userDao= Room.databaseBuilder(this, UserDataBase.class,UserDataBase.DB_NAME).build().getUserDao();
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=editemail.getText().toString().trim();
                String password=editpasswrd.getText().toString().trim();
                String username=editusername.getText().toString().trim();
                User user=new User(username,email,password);
                db.insert(user);
                User user2=db.getUser(email,password);
                if(user2!=null)
                {
                    Intent i=new Intent(RegisterActivity.this,MainActivity.class);
                    i.putExtra("User",user);
                    startActivity(i);
                    finish();
                }

            }
        });


    }
}
