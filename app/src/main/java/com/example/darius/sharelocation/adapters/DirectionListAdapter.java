package com.example.darius.sharelocation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.models.Direction;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DirectionListAdapter extends RecyclerView.Adapter<DirectionListAdapter.DirectionViewHolder>{
    private ArrayList<String> mDirections = new ArrayList<String>();
    private Context mContext;

    public DirectionListAdapter(Context context, ArrayList<String> directions) {
        mContext = context;
        mDirections = directions;
    }


    @Override
    public DirectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.direction_list_item, parent, false);
        DirectionViewHolder viewHolder = new DirectionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DirectionViewHolder holder, int position) {
        holder.bindDirection(mDirections.get(position));
    }


    @Override
    public int getItemCount() {
        return mDirections.size();
    }

    public class DirectionViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tripInfo) TextView mDirectionView;
//        @Bind(R.id.restaurantNameTextView) TextView mNameTextView;
//        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
//        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public DirectionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindDirection(String direction) {
            mDirectionView.setText(direction);
//            mCategoryTextView.setText(restaurant.getCategories().get(0));
//            mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
        }
    }
}
