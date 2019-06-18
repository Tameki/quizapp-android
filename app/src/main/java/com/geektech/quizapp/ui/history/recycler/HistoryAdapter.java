package com.geektech.quizapp.ui.history.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.ShortQuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ShortQuizResult> mQuizResults = new ArrayList<>();
    private HistoryViewHolder.HistoryViewHolderListener mListener;

    public HistoryAdapter(HistoryViewHolder.HistoryViewHolderListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HistoryViewHolder) {
            ((HistoryViewHolder) holder).onBind(mQuizResults.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mQuizResults.size();
    }

    public void setQuizResults(List<ShortQuizResult> quizResults) {
        mQuizResults.clear();
        mQuizResults.addAll(quizResults);
        notifyDataSetChanged();
    }
}
