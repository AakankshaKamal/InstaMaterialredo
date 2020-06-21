package io.github.froger.instamaterial.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.Model.User;
import io.github.froger.instamaterial.ui.data.UserDao;
import io.github.froger.instamaterial.ui.data.UserDataBase;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LoginActivity extends AppCompatActivity {

    UserDao db;
    UserDataBase dataBase;

    //variable copied
    private ImageView bookIconImageView;
    private TextView bookITextView;
    private ProgressBar loadingProgressBar;
    private RelativeLayout rootView, afterAnimationView;
    private Button lobtn,register;
    private EditText editemail,editpaswrd,editname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        initViews();
        dataBase= Room.databaseBuilder(this,UserDataBase.class,UserDataBase.DB_NAME).allowMainThreadQueries().build();
        db=dataBase.getUserDao();
editemail=findViewById(R.id.emailEditText);
editpaswrd=findViewById(R.id.passwordEditText);

lobtn=findViewById(R.id.login);
register=findViewById(R.id.loginButton);
register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);

    }
});

lobtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

//   Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//   startActivity(intent);
        String email=editemail.getText().toString().trim();
        String password=editpaswrd.getText().toString().trim();
        User user=db.getUser(email,password);
        if(user!=null)
        {
            Intent i=new Intent(LoginActivity.this,MainActivity.class);
            i.putExtra("User",user);
            startActivity(i);
            finish();
        }
        else
        {
            Toast.makeText(LoginActivity.this,"wrong",Toast.LENGTH_SHORT).show();
        }




    }
});

        //xml


        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                bookITextView.setVisibility(GONE);
                loadingProgressBar.setVisibility(GONE);
                rootView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.style_color_primary_dark));
                bookIconImageView.setImageResource(R.drawable.ic_instagram_white);
                startAnimation();

            }
        }.start();
    }

    private void initViews() {
        bookIconImageView = findViewById(R.id.bookIconImageView);
        bookITextView = findViewById(R.id.bookITextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        rootView = findViewById(R.id.rootView);
        afterAnimationView = findViewById(R.id.afterAnimationView);
    }

    private void startAnimation() {
        ViewPropertyAnimator viewPropertyAnimator = bookIconImageView.animate();
        viewPropertyAnimator.x(50f);
        viewPropertyAnimator.y(100f);
        viewPropertyAnimator.setDuration(1000);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                afterAnimationView.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}

