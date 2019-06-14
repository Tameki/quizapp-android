package com.geektech.quizapp.ui.quiz;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.ui.quiz.recycler.QuizAdapter;
import com.geektech.quizapp.ui.quiz.recycler.QuizViewHolder;

public class QuizActivity extends AppCompatActivity
    implements QuizViewHolder.QuizViewHolderListener {

    private QuizViewModel mViewModel;
    private RecyclerView mRecycler;
    private QuizAdapter mAdapter;
    private ProgressBar mProgress;
    private TextView mProgressText;
    private TextView mTitle;

    private final static String EXTRA_AMOUNT = "amount";

    public static void start(Context context, int amount) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amount);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        mViewModel.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mViewModel = ViewModelProviders.of(this)
                .get(QuizViewModel.class);

        initView();

        mViewModel.loading.observe(this, loading -> {
            if (loading) {
                //TODO: Show loading progress
            } else {
                //TODO: Hide loading progress
            }
        });

        mViewModel.questions.observe(this, questions -> {
            if (questions != null) {
                Log.d("ololo", "Questions update");
                mAdapter.setQuestions(questions);
                mProgress.setMax(questions.size());
            }
        });

        mViewModel.currentQuestionPosition.observe(this, position -> {
            if (position != null) {
                mProgress.setProgress(position + 1);
                mProgressText.setText((position + 1) + "/" + mAdapter.getItemCount());
                mTitle.setText(mViewModel.getTitle());

                mRecycler.smoothScrollToPosition(position);
            }
        });

        mViewModel.loadQuestions(getIntent().getIntExtra(EXTRA_AMOUNT, 10));
    }

    private void initRecycler() {
        mAdapter = new QuizAdapter(this);
        mRecycler = findViewById(R.id.quiz_recycler);
        mRecycler.setAdapter(mAdapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(
                this,
                RecyclerView.HORIZONTAL,
                false);
        mRecycler.setLayoutManager(lm);

        //Prevent user touch scroll
        mRecycler.setOnTouchListener((v, event) -> true);
    }

    private void initView() {
        mProgress = findViewById(R.id.quiz_progress);
        mProgressText = findViewById(R.id.quiz_progress_text);
        mTitle = findViewById(R.id.quiz_title);

        mProgressText.setEnabled(false);

        initRecycler();

        findViewById(R.id.quiz_skip).setOnClickListener(v -> mViewModel.onNextClick());
        findViewById(R.id.quiz_back).setOnClickListener(v -> mViewModel.onBackPressed());
    }

    @Override
    public void onAnswerSelected(int position, String answer) {
        mViewModel.onAnswerSelected(position, answer);
    }
}
