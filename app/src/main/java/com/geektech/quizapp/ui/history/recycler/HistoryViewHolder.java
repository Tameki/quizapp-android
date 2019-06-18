package com.geektech.quizapp.ui.history.recycler;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.model.ShortQuizResult;

public class HistoryViewHolder extends RecyclerView.ViewHolder {
    public HistoryViewHolder(@NonNull View itemView, HistoryViewHolderListener listener) {
        super(itemView);
    }

    void onBind(ShortQuizResult quizResult) {

    }

    public interface HistoryViewHolderListener {
        void onClick(int position);

        void onOptionsClick(int position);
    }
}
