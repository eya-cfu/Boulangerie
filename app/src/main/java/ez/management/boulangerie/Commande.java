package ez.management.boulangerie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bakerieslibrary.models.CommandesBL;
import com.example.bakerieslibrary.models.Produits;
import com.example.bakerieslibrary.models.customresponses.Count;
import com.example.bakerieslibrary.tags.CommandesBLController;
import com.example.bakerieslibrary.tags.ProduitsController;
import com.example.bakerieslibrary.utils.VolleyErrorHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Commande extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    Button dateBtn;
    Button addBtn;
    TextView codeTxt;
    EditText qteTxt;
    Spinner nomSpinner;
    String productName ="";
    RecyclerView rv;
    RecyclerViewAdapterItem adapter ;
    Context mContext;
    Button validerBtn;
    AlertDialog.Builder alertDialogBuilder ;
    AlertDialog alertDialog;
    Date dueDate = null;
    DatePickerDialog datePickerDialog = null;
    int numeroCommande=0;
    int idBoulangerie;
    TextView errorTxt;
    ProgressBar progressBar;

    Intent i1;
    CommandesBLController cmdController ;
    ProduitsController produitsController ;

    private ArrayList<String> pdtNames = new ArrayList<>();
    private ArrayList<String> pdtCodes = new ArrayList<>();

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mCodes = new ArrayList<>();
    private ArrayList<Integer> mQtes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);

        cmdController = new CommandesBLController(getApplicationContext());
        produitsController = new ProduitsController(getApplicationContext());
        i1 = new Intent(getApplicationContext(),Accueil.class);

        mContext = this;
        dateBtn = findViewById(R.id.dateBtn);
        addBtn = findViewById(R.id.addBtn);
        validerBtn = findViewById(R.id.validerBtn);
        codeTxt = findViewById(R.id.codeTxt);
        qteTxt = findViewById(R.id.qteTxt);
        nomSpinner = findViewById(R.id.nomSpinner);
        errorTxt = findViewById(R.id.errorTxt);
        progressBar = findViewById(R.id.progressBar_cyclic2);

        //Recycler view for ordered products
        rv = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapterItem(mNames,mCodes,mQtes);
        rv.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(rv);
        rv.setAdapter(adapter);

        alertDialogBuilder = new AlertDialog.Builder(this);

        if(getIntent().hasExtra("idBL")) {
            idBoulangerie = getIntent().getExtras().getInt("idBL");
        } else {
            validerBtn.setClickable(false);
            errorTxt.setText("une erreur est survenue");
        }


        cmdController.getCount(new Response.Listener<Count>() {
            @Override
            public void onResponse(Count response) {
                if(response.getCount()!=null){
                    numeroCommande = response.getCount() +1;
                }
                //Log.d("Count", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorTxt.setText(VolleyErrorHelper.getMessage(error));
            }
        });

        //Configuring spinner product list
        produitsController.getProduits(new Response.Listener<List<Produits>>() {
            @Override
            public void onResponse(List<Produits> response) {
                errorTxt.setText("");
               // Log.d("Products", "Got'em "+ response.size());

                for (Produits produit : response){
                    pdtNames.add(produit.getLibelle());
                    pdtCodes.add(produit.getCodeProduit().toString());
                //    Log.d("Products", produit.toString());
                }


                //noinspection unchecked
                ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item,
                        pdtNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                nomSpinner.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                errorTxt.setText(VolleyErrorHelper.getMessage(error));

               // Log.d("Products Error","Some error occured"+ error.getMessage());
               // Log.d("Products Error", "errors:" +
                //        error.getNetworkTimeMs()+", "+error.getCause());
            }
        });

        nomSpinner.setOnItemSelectedListener(this);

        //Configuring Dialog box on validation
         alertDialogBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.cancel();
             }
         });
         alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

                 progressBar.setVisibility(View.VISIBLE);
                 dialog.cancel();


                 Date creationDate = new Date();
                 SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy",Locale.FRANCE);
                 String idCommande = numeroCommande+"BL"+idBoulangerie + "-" + sdf.format(creationDate);

                 final CommandesBL cmd = new CommandesBL(idCommande,dueDate,creationDate,
                         CommandesBL.EtatEnum.nouvelle, idBoulangerie,null);

                 cmdController.addCommandeBL(cmd, new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {

                         progressBar.setVisibility(View.GONE);
                         errorTxt.setText("");

                         Toast.makeText(mContext,"commande créée",Toast.LENGTH_LONG).show();
                         Log.d("cmd",cmd.toString());

                         startActivity(i1);
                     }
                 }, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         progressBar.setVisibility(View.GONE);
                         errorTxt.setText(VolleyErrorHelper.getMessage(error));
                     }
                 });


             }
         });
         alertDialogBuilder.setTitle("Validation");
         alertDialogBuilder.setMessage("Êtes-vous sûr de vouloir valider cette commande?");
         alertDialog = alertDialogBuilder.create();

        //Buttons

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showDatePicker();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((productName.length()!=0)&&(codeTxt.getText().length() != 0)&&
                        (qteTxt.getText().length() != 0)) {
                    mNames.add(productName);
                    mCodes.add(codeTxt.getText().toString());
                    mQtes.add(Integer.parseInt(qteTxt.getText().toString()));
                    initRecyclerView();
                    Toast.makeText(mContext,"Produit ajouté",Toast.LENGTH_SHORT).show();
                    qteTxt.setText("");
                } else {
                    Toast.makeText(mContext,"Commande invalide",Toast.LENGTH_LONG).show();
                }
            }
        });

        validerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(datePickerDialog == null || dueDate == null || dueDate.compareTo(new Date()) <= 0){
                    Toast.makeText(mContext,"Choisissez une date!",Toast.LENGTH_LONG).show();
                    return;
                }
                if (mCodes.size() == 0){
                    Toast.makeText(mContext,"Commande invalide!",Toast.LENGTH_LONG).show();
                    return;
                }

                alertDialog.show();
            }
        });

    }


    private void showDatePicker(){
        datePickerDialog = new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth+"/"+(month+1)+"/"+year;
        dateBtn.setText(date);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,dayOfMonth);
        dueDate = calendar.getTime();
    }


    private void initRecyclerView(){
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        productName =parent.getItemAtPosition(position).toString();
        codeTxt.setText(pdtCodes.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            mCodes.remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
            Toast.makeText(mContext,"Element supprimé",Toast.LENGTH_SHORT).show();
        }
    };


}
