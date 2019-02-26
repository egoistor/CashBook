package com.example.wangbeimin.cashbook.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.wangbeimin.cashbook.R;

import java.util.List;

public class CashAdapter extends RecyclerView.Adapter<CashAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private RecyclerViewOnItemClickListener onItemClickListener;
    private RecyclerViewOnItemLongClickListener onItemLongClickListener;
    private String name;


    private List<Cash> myCashList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        TextView CashName;
        TextView TimeShow;

        public ViewHolder(View view){
            super(view);
            this.view=view;
            CashName = view.findViewById(R.id.cash_message);
            TimeShow = view.findViewById(R.id.time_shows);
        }
    }

    public CashAdapter(List<Cash> cashList){
        myCashList = cashList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cash, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onClick(View v) { if (onItemClickListener != null) {
        onItemClickListener.onItemClickListener(v, (Integer) v.getTag());
    }
    }

    @Override public boolean onLongClick(View v) {
        return onItemLongClickListener != null && onItemLongClickListener.onItemLongClickListener(v, (Integer) v.getTag());
    }

    public void setRecyclerViewOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }
    public void setOnItemLongClickListener(RecyclerViewOnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface RecyclerViewOnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public interface RecyclerViewOnItemLongClickListener {
        boolean onItemLongClickListener(View view, int position);
    }
    @Override

    public void onBindViewHolder(final ViewHolder holder, int position) {

        Cash cash = myCashList.get(position);
        name=cash.getType()+cash.getTag()+cash.getIncome();
        holder.CashName.setText(name);
        holder.TimeShow.setText(cash.getYear()+"/"+cash.getMonth()+"/"+cash.getDay());
        holder.view.setTag(position);
    }


    @Override
    public int getItemCount() {
        return myCashList.size();
    }
}
