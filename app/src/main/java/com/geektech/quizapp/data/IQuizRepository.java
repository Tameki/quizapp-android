package com.geektech.quizapp.data;

import com.geektech.quizapp.model.Question;

import java.util.List;

public interface IQuizRepository {

    //TODO: Add difficulty input
    void getQuestions(int amount, QuestionsCallback callback);

    interface QuestionsCallback {
        void onSuccess(List<Question> questions);

        void onFailure(String message);
    }
}
