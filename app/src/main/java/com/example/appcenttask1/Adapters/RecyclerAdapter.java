package com.example.appcenttask1.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appcenttask1.Model.Model;
import com.example.appcenttask1.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG = "RecyclerAdapter";

    private Context mContext;
    private ArrayList<String> item_title = new ArrayList<>();
    private ArrayList<String> item_desc = new ArrayList<>();
    private ArrayList<String> item_date = new ArrayList<>();
    private ArrayList<String> item_imgUrl = new ArrayList<>();
    private List<Model> modelParentsList;
    private List<Model> modelParentsListTemp;

    public RecyclerAdapter(Context mContext, List<Model> modelParentsList) {
        this.mContext = mContext;
        this.modelParentsList = modelParentsList;
        modelParentsListTemp = new ArrayList<>(modelParentsList);
    }

    public RecyclerAdapter(Context mContext, ArrayList<String> item_title, ArrayList<String> item_desc,
                           ArrayList<String> item_date, ArrayList<String> item_imgUrl, List<Model> modelParentsList,
                           List<Model> modelParentsListTemp)
    {
        this.mContext = mContext;
        this.item_title = item_title;
        this.item_desc = item_desc;
        this.item_date = item_date;
        this.item_imgUrl = item_imgUrl;
        this.modelParentsList = modelParentsList;
        this.modelParentsListTemp = modelParentsListTemp;
        modelParentsListTemp = new ArrayList<>(modelParentsList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called" + position);

        //Test
//        holder.tv1_title.setText(item_title.get(position));
//        holder.tv2_desc.setText(item_desc.get(position));
//        holder.tv3_date.setText(item_date.get(position));

        //Major
        holder.tv1_title.setText(modelParentsListTemp.get(position).getTitle());
        holder.tv2_desc.setText(modelParentsListTemp.get(position).getDescription());
        holder.tv3_date.setText(modelParentsListTemp.get(position).getPublishedAt());

        Glide.with(mContext)
                .asBitmap()
                .load(modelParentsListTemp.get(position).getUrlToImage())
                .into(holder.imageView);


        holder.cl_item_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Position" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelParentsListTemp.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView tv1_title;
        TextView tv2_desc;
        TextView tv3_date;
        ConstraintLayout cl_item_container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.vholder_items_img);
            tv1_title = itemView.findViewById(R.id.vholder_items_tv_title);
            tv2_desc = itemView.findViewById(R.id.vholder_items_tv_desc);
            tv3_date = itemView.findViewById(R.id.vholder_items_tv_published);
            cl_item_container = itemView.findViewById(R.id.vholder_cl_item_container);

        }
    }
}
