package ez.management.boulangerie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Historique extends AppCompatActivity implements RecylerViewAdapterCmd.OnCmdListener {

    RecyclerView rv;
    RecylerViewAdapterCmd adapter ;
    TextView rienTxt2;

    private ArrayList<String> mCmdCodes = new ArrayList<>();
    private ArrayList<String> mCmdDates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        rienTxt2 = findViewById(R.id.rienTxt2);

        //test init for Cmds
        for (int i=1; i<=10;i++){
            mCmdCodes.add("cmd"+i);
            mCmdDates.add("date cmd"+i);
        }

        if(mCmdCodes.size() == 0){
            rienTxt2.setText("Vous n'avez aucune commande honorée.");
        }else {
            rienTxt2.setText("");
        }

        rv = findViewById(R.id.recyclerView2);
        adapter = new RecylerViewAdapterCmd(this,mCmdCodes,mCmdDates,this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onCmdClick(int position) {

    }
}
