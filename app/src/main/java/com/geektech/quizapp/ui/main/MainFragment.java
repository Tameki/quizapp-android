package com.geektech.quizapp.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.ui.quiz.QuizActivity;
import com.geektech.quizapp.ui.widgets.SeekBarProgressListener;

public class MainFragment extends Fragment {

    private AppCompatSeekBar mSeekBar;
    private AppCompatSpinner mCategorySpinnner;
    private AppCompatSpinner mDifficultySpinner;

    private TextView mAmount;
    private View mStart;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSeekBar = view.findViewById(R.id.main_amount_seek_bar);
        mCategorySpinnner = view.findViewById(R.id.main_category_spinner);
        mDifficultySpinner = view.findViewById(R.id.main_difficulty_spinner);
        mAmount = view.findViewById(R.id.main_questions_amount);
        mStart = view.findViewById(R.id.main_start);



        mSeekBar.setOnSeekBarChangeListener(new SeekBarProgressListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAmount.setText(String.valueOf(progress));
            }
        });

        mStart.setOnClickListener(v -> {
            //TODO: Validate questions amount

            QuizActivity.start(getContext(), mSeekBar.getProgress());
            Log.d("ololo", "Start properties - amount:" + mSeekBar.getProgress()
                    + " category: " + mCategorySpinnner.getSelectedItemPosition()
                    + " difficulty: " + mDifficultySpinner.getSelectedItemPosition());
        });
    }
}
