package org.fmi.tryme.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.fmi.tryme.JsonTestUtils;
import org.fmi.tryme.admin.beans.Trytest;
import org.fmi.tryme.test.beans.TestScore;
import org.fmi.tryme.test.beans.TestSubmission;
import org.junit.Before;
import org.junit.Test;

public class TestScorerTest {

	private TestScorer scorer;

	private Trytest test;

	@Before
	public void setup() throws IOException {
		test = JsonTestUtils.readJson("tests/test.json", Trytest.class);
		scorer = new TestScorerImpl();
	}

	@Test
	public void testScoreOneWrong() throws IOException {
		TestSubmission submission = JsonTestUtils.readJson("tests/test_submission.json", TestSubmission.class);

		TestScore score = scorer.score(test, submission.getAnswers());
		assertEquals("Unexpected score", 40, score.getScore());
	}

	@Test(expected = IllegalQuestionException.class)
	public void testSubmitWithInvalidQuestionId() throws IOException {
		TestSubmission submission = getSubmission("tests/test_submission_invalid_question_id.json");
		scorer.score(test, submission.getAnswers());
	}
	
	@Test(expected = IllegalAnswerException.class)
	public void testSubmitWithInvalidAnswerId() throws IOException {
		TestSubmission submission = getSubmission("tests/test_submission_invalid_answer_id.json");
		scorer.score(test, submission.getAnswers());
	}

	private TestSubmission getSubmission(String submissionFile) throws IOException {
		return JsonTestUtils.readJson(submissionFile, TestSubmission.class);
	}

}
