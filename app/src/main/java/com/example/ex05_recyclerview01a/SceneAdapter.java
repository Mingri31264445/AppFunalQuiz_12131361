package com.example.ex05_recyclerview01a;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.ViewHolder>{
    private ArrayList<SceneInfo> listdata;
    private static final String TAG = "SceneAdapter";

    // RecyclerView recyclerView;
    public SceneAdapter(ArrayList<SceneInfo> listdata) {
        this.listdata = listdata;
    }

    @NonNull
    @NotNull
    @Override
    public SceneAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    public void updateData(List<SceneInfo> newData) {
        Log.d(TAG, "Updating data with new list size: " + newData.size());
        this.listdata.clear(); // 清除舊數據
        this.listdata.addAll(newData); // 添加新數據
        notifyDataSetChanged(); // 通知RecyclerView數據更新
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SceneAdapter.ViewHolder holder, int position) {
        final SceneInfo sceneInfo = (SceneInfo)listdata.get(position);//listdata[position];
        holder.textView.setText(sceneInfo.getDescription());
        holder.textView2.setText(sceneInfo.getCountry());
        holder.textView5.setText(sceneInfo.getISBN());
        holder.imageView.setImageResource(sceneInfo.getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), sceneInfo.getDescription() + "  有限時下殺優惠!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), SecondActivity.class);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public TextView textView2;
        public TextView textView5;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            this.textView2 = (TextView) itemView.findViewById(R.id.textView2);
            this.textView5 = (TextView) itemView.findViewById(R.id.textView5);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
