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

    public RecyclerViewAdapterItem(ArrayList<String> mPdtNames,
                                   ArrayList<String> mPdtCodes, ArrayList<Integer> mQtes) {
        this.mPdtNames = mPdtNames;
        this.mPdtCodes = mPdtCodes;
        this.mQtes = mQtes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,
                false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        if(mPdtCodes.size() != 0) {
            holder.nom.setText(mPdtNames.get(position));
            holder.code.setText(mPdtCodes.get(position));
            holder.qte.setText(String.valueOf(mQtes.get(position)));

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
