package com.example.verylonglist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.verylonglist.Item;
import com.example.verylonglist.R;

import java.util.List;
import com.example.verylonglist.parser.StringToIntParser;

public class LabRecyclerViewAdapter extends RecyclerView.Adapter<LabRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Item>itemList;

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView idTextView;
        private ImageView idImag;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.idTextView);
            linearLayout = itemView.findViewById(R.id.itemLinearLayout);
        }
    }

    public LabRecyclerViewAdapter(Context context, List<Item> items){
        this.context = context;
        this.itemList = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Item item = itemList.get(i);
        if(itemList.get(i).color.equals("White")) viewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        else viewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorGrey));
        viewHolder.idTextView.setText(StringToIntParser.getParsedString(item.getId()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
