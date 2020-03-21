package ez.management.boulangerie;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecylerViewAdapterCmd extends RecyclerView.Adapter<RecylerViewAdapterCmd.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapterCmd";

    private Context mContext;
    private ArrayList<String> mCmdCodes = new ArrayList<>();
    private ArrayList<String> mCmdDates = new ArrayList<>();
    private OnCmdListener onCmdListener;
    private boolean historique = false;



    public RecylerViewAdapterCmd(Context mContext, ArrayList<String> mCmdCodes,
                                 ArrayList<String> mCmdDates, OnCmdListener onCmdListener) {
        this.mContext = mContext;
        this.mCmdCodes = mCmdCodes;
        this.mCmdDates = mCmdDates;
        this.onCmdListener = onCmdListener;

    }

    public RecylerViewAdapterCmd(Context mContext, ArrayList<String> mCmdCodes,
                                 ArrayList<String> mCmdDates, OnCmdListener onCmdListener,
                                 Boolean historique) {
        this.mContext = mContext;
        this.mCmdCodes = mCmdCodes;
        this.mCmdDates = mCmdDates;
        this.onCmdListener = onCmdListener;
        this.historique = historique;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        if(mCmdCodes.size()!=0){
            String liste = "(liste des nom pdts,code pdts,qte)";
            String msg = liste;
            String bl = "Boulangerie "+Math.round(10*Math.random()+1)+"(adresse, nom+tel responsable)";
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialogBuilder.setTitle("Details de "+mCmdCodes.get(position));
            if (historique){
                alertDialogBuilder.setMessage(bl+"\n"+liste);
            } else {
                alertDialogBuilder.setMessage(msg);
            }
            final AlertDialog alertDialog = alertDialogBuilder.create();

            holder.dateCmd.setText(mCmdDates.get(position));
            holder.codeCmd.setText(mCmdCodes.get(position));
            holder.detailsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.show();
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
