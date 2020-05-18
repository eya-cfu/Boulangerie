package ez.management.boulangerie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bakerieslibrary.ApiInvoker;
import com.example.bakerieslibrary.models.CommandesBL;
import com.example.bakerieslibrary.tags.CommandesBLController;
import com.example.bakerieslibrary.utils.VolleyErrorHelper;

import java.util.ArrayList;
import java.util.List;

public class Historique extends AppCompatActivity implements RecylerViewAdapterCmd.OnCmdListener {

    RecyclerView rv;
    RecylerViewAdapterCmd adapter ;
    TextView rienTxt2;
    CommandesBLController commandesBLController ;
    int idBL;
    ProgressBar progressBar;

    private ArrayList<String> mCmdCodes = new ArrayList<>();
    private ArrayList<String> mCmdDates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        commandesBLController= new CommandesBLController(getApplicationContext());
        rienTxt2 = findViewById(R.id.rienTxt2);
        progressBar = findViewById(R.id.progressBar_cyclic3);


        rv = findViewById(R.id.recyclerView2);
        adapter = new RecylerViewAdapterCmd(this,mCmdCodes,mCmdDates,this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        if(getIntent().hasExtra("idBL")){
            idBL = getIntent().getExtras().getInt("idBL");

            commandesBLController.getCmdsByEtatAndBoulangerieID(CommandesBL.EtatEnum.honoree.toString(), idBL,
                    new Response.Listener<List<CommandesBL>>() {
                        @Override
                        public void onResponse(List<CommandesBL> response) {
                            if(response.size() != 0){
                                for (CommandesBL cmd : response){
                                    mCmdCodes.add(cmd.getIdCommandeBL());
                                    mCmdDates.add(ApiInvoker.formatDate(cmd.getDueDate()));
                                    Log.d("cmd",cmd.toString());
                                }


                                adapter.notifyDataSetChanged();
                                if(mCmdCodes.size() == 0){
                                    rienTxt2.setText("Vous n'avez aucune commande honorée.");
                                }else {
                                    rienTxt2.setText("");
                                }
                              }
                            progressBar.setVisibility(View.GONE);
                            Log.d("response",String.valueOf(response.size()));
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressBar.setVisibility(View.GONE);
                            rienTxt2.setTextColor(Color.RED);
                            rienTxt2.setText(VolleyErrorHelper.getMessage(error));
                            Log.d("error",error.getMessage());
                        }
                    });

        }




    }

    @Override
    public void onCmdClick(int position) {

    }
}
