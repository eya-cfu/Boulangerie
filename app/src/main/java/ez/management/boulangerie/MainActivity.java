package ez.management.boulangerie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button connectBtn;
    EditText loginTxt;
    EditText pwdTxt;
    String login="";
    String pwd="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectBtn = findViewById(R.id.connectBtn);
        loginTxt = findViewById(R.id.loginTxt);
        pwdTxt = findViewById(R.id.pwdText);
        login = loginTxt.getText().toString();
        pwd = pwdTxt.getText().toString();

        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),Accueil.class);

                intent1.putExtra("login",login);
                startActivity(intent1);
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}
