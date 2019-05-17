package com.example.SecondLab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.*;
import java.lang.reflect.Type;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    private List<Item> items;

    public ListAdapter() {

    }

    public ListAdapter(Context context, @NonNull String list) {
        this.context = context;
        Type typeToken = new TypeToken<List<Item>>() {}.getType();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        items = gson.fromJson(list, typeToken);
        items.remove(0);

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView icon;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            icon = view.findViewById(R.id.icon);
        }
    }

    @Override
    public int getItemCount() {
        if (items!= null)
            return items.size();
        else
            return 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_list_item,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Item item = items.get(i);
        viewHolder.name.setText(item.getName());
        Picasso.get().load(context.getResources().getString(R.string.img_url)
                + item.getGraphic()).into(viewHolder.icon);

    }
}