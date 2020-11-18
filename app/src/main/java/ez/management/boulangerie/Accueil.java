package ez.management.boulangerie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bakerieslibrary.ApiException;
import com.example.bakerieslibrary.ApiInvoker;
import com.example.bakerieslibrary.models.Boulangeries;
import com.example.bakerieslibrary.models.Profils;
import com.example.bakerieslibrary.tags.BoulangeriesController;
import com.example.bakerieslibrary.tags.CommandesBLController;
import com.example.bakerieslibrary.tags.ProfilsController;
import com.example.bakerieslibrary.utils.VolleyErrorHelper;

import java.io.File;
import java.io.IOException;


public class Accueil extends AppCompatActivity {

    Button nouvelleBtn;
    Button historiqueBtn;
    Button enCoursBtn;
    Button disconnectBtn;
    TextView profilTxt;
    Intent i1,i2,i3,i4;
    String file = "UserData";
    File cacheFile;
    ProfilsController profilsController ;
    BoulangeriesController boulangeriesController ;
    CommandesBLController commandesBLController;
    Boulangeries thisBoulangerie = new Boulangeries();
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);


        profilsController = new ProfilsController(getApplicationContext());
        boulangeriesController = new BoulangeriesController(getApplicationContext());
        commandesBLController = new CommandesBLController(getApplicationContext());
        context = this;
        nouvelleBtn = findViewById(R.id.nouvelleBtn);
        historiqueBtn = findViewById(R.id.historiqueBtn);
        enCoursBtn = findViewById(R.id.enCoursBtn);
        disconnectBtn = findViewById(R.id.disconnectBtn);
        profilTxt = findViewById(R.id.profilTxtView);

        cacheFile = new File(getCacheDir(), file);
        try {
            thisBoulangerie = thisBoulangerie.getUser(cacheFile);
            profilTxt.setText("Vous etes BL"+ thisBoulangerie.getIdBoulangerie() + " "
            + thisBoulangerie.getNomBL());
           // Log.d("cache boul", thisBoulangerie.toString());
        } catch (IOException | ClassNotFoundException e) {
            if(!cacheFile.exists()) {
                cacheFile.delete();
                Toast.makeText(this,"Veuillez vous reconnecter", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
            Toast.makeText(this,"Erreur", Toast.LENGTH_LONG).show();
        }


        i1 = new Intent(getApplicationContext(),Commande.class);
        i2 = new Intent(getApplicationContext(),enCours.class);
        i3 = new Intent(getApplicationContext(),Historique.class);
        i4 = new Intent(getApplicationContext(),MainActivity.class);





        nouvelleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1.putExtra("idBL",thisBoulangerie.getIdBoulangerie());

                startActivity(i1);
            }
        });

        enCoursBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i2.putExtra("idBL",thisBoulangerie.getIdBoulangerie());
                startActivity(i2);
            }
        });

        historiqueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i3.putExtra("idBL",thisBoulangerie.getIdBoulangerie());
                startActivity(i3);
            }
        });

        disconnectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish connection with server
                cacheFile.delete();
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
