package ez.management.boulangerie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Accueil extends AppCompatActivity {

    Button nouvelleBtn;
    Button historiqueBtn;
    Button enCoursBtn;
    Button disconnectBtn;
    String login;
    Intent i1,i2,i3,i4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        login = "";
        nouvelleBtn = findViewById(R.id.nouvelleBtn);
        historiqueBtn = findViewById(R.id.historiqueBtn);
        enCoursBtn = findViewById(R.id.enCoursBtn);
        disconnectBtn = findViewById(R.id.disconnectBtn);

        i1 = new Intent(getApplicationContext(),Commande.class);
        i2 = new Intent(getApplicationContext(),enCours.class);
        i3 = new Intent(getApplicationContext(),Historique.class);
        i4 = new Intent(getApplicationContext(),MainActivity.class);


        if(getIntent().hasExtra("login")){
            login = getIntent().getExtras().getString("login");
        }

        nouvelleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i1);
            }
        });

        enCoursBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i2);
            }
        });

        historiqueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i3);
            }
        });

        disconnectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish connection with server
                startActivity(i4);
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
