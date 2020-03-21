package ez.management.boulangerie;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterItem extends RecyclerView.Adapter<RecyclerViewAdapterItem.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapterItem";

    private ArrayList<String> mPdtNames = new ArrayList<>();
    private ArrayList<String> mPdtCodes = new ArrayList<>();
    private ArrayList<Integer> mQtes = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterItem(Context mContext, ArrayList<String> mPdtNames,
                                   ArrayList<String> mPdtCodes, ArrayList<Integer> mQtes) {
        this.mPdtNames = mPdtNames;
        this.mPdtCodes = mPdtCodes;
        this.mQtes = mQtes;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,
                false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        if(mPdtCodes.size() != 0) {
            holder.nom.setText(mPdtNames.get(position));
            holder.code.setText(mPdtCodes.get(position));
            holder.qte.setText(mQtes.get(position).toString());
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: clicked on: " + mPdtNames.get(position));
                    //gonna change this for notifier to delete clicked item
                    Toast.makeText(mContext, "hola", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPdtCodes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nom;
        TextView code;
        TextView qte;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.pdtNom);
            code = itemView.findViewById(R.id.pdtCode);
            qte = itemView.findViewById(R.id.qte);
            parentLayout =itemView.findViewById(R.id.parent_layout);

        }
    }
}
