package com.geektech.quizapp.ui.quiz.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.model.TypeEnum;

public class QuizViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {

    private TextView mQuestion;
    private QuizViewHolderListener mListener;

    private View mMultipleContainer;
    private View mBooleanContainer;
    private View mOption1;
    private View mOption2;
    private View mOption3;
    private View mOption4;
    private View mOptionTrue;
    private View mOptionFalse;

    public QuizViewHolder(
            @NonNull View itemView,
            QuizViewHolderListener listener
    ) {
        super(itemView);
        mListener = listener;
        mQuestion = itemView.findViewById(R.id.question_text);

        mOption1 = itemView.findViewById(R.id.question_option_1);
        mOption1.setOnClickListener(this);

        itemView.findViewById(R.id.question_option_2).setOnClickListener(this);
        itemView.findViewById(R.id.question_option_3).setOnClickListener(this);
        itemView.findViewById(R.id.question_option_4).setOnClickListener(this);
        itemView.findViewById(R.id.question_option_true).setOnClickListener(this);
        itemView.findViewById(R.id.question_option_false).setOnClickListener(this);
        //TODO: Add options on click listener

    }

    public void onBind(Question question) {
        mQuestion.setText(Html.fromHtml(question.getQuestion()));

        if (question.getType() == TypeEnum.MULTIPLE) {
            //TODO: Set multiple container visible
            //TODO: Set boolean container gone
        } else {
            //TODO: Set boolean container visible
            //TODO: Set multiple container gone
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.question_option_1:
                mListener.onAnswerSelected(getAdapterPosition(), "asd");
                break;
            case R.id.question_option_2:
                break;
            case R.id.question_option_3:
                break;
            case R.id.question_option_4:
                break;
            case R.id.question_option_true:
                break;
            case R.id.question_option_false:
                break;
        }
    }

    public interface QuizViewHolderListener {
        void onAnswerSelected(int position, String answer);
    }
}
