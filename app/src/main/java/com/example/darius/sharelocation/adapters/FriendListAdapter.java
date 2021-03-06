package com.example.darius.sharelocation.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.models.Friend;
import com.example.darius.sharelocation.ui.FriendsActivity;
import com.example.darius.sharelocation.ui.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 7/8/16.
 */
public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendViewHolder>{
    private Context mContext;
    private ArrayList<Friend> mFriends;
    private int mTripPosition;
    public static final String TAG = FriendsActivity.class.getSimpleName();

    public FriendListAdapter(Context context, ArrayList<Friend> friends, int tripPosition) {
        mContext = context;
        mFriends = friends;
        mTripPosition = tripPosition;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list_item, parent, false);
        FriendViewHolder viewHolder = new FriendViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        holder.bindFriend(mFriends.get(position));
    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.friendInfo) TextView mFriendView;
        @Bind(R.id.quickbadge) QuickContactBadge mBadge;

        private Context mContext;
        public FriendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindFriend(Friend friend) {
            if (friend.getFriendName().equals(friend.getNumber())){
                mFriendView.setText("no name  " + friend.getNumber());
            } else {
                mFriendView.setText(friend.getFriendName() + "  " + friend.getNumber());

            }
            if (friend.getThumbUri() == null){
                Log.d(TAG, "bindFriend: "+ friend.getThumbUri());

                Picasso.with(mContext).load(R.drawable.aragorn).into(mBadge);
            } else {
                Picasso.with(mContext).load(friend.getThumbUri()).into(mBadge);

            }

        }

        @Override
        public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MainActivity.EXTRA_FRIEND, mFriends.toArray(new Friend[mFriends.size()])[getAdapterPosition()]);
                resultIntent.putExtra(MainActivity.EXTRA_LIST_POSITION, mTripPosition);
                ((Activity) mContext).setResult(((Activity) mContext).RESULT_OK, resultIntent);
                ((Activity) mContext).finish();

//            Intent intent = new Intent(mContext, FriendsActivity.class);
//            intent.putExtra(MainActivity.EXTRA_DIRECTION, mRoutes.toArray(new Route[0])[getAdapterPosition()] );
//            intent.putExtra(MainActivity.EXTRA_LIST_POSITION, getAdapterPosition());
//
//            ((Activity) mContext).startActivityForResult(intent, MainActivity.REQUEST_FRIENDS);

        }
    }
}
