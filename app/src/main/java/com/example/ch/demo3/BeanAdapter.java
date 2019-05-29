package com.example.ch.demo3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ch.demo3.bean.Bean;

import java.util.ArrayList;

public class BeanAdapter extends RecyclerView.Adapter<BeanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Bean.ResultsEntity> list;

    public BeanAdapter(Context context, ArrayList<Bean.ResultsEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Bean.ResultsEntity resultsEntity = list.get(i);

        Glide.with(context).load(resultsEntity.getUrl())
                .apply(new RequestOptions().circleCrop())
                .into(viewHolder.iv);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOnLickListener != null) {
                    myOnLickListener.MyClick(i, resultsEntity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }

    public interface MyOnLickListener {
        void MyClick(int position, Bean.ResultsEntity resultsEntity);
    }

    private MyOnLickListener myOnLickListener;

    public void setMyOnLickListener(MyOnLickListener myOnLickListener) {
        this.myOnLickListener = myOnLickListener;
    }
}
