package com.example.darius.sharelocation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.darius.sharelocation.R;
import com.example.darius.sharelocation.models.Route;
import com.example.darius.sharelocation.models.Step;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 7/15/16.
 */
public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepViewHolder> {
    private ArrayList<Step> mSteps = new ArrayList<Step>();
    private Context mContext;




    public StepListAdapter(Context context, ArrayList<Step> Steps) {
        mContext = context;
        mSteps = Steps;
    }


    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_list_item, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        holder.bindDirection(mSteps.get(position));
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.stepInfo)
        TextView mStepView;
        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindDirection(Step step) {
            mStepView.setText(step.getDistance() + ",  " + step.getDuration() + ",  "  + Html.fromHtml("<br>") + Html.fromHtml(step.getHtmlInstruction()));
        }
    }
}
