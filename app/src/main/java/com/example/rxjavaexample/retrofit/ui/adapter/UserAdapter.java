package com.example.rxjavaexample.retrofit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavaexample.R;
import com.example.rxjavaexample.retrofit.db.entity.Post;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.DataViewHolder> {

    ArrayList<Post> dataModelList = new ArrayList<>();
    Context context;

    public UserAdapter(Context context) {
        this.context = context;
    }


    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row, parent, false);
        DataViewHolder holder = new DataViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {

        holder.getTxtUserId().setText("" + dataModelList.get(position).getUserId());
        holder.getTxtId().setText("" + dataModelList.get(position).getId());
        holder.getTxtTitle().setText(dataModelList.get(position).getTitle());
        holder.getTxtBody().setText(dataModelList.get(position).getBody());


//        Glide.with(context).load(dataModelList.get(position).getProfile_imge()).into(holder.getImgProfile());
    }

    @Override
    public int getItemCount() {
        return dataModelList.size() > 0 ? dataModelList.size() : 0;
    }

    public void setDataToAdapter(ArrayList<Post> dataModelList) {
        this.dataModelList = dataModelList;
        notifyDataSetChanged();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView userId, id, title, body;
//        private ImageView imgProfile;

        public DataViewHolder(View itemView) {
            super(itemView);
        }

        public TextView getTxtUserId() {
            if (userId == null) {
                userId = itemView.findViewById(R.id.txtUserId);
            }
            return userId;
        }

        public TextView getTxtId() {
            if (id == null) {
                id = itemView.findViewById(R.id.txtId);
            }
            return id;
        }

        public TextView getTxtTitle() {
            if (title == null) {
                title = itemView.findViewById(R.id.txtTitle);
            }
            return title;
        }

        public TextView getTxtBody() {
            if (body == null) {
                body = itemView.findViewById(R.id.txtBody);
            }
            return body;
        }
    }
}
