package ez.management.boulangerie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class enCours extends AppCompatActivity implements RecylerViewAdapterCmd.OnCmdListener {

    RecyclerView rv;
    RecylerViewAdapterCmd adapter ;
    TextView rienTxt;

    private ArrayList<String> mCmdCodes = new ArrayList<>();
    private ArrayList<String> mCmdDates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_cours);

        rienTxt = findViewById(R.id.rienTxt);

        if(mCmdCodes.size() == 0){
            rienTxt.setText("Vous n'avez aucune commande en cours.");
        }else {
            rienTxt.setText("");
        }

        rv = findViewById(R.id.recyclerView1);
        adapter = new RecylerViewAdapterCmd(this,mCmdCodes,mCmdDates,this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onCmdClick(int position) {

    }
}
