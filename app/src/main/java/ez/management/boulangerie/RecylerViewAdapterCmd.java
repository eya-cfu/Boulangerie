package ez.management.boulangerie;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bakerieslibrary.ApiException;
import com.example.bakerieslibrary.models.Boulangeries;
import com.example.bakerieslibrary.models.CommandesBL;
import com.example.bakerieslibrary.models.customresponses.FilteredDetailsCommandeBL;
import com.example.bakerieslibrary.tags.BoulangeriesController;
import com.example.bakerieslibrary.tags.CommandesBLController;
import com.example.bakerieslibrary.tags.DetailsCommandeBLController;
import com.example.bakerieslibrary.utils.VolleyErrorHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public class RecylerViewAdapterCmd extends RecyclerView.Adapter<RecylerViewAdapterCmd.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapterCmd";

    private Context mContext;
    private ArrayList<String> mCmdCodes = null;
    private ArrayList<String> mCmdDates = null;
    private OnCmdListener onCmdListener;
    private CommandesBLController commandesBLController;
    private String detailsStr="";
    private AlertDialog alertDialog;
    private ProgressDialog progressDialog;



    public RecylerViewAdapterCmd(Context mContext, ArrayList<String> mCmdCodes,
                                 ArrayList<String> mCmdDates, OnCmdListener onCmdListener) {
        this.mContext = mContext;
        this.mCmdCodes = mCmdCodes;
        this.mCmdDates = mCmdDates;
        this.onCmdListener = onCmdListener;
        commandesBLController = new CommandesBLController(mContext);
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Chargement...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cmditem,parent,
                false);
        ViewHolder holder = new ViewHolder(view,onCmdListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");



        if(mCmdCodes.size()!=0){


            detailsStr = "";

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialogBuilder.setTitle("Details de "+mCmdCodes.get(position));

            holder.dateCmd.setText(mCmdDates.get(position));
            holder.codeCmd.setText(mCmdCodes.get(position));

            holder.detailsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    progressDialog.show();

                    commandesBLController.getDetailsByCommandeBL(mCmdCodes.get(position),
                            new Response.Listener<List<FilteredDetailsCommandeBL>>() {

                                StringBuilder sb = new StringBuilder() ;
                                @Override
                                public void onResponse(List<FilteredDetailsCommandeBL> response) {
                                    for(FilteredDetailsCommandeBL detail : response){
                                        sb.append(detail.toString());
                                        sb.append("\n");
                                    }
                                    detailsStr = sb.substring(0,sb.length());


                                    alertDialogBuilder.setMessage(detailsStr);
                                    alertDialog = alertDialogBuilder.create();

                                    progressDialog.dismiss();


                                    alertDialog.show();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    detailsStr = VolleyErrorHelper.getMessage(error);


                                    alertDialogBuilder.setMessage(detailsStr);
                                    alertDialog = alertDialogBuilder.create();

                                    progressDialog.dismiss();


                                    alertDialog.show();
                                }
                            });




                }
            });
        }else{
            Log.i(TAG, "onBindViewHolder: 0 commandes");
        }

    }


    @Override
    public int getItemCount() {
        return mCmdCodes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RelativeLayout cmdParentLayout;
        TextView codeCmd;
        TextView dateCmd;
        Button detailsBtn;
        OnCmdListener onCmdListener;

        public ViewHolder(@NonNull View itemView, OnCmdListener onCmdListener) {
            super(itemView);
            cmdParentLayout = itemView.findViewById(R.id.cmdParent_layout);
            codeCmd = itemView.findViewById(R.id.codeCmdTxt);
            dateCmd = itemView.findViewById(R.id.dateCmdTxt);
            detailsBtn = itemView.findViewById(R.id.detailsBtn);
            this.onCmdListener = onCmdListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCmdListener.onCmdClick(getAdapterPosition());
        }
    }

    public interface OnCmdListener{
        void onCmdClick(int position);
    }

}
