package com.geektech.quizapp.ui.quiz;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.geektech.quizapp.App;
import com.geektech.quizapp.data.IQuizRepository;
import com.geektech.quizapp.model.Question;

import java.util.List;

class QuizViewModel extends ViewModel {

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();

    //TODO: Add resultEvent and closeEvent (SingleLiveEvent)

    void loadQuestions(int amount) {
        loading.setValue(true);
        App.quizRepository.getQuestions(amount, new IQuizRepository.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                loading.setValue(false);
                QuizViewModel.this.questions.setValue(questions);
                currentQuestionPosition.setValue(0);
                for (Question question : questions) {
                    Log.d("ololo", question.toString());
                }
            }

            @Override
            public void onFailure(String message) {
                loading.setValue(false);
                Log.d("ololo", message);
            }
        });
    }

    void onNextClick() {
        int currentPosition = currentQuestionPosition.getValue() + 1;
        if (currentPosition == questions.getValue().size()) {
            //TODO: Open result screen
        } else {
            currentQuestionPosition.setValue(currentPosition);
        }
    }

    void onBackPressed() {
        int currentPosition = currentQuestionPosition.getValue() - 1;
        if (currentPosition >= 0) {
            currentQuestionPosition.setValue(currentPosition);
        } else {
            //TODO: Close quiz screen
        }
    }

    String getTitle() {
        if (questions.getValue() != null && currentQuestionPosition.getValue() != null) {
            return questions.getValue()
                    .get(currentQuestionPosition.getValue())
                    .getCategory();
        } else {
            return "";
        }
    }

}
