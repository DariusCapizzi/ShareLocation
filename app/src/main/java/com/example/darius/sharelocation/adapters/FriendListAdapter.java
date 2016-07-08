package com.example.darius.sharelocation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.models.Friend;
import com.example.darius.sharelocation.ui.FriendsActivity;
import com.example.darius.sharelocation.ui.MainActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 7/8/16.
 */
public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendViewHolder>{
    private Context mContext;
    private ArrayList<Friend> mFriends;
    public static final String TAG = FriendsActivity.class.getSimpleName();

    public FriendListAdapter(Context context, ArrayList<Friend> friends) {
        mContext = context;
        mFriends = friends;
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
            mFriendView.setText(friend.getFriendName());
            mBadge.setImageBitmap(friend.getThumb());
//            Log.d(TAG, "bindDirection: "+ direction.getFriend());
        }

        @Override
        public void onClick(View view) {

        }
    }
}
