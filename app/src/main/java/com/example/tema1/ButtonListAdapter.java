package com.example.tema1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ButtonListAdapter extends RecyclerView.Adapter<ButtonListAdapter.ButtonViewHolder> {
    private final LinkedList<String> mButtonList;
    private LayoutInflater mInflater;

    public ButtonListAdapter(Context context, LinkedList<String> ButtonList) {
        mInflater = LayoutInflater.from(context);
        this.mButtonList = ButtonList;
    }

    //Ne creem ViewHolderul
    @NonNull
    @Override
    public ButtonListAdapter.ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recyclerbutton, parent, false);

        return new ButtonViewHolder(mItemView, this);
    }
    //Setam textul pentru buton
    @Override
    public void onBindViewHolder(@NonNull ButtonListAdapter.ButtonViewHolder holder, int position) {
        holder.buttonItemView.setText(mButtonList.get(position));
    }

    @Override
    public int getItemCount() {
        return mButtonList.size();
    }

    class ButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final Button buttonItemView;
        final ButtonListAdapter mAdapter;

        public ButtonViewHolder(View itemView, ButtonListAdapter adapter) {
            super(itemView);
            buttonItemView = itemView.findViewById(R.id.button);
            this.mAdapter = adapter;
            buttonItemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.w("Recycler Click", "I clicked the button");
            Toast.makeText(view.getContext(), "Click!", Toast.LENGTH_SHORT).show();
        }
    }

}

