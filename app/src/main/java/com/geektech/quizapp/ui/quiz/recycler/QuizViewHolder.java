package com.geektech.quizapp.ui.quiz.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.model.TypeEnum;

public class QuizViewHolder extends RecyclerView.ViewHolder {

    private TextView mQuestion;

    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        mQuestion = itemView.findViewById(R.id.question_text);
    }

    public void onBind(Question question) {
        mQuestion.setText(question.getQuestion());

        if (question.getType() == TypeEnum.MULTIPLE) {
            //TODO: Set multiple container visible
            //TODO: Set boolean container gone
        } else {
            //TODO: Set boolean container visible
            //TODO: Set multiple container gone
        }
    }
}
