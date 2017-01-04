package org.fmi.tryme.test;

import java.util.List;

import org.fmi.tryme.admin.beans.Answer;
import org.fmi.tryme.admin.beans.Question;
import org.fmi.tryme.admin.beans.Trytest;
import org.fmi.tryme.test.beans.AnsweredQuestion;
import org.fmi.tryme.test.beans.ScoredQuestion;
import org.fmi.tryme.test.beans.TestScore;
import org.springframework.stereotype.Component;

@Component
public class TestScorerImpl implements TestScorer {

	@Override
	public TestScore score(Trytest test, List<AnsweredQuestion> answeredQuestions) {
		TestScore result = new TestScore(test.getDescription().getId());

		for (AnsweredQuestion answeredQuestion : answeredQuestions) {
			Question question = getQuestion(test, answeredQuestion.getQuestionId());
			Answer answer = getAnswer(question, answeredQuestion.getAnswerId());

			result.addQuestion(new ScoredQuestion(question, answer.isCorrect()));
		}
		return result;
	}

	private Answer getAnswer(Question question, String answerId) {
		Answer answer = question.getAnswer(answerId);
		if (answer == null) {
			throw new IllegalAnswerException("Cannot find question with id: " + answerId);
		}
		return answer;
	}

	private Question getQuestion(Trytest test, String questionId) {
		Question question = test.getQuestion(questionId);
		if (question == null) {
			throw new IllegalQuestionException("Cannot find question with id: " + questionId);
		}
		return question;
	}

}
