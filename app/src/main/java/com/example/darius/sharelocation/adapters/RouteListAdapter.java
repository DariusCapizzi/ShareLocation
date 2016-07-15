package com.example.darius.sharelocation.adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.models.Route;
import com.example.darius.sharelocation.models.Step;
import com.example.darius.sharelocation.ui.FriendsActivity;
import com.example.darius.sharelocation.ui.MainActivity;
import com.example.darius.sharelocation.ui.TripDetailFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RouteListAdapter extends RecyclerView.Adapter<RouteListAdapter.RouteViewHolder>{
    private ArrayList<Route> mRoutes = new ArrayList<Route>();
    private Context mContext;
    public static final String TAG = "RouteListAdapter";


    public RouteListAdapter(Context context, ArrayList<Route> routes) {
        mContext = context;
        mRoutes = routes;
    }


    @Override
    public RouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.direction_list_item, parent, false);
        return new RouteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RouteViewHolder holder, int position) {
        holder.bindDirection(mRoutes.get(position));
    }


    @Override
    public int getItemCount() {
        return mRoutes.size();
    }

    public class RouteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.tripInfo) TextView mDirectionView;
        @Bind(R.id.friendInfo) TextView mFriendView;
        @Bind(R.id.quickbadge) QuickContactBadge mBadge;
        @Bind(R.id.share) Button mShareButton;

        private Context mContext;

        public RouteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mDirectionView.setOnClickListener(this);
            mShareButton.setOnClickListener(this);

        }

        public void bindDirection(Route route) {

            mDirectionView.setText(route.getDistance() + "les,  " + route.getDuration() + ",  "  + Html.fromHtml("<br>") +"via "+route.getSummary()+ " in  " + route.getStepArray().size() + " steps" );

            // TODO implement this on TripActivity header
//            mDirectionView.setText("FROM: "+ Html.fromHtml("<br>")+route.getStartAddress() + Html.fromHtml("<br>") + "TO:  " + Html.fromHtml("<br>")+ route.getEndAddress());
            //TODO put this in step adapter
//            mDirectionView.setText(route.getDistance() + ",  " + route.getDuration() + ",  "  + Html.fromHtml("<br>") + Html.fromHtml(route.getHtmlInstruction()));

            if (route.getFriendArray().size() > 0 ){
                //TODO support multiple friends in XML
                mShareButton.setVisibility(View.GONE);
                for (int i = 0; i< route.getFriendArray().size(); i++) {

                        mFriendView.setText(route.getFriendArray().get(i).getFriendName());
                        mBadge.setImageBitmap(route.getFriendArray().get(i).getThumb());
                        mBadge.getLayoutParams().height = 140;
                        mBadge.getLayoutParams().width =140;

                }

            }else {
                mFriendView.setText("");
                mBadge.setImageResource(0);
                mBadge.getLayoutParams().height = 0;
                mBadge.getLayoutParams().width = 0;
            }
        }


        @Override
        public void onClick(View v) {
            if (v == mShareButton){

                Intent intent = new Intent(mContext, FriendsActivity.class);
                intent.putExtra(MainActivity.EXTRA_DIRECTION, mRoutes.toArray(new Route[mRoutes.size()])[getAdapterPosition()] );
                intent.putExtra(MainActivity.EXTRA_LIST_POSITION, getAdapterPosition());

                ((Activity) mContext).startActivityForResult(intent, MainActivity.REQUEST_FRIENDS);

            } else if (v == mDirectionView) {
                Bundle bundle = new Bundle();
//              //if I decide I want just the steps, ill need to also implement parcable on step  ArrayList<Step> stepArray = mRoutes.toArray(new Route[mRoutes.size()])[getAdapterPosition()].getStepArray();
                bundle.putParcelable("route",mRoutes.toArray(new Route[mRoutes.size()])[getAdapterPosition()]);
                Log.d(TAG, "onClick: ");
                //TODO start trip detail fragment here
                TripDetailFragment tripDetailFragment = new TripDetailFragment();
                FragmentManager fragmentManager = ((Activity) mContext).getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                tripDetailFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.placeHolder, tripDetailFragment);
                fragmentTransaction.commit();
            }

        }
    }
}
