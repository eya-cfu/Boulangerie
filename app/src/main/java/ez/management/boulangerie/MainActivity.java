package ez.management.boulangerie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bakerieslibrary.ApiException;
import com.example.bakerieslibrary.ApiInvoker;
import com.example.bakerieslibrary.models.Boulangeries;
import com.example.bakerieslibrary.models.Profils;
import com.example.bakerieslibrary.tags.BoulangeriesController;
import com.example.bakerieslibrary.tags.ProfilsController;
import com.example.bakerieslibrary.utils.VolleyErrorHelper;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button connectBtn;
    EditText loginTxt;
    EditText pwdTxt;
    String login="";
    String pwd="";
    ProfilsController profilsController ;
    BoulangeriesController boulangeriesController ;
    Profils responsable = null;
    Boulangeries thisBoulangerie = new Boulangeries();
    Context context;
    ProgressBar progressBar;
    Intent intent1;
    TextView errorTxt;
    String file = "UserData";
    File cacheFile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorTxt = findViewById(R.id.errorTxt1);
        connectBtn = findViewById(R.id.connectBtn);
        loginTxt = findViewById(R.id.loginTxt);
        pwdTxt = findViewById(R.id.pwdText);
        progressBar = findViewById(R.id.progressBar_cyclic1);
        context = this;
        profilsController = new ProfilsController(getApplicationContext());
        boulangeriesController = new BoulangeriesController(getApplicationContext());
        intent1 = new Intent(getApplicationContext(),Accueil.class);

        errorTxt.setText("");

        try {
            File.createTempFile(file, null, getCacheDir());
            cacheFile = new File(getCacheDir(), file);
        } catch (IOException e) {
            errorTxt.setText("Une erreur est survenue.");
            connectBtn.setClickable(false);
        }


        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login = loginTxt.getText().toString();
                pwd = pwdTxt.getText().toString();
                if(login.length() == 0 || pwd.length() == 0){
                    return;
                }

                errorTxt.setText("");
                progressBar.setVisibility(View.VISIBLE);

                /*
                    verify auth information
                 */

                //Getting Boulangerie informations by login
                profilsController.getProfilByLogin(login, new Response.Listener<Profils>() {
                    @Override
                    public void onResponse(Profils profil) {
                        if(profil != null){
                            responsable = profil;
                            Log.d("Profils",responsable.toString());
                            responsable.setMatricule(3333);

                            boulangeriesController.getBoulangerieByMatricule(responsable.getMatricule(),
                                    new Response.Listener<Boulangeries>() {
                                        @Override
                                        public void onResponse(Boulangeries boulangerie) {
                                            progressBar.setVisibility(View.GONE);
                                            if(boulangerie != null){
                                                thisBoulangerie = boulangerie;
                                              //  Log.d("Boul",thisBoulangerie.toString());
                                                try {
                                                    thisBoulangerie.saveUser(cacheFile,thisBoulangerie);
                                                    startActivity(intent1);
                                                } catch (IOException e) {
                                                    errorTxt.setText("Une erreur est survenue.");
                                                }

                                            }else{
                                                errorTxt.setText("Une erreur est survenue.");
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            errorTxt.setText(VolleyErrorHelper.getMessage(error));
                                            //Log.d("BL error", error.getMessage());
                                        }
                                    });
                        }else{
                            errorTxt.setText("Une erreur est survenue.");
                        }
                        progressBar.setVisibility(View.GONE);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        errorTxt.setText(VolleyErrorHelper.getMessage(error));
                       // Log.d("Profil error", error.getMessage());

                    }
                });

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
