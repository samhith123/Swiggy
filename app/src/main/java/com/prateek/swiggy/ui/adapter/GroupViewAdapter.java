package com.prateek.swiggy.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prateek.swiggy.R;
import com.prateek.swiggy.event.GroupItemClickEvent;
import com.prateek.swiggy.rest.Request.VariantGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class GroupViewAdapter extends RecyclerView.Adapter<GroupViewAdapter.MyViewHolder> {

    private ArrayList<VariantGroup> mVariant;

    private int isSelectedPosition = 0;

    public GroupViewAdapter() {

    }

    public GroupViewAdapter(ArrayList<VariantGroup> variants) {
        this.mVariant = variants;
    }

    public void refreshWithData(ArrayList<VariantGroup> variants) {
        this.mVariant = variants;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final VariantGroup varient = mVariant.get(position);
        holder.variantGroupTitle.setText(varient.getName());

        if (position == isSelectedPosition) {
            holder.mMainGroupView.setCardBackgroundColor(Color.WHITE);
        } else {
            holder.mMainGroupView.setCardBackgroundColor(Color.CYAN);
        }

        holder.mMainGroupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new GroupItemClickEvent(position));

                isSelectedPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mVariant != null) {
            return mVariant.size();
        }
        return 0;
    }

    public interface GroupClickListener {
        void onGroupItemClick(int clickIndex);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView mMainGroupView;
        TextView variantGroupTitle;

        public MyViewHolder(View view) {
            super(view);
            mMainGroupView = (CardView) view.findViewById(R.id.variant_item_card_view);
            variantGroupTitle = (TextView) view.findViewById(R.id.group_item_title);
        }
    }
}