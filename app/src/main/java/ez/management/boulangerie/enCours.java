package ez.management.boulangerie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bakerieslibrary.ApiInvoker;
import com.example.bakerieslibrary.models.CommandesBL;
import com.example.bakerieslibrary.tags.CommandesBLController;
import com.example.bakerieslibrary.utils.VolleyErrorHelper;

import java.util.ArrayList;
import java.util.List;

public class enCours extends AppCompatActivity implements RecylerViewAdapterCmd.OnCmdListener {

    RecyclerView rv;
    RecylerViewAdapterCmd adapter ;
    TextView rienTxt;
    Context mContext = this;
    int idBL;
    CommandesBLController commandesBLController;

    ProgressBar progressBar;

    private ArrayList<String> mCmdCodes = new ArrayList<>();
    private ArrayList<String> mCmdDates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_cours);

        progressBar = findViewById(R.id.progressBar_cyclic);

        commandesBLController = new CommandesBLController(getApplicationContext());

        rienTxt = findViewById(R.id.rienTxt);

        rv = findViewById(R.id.recyclerView1);
        adapter = new RecylerViewAdapterCmd(this,mCmdCodes,mCmdDates,this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        if(getIntent().hasExtra("idBL")){
            idBL = getIntent().getExtras().getInt("idBL");

            commandesBLController.getCmdsByEtatAndBoulangerieID(CommandesBL.EtatEnum.nouvelle.toString(), idBL,
                    new Response.Listener<List<CommandesBL>>() {
                        @Override
                        public void onResponse(List<CommandesBL> response) {
                            Log.d("idBoul ", String.valueOf(idBL));
                            if(response.size() != 0){
                                rienTxt.setText("");

                                for (CommandesBL cmd : response){
                                    mCmdCodes.add(cmd.getIdCommandeBL());
                                    mCmdDates.add(ApiInvoker.formatDate(cmd.getDueDate()));
                                   // Log.d("cmd",cmd.toString());
                                }
                            }

                            adapter.notifyDataSetChanged();

                            if (mCmdCodes.size()==0) {
                                rienTxt.setText("Vous n'avez aucune commande en cours.");
                            }else{
                                rienTxt.setText("");
                            }
                           // progressBar.setVisibility(View.GONE);
                           // Log.d("response",String.valueOf(response.size()));
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressBar.setVisibility(View.GONE);
                            rienTxt.setTextColor(Color.RED);
                            rienTxt.setText(VolleyErrorHelper.getMessage(error));
                           // Log.d("error",""+error.getMessage());
                        }
                    });
            commandesBLController.getCmdsByEtatAndBoulangerieID(CommandesBL.EtatEnum.enCours.toString(), idBL,
                    new Response.Listener<List<CommandesBL>>() {
                        @Override
                        public void onResponse(List<CommandesBL> response) {
                            if(response.size() != 0){
                                rienTxt.setText("");

                                for (CommandesBL cmd : response){
                                    mCmdCodes.add(cmd.getIdCommandeBL());
                                    mCmdDates.add(ApiInvoker.formatDate(cmd.getDueDate()));
                                    // Log.d("cmd",cmd.toString());
                                }

                            }
                            adapter.notifyDataSetChanged();
                            if (mCmdCodes.size()==0) {
                                rienTxt.setText("Vous n'avez aucune commande en cours.");
                            }else{
                                rienTxt.setText("");
                            }
                            progressBar.setVisibility(View.GONE);
                            // Log.d("response",String.valueOf(response.size()));
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressBar.setVisibility(View.GONE);
                            rienTxt.setTextColor(Color.RED);
                            rienTxt.setText(VolleyErrorHelper.getMessage(error));
                           // Log.d("error",""+error.getMessage());
                        }
                    });



        }

    }

    @Override
    public void onCmdClick(final int position) {
        rienTxt.setText("");


        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Confirmer la reception");
        alertDialogBuilder.setMessage("Confirmer la reception de la commande "+mCmdCodes.get(position)+"?");
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                commandesBLController.patchCmdBlEtat(mCmdCodes.get(position), CommandesBL.EtatEnum.honoree.toString(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(mContext,"Commande reçue", Toast.LENGTH_SHORT).show();
                                mCmdCodes.remove(position);
                                mCmdDates.remove(position);
                                adapter.notifyDataSetChanged();

                             //   Log.d("patch response", response);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                rienTxt.setTextColor(Color.RED);
                                rienTxt.setText(VolleyErrorHelper.getMessage(error));
                            }
                        });
            }
        });
        alertDialogBuilder.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = alertDialogBuilder.create();

        commandesBLController.getCommandeBLById(mCmdCodes.get(position), new Response.Listener<CommandesBL>() {
            @Override
            public void onResponse(CommandesBL response) {
                if(response.getEtat().toString().equalsIgnoreCase("enCours")){
                    alertDialog.show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                rienTxt.setTextColor(Color.RED);
                rienTxt.setText(VolleyErrorHelper.getMessage(error));
            }
        });

        

    }
}
