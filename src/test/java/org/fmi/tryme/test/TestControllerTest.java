package org.fmi.tryme.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.fmi.tryme.JsonTestUtils;
import org.fmi.tryme.admin.TestProvider;
import org.fmi.tryme.admin.beans.TestDescription;
import org.fmi.tryme.admin.beans.Trytest;
import org.fmi.tryme.http.BadRequestException;
import org.fmi.tryme.http.NotFoundException;
import org.fmi.tryme.test.beans.TestResult;
import org.fmi.tryme.test.beans.TestScore;
import org.fmi.tryme.test.beans.TestSubmission;
import org.fmi.tryme.test.beans.UnscoredTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TestControllerTest {

	@Mock
	private TestResultRepository repository;

	@Mock
	private TestProvider testProvider;

	private TestController controller;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		controller = new TestController(repository, testProvider, new TestScorerImpl());
	}

	@Test
	public void testStartTest() throws IOException {
		Trytest test = mockTest("test_01");
		
		TestDescription description = test.getDescription();
		UnscoredTest startedTest = controller.startTest(description.getId());

		assertEquals("Unexpected test ID", description.getId(), startedTest.getId());
		assertEquals("Unexpected test title", description.getTitle(), startedTest.getTitle());
		assertEquals("Unexpected test subject", description.getSubject(), startedTest.getSubject());
		assertEquals("Unexpected test category", description.getCategory(), startedTest.getCategory());
	}

	@Test
	public void testSubmitAnswer() throws IOException {
		TestSubmission submission = mockTestSubmission("tests/test_submission.json");

		TestScore result = controller.submitTest(submission.getId(), submission);
		assertEquals("Unexpected score", 40, result.getScore());

		ArgumentCaptor<TestResult> resultCaptor = ArgumentCaptor.forClass(TestResult.class);
		Mockito.verify(repository).save(resultCaptor.capture());

		TestResult persisted = resultCaptor.getValue();
		assertEquals("Unexpected score", result.getScore(), persisted.getTestScore());
		assertEquals("Unexpected category", "History", persisted.getTestCategory());
		assertEquals("Unexpected user", "test@google.com", persisted.getUserEmail());
	}

	@Test(expected = NotFoundException.class)
	public void testSubmitWithInvalidTestId() throws IOException {
		TestSubmission submission = JsonTestUtils.readJson("tests/test_submission.json", TestSubmission.class);
		controller.submitTest(submission.getId(), submission);
	}

	@Test(expected = BadRequestException.class)
	public void testSubmitWithInvalidQuestionId() throws IOException {
		TestSubmission submission = mockTestSubmission("tests/test_submission_invalid_question_id.json");
		controller.submitTest(submission.getId(), submission);
	}

	@Test(expected = BadRequestException.class)
	public void testSubmitWithInvalidAnswerId() throws IOException {
		TestSubmission submission = mockTestSubmission("tests/test_submission_invalid_answer_id.json");
		controller.submitTest(submission.getId(), submission);
	}

	private TestSubmission mockTestSubmission(String submissionFile) throws IOException {
		TestSubmission submission = JsonTestUtils.readJson(submissionFile, TestSubmission.class);
		mockTest(submission.getId());
		return submission;
	}

	private Trytest mockTest(String testId) throws IOException {
		Trytest test = JsonTestUtils.readJson("tests/test.json", Trytest.class);
		Mockito.when(testProvider.getTest(testId)).thenReturn(test);
		return test;
	}

}
