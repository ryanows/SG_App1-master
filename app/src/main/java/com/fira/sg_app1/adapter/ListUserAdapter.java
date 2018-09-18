package com.fira.sg_app1.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fira.sg_app1.R;
import com.fira.sg_app1.model.User;

import java.util.ArrayList;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserVH>
{
    private ArrayList<User> mData;

    public ListUserAdapter(ArrayList<User> mData){
        this.mData = mData;
    }

    @NonNull
    @Override
    public ListUserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cv_user, parent, false);
        return new ListUserVH(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListUserVH holder, int position) {
        holder.tvNama.setText(mData.get(position).nama);
        holder.tvUmur.setText(Integer.toString(mData.get(position).umur));
        holder.tvGender.setText(mData.get(position).gender);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
class ListUserVH extends RecyclerView.ViewHolder {

    TextView tvNama, tvUmur, tvGender;

    ListUserVH(View itemView) {
        super(itemView);

        tvNama = itemView.findViewById(R.id.tv_nama);
        tvUmur = itemView.findViewById(R.id.tv_umur);
        tvGender = itemView.findViewById(R.id.tv_gender);
    }
}