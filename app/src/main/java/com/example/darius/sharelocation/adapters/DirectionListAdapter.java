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
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.models.Direction;
import com.example.darius.sharelocation.ui.FriendsActivity;
import com.example.darius.sharelocation.ui.MainActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DirectionListAdapter extends RecyclerView.Adapter<DirectionListAdapter.DirectionViewHolder>{
    private ArrayList<Direction> mDirections = new ArrayList<Direction>();
    private Context mContext;
    public static final String TAG = MainActivity.class.getSimpleName();


    public DirectionListAdapter(Context context, ArrayList<Direction> directions) {
        mContext = context;
        mDirections = directions;
    }


    @Override
    public DirectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.direction_list_item, parent, false);
        return new DirectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DirectionViewHolder holder, int position) {
        holder.bindDirection(mDirections.get(position));
    }


    @Override
    public int getItemCount() {
        return mDirections.size();
    }

    public class DirectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.tripInfo) TextView mDirectionView;
        @Bind(R.id.friendInfo) TextView mFriendView;
        @Bind(R.id.quickbadge) QuickContactBadge mBadge;

        private Context mContext;

        public DirectionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindDirection(Direction direction) {
            mDirectionView.setText(direction.getDistance() + ",  " + direction.getDuration() + ",  "  + Html.fromHtml("<br>") + Html.fromHtml(direction.getHtmlInstruction()));
//            Log.d(TAG, "bindDirection: "+ direction.getFriend());

            // TODO if
            if (direction.getFriendArray().size() > 0 ){

                for (int i = 0 ; i< direction.getFriendArray().size(); i++) {
//                    Log.d(TAG, "bindDirection: "+direction.getFriendArray().get(i).getDirectionArray().contains(direction));
//                    if (direction.getFriendArray().get(i).getDirectionArray().contains(direction)){

                        mFriendView.setText(direction.getFriendArray().get(i).getFriendName());
                        mBadge.setImageBitmap(direction.getFriendArray().get(i).getThumb());
                        mBadge.getLayoutParams().height = 140;
                        mBadge.getLayoutParams().width =140;
//                    } else {
//                        mFriendView.setText("");
//                    }

                }

            }else {
                mFriendView.setText("");
                mBadge.setImageResource(0);
            }
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, FriendsActivity.class);
            intent.putExtra(MainActivity.EXTRA_DIRECTION, mDirections.toArray(new Direction[mDirections.size()])[getAdapterPosition()] );
            intent.putExtra(MainActivity.EXTRA_LIST_POSITION, getAdapterPosition());

            ((Activity) mContext).startActivityForResult(intent, MainActivity.REQUEST_FRIENDS);
        }
    }
}
