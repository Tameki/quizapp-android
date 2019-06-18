package com.geektech.quizapp.ui.history;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.QuizResult;
import com.geektech.quizapp.ui.history.recycler.HistoryAdapter;
import com.geektech.quizapp.ui.history.recycler.HistoryViewHolder;

import java.util.List;

public class HistoryFragment extends Fragment
    implements HistoryViewHolder.HistoryViewHolderListener {

    private HistoryViewModel mViewModel;
    private RecyclerView mRecycler;
    private HistoryAdapter mAdapter;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);

        mViewModel.history.observe(this, quizResults -> {
            mAdapter.setQuizResults(quizResults);
            Log.d("ololo", "History update " + quizResults.size());
        });
    }

    private void init(View view) {
        mAdapter = new HistoryAdapter(this);

        mRecycler = view.findViewById(R.id.history_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public void onOptionsClick(int position) {

    }
}
