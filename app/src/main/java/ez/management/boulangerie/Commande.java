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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Commande extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    Button dateBtn;
    Button addBtn;
    EditText codeTxt;
    EditText qteTxt;
    Spinner nomSpinner;
    String productName ="";
    RecyclerView rv;
    RecyclerViewAdapterItem adapter ;
    Context mContext;
    Button validerBtn;
    AlertDialog.Builder alertDialogBuilder ;
    AlertDialog alertDialog;



    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mCodes = new ArrayList<>();
    private ArrayList<Integer> mQtes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);

        dateBtn = findViewById(R.id.dateBtn);
        addBtn = findViewById(R.id.addBtn);
        validerBtn = findViewById(R.id.validerBtn);
        codeTxt = findViewById(R.id.codeTxt);
        qteTxt = findViewById(R.id.qteTxt);
        nomSpinner = findViewById(R.id.nomSpinner);

        //Recycler view
        rv = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapterItem(this,mNames,mCodes,mQtes);
        rv.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(rv);
        rv.setAdapter(adapter);

        mContext = this;
        alertDialogBuilder = new AlertDialog.Builder(this);

        //Configuring spinner product list
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pdtNames, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         nomSpinner.setAdapter(adapter);
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
                 Intent i1 = new Intent(getApplicationContext(),Accueil.class);

                 startActivity(i1);
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
                    Toast.makeText(mContext,"produit ajouté",Toast.LENGTH_SHORT).show();
                    codeTxt.setText("");
                    qteTxt.setText("");
                } else {
                    Toast.makeText(mContext,"commande invalide",Toast.LENGTH_LONG).show();
                }
            }
        });

        validerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });

    }


    private void showDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,
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
    }


    private void initRecyclerView(){
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        productName =parent.getItemAtPosition(position).toString();
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
