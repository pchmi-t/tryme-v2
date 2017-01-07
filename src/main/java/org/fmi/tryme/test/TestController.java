package org.fmi.tryme.test;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.fmi.tryme.admin.TestProvider;
import org.fmi.tryme.admin.beans.Category;
import org.fmi.tryme.admin.beans.TestDescription;
import org.fmi.tryme.admin.beans.Trytest;
import org.fmi.tryme.http.BadRequestException;
import org.fmi.tryme.http.NotFoundException;
import org.fmi.tryme.test.beans.AnsweredQuestion;
import org.fmi.tryme.test.beans.TestResult;
import org.fmi.tryme.test.beans.TestScore;
import org.fmi.tryme.test.beans.TestSubmission;
import org.fmi.tryme.test.beans.UnscoredTest;
import org.fmi.tryme.user.AuthenticatedUser;
import org.fmi.tryme.user.UserService;
import org.fmi.tryme.user.UserServiceFactory;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;

@RestController
@RequestMapping("/api/v1/tests")
public class TestController {

	private static final String CATEGORY_ID_PARAM = "category";
	private static final String TEST_ID_PARAM = "testID";

	private final TestResultRepository resultsRepository;
	private final TestProvider testProvider;
	private final TestScorer testScorer;
	private final UserServiceFactory userServiceFactory;

	@Autowired
	public TestController(TestResultRepository repository, TestProvider testProvider, TestScorer testScorer,
			UserServiceFactory userServiceFactory) {
		this.resultsRepository = repository;
		this.testProvider = testProvider;
		this.userServiceFactory = userServiceFactory;
		this.testScorer = testScorer;
	}

	@RequestMapping(value = "categories", method = RequestMethod.GET)
	public Set<Category> getCategories() {
		return testProvider.getCategories();
	}

	@RequestMapping(value = "categories/{" + CATEGORY_ID_PARAM + "}/descriptions", method = RequestMethod.GET)
	public Set<TestDescription> getTestDescriptionsForCategory(
			@NotBlank @PathVariable(CATEGORY_ID_PARAM) String categoryID) {
		return testProvider.getTests(categoryID);
	}

	@RequestMapping(value = "{" + TEST_ID_PARAM + "}", method = RequestMethod.GET)
	public UnscoredTest startTest(@NotBlank @PathVariable(TEST_ID_PARAM) String testID) {
		Trytest tryTest = getTest(testID);
		return UnscoredTest.fromTest(tryTest);
	}

	@Transactional
	@RequestMapping(value = "{" + TEST_ID_PARAM + "}/submit", method = RequestMethod.POST)
	public TestScore submitTest(@NotBlank @PathVariable(TEST_ID_PARAM) String testID,
			@RequestBody @Valid TestSubmission submittedTest) {
		Trytest test = getTest(testID);

		TestScore score = scoreTest(test, submittedTest.getAnswers());

		UserService userService = userServiceFactory.create();
		AuthenticatedUser user = userService.getUser();

		TestResult result = new TestResult();
		result.setTestCategory(test.getDescription().getCategory().getName());
		result.setTestScore(score.getScore());
		result.setUserId(user.getId());
		resultsRepository.save(result);

		return score;
	}

	@Transactional
	@RequestMapping(value = "results", method = RequestMethod.GET)
	public Set<TestResult> getAllResults() {
		Iterable<TestResult> results = resultsRepository.findAll();
		return Sets.newHashSet(results);
	}

	private TestScore scoreTest(Trytest test, List<AnsweredQuestion> answered) {
		try {
			return testScorer.score(test, answered);
		} catch (IllegalAnswerException | IllegalQuestionException e) {
			throw new BadRequestException("Cannot score test with id: " + test.getDescription().getId(), e);
		}
	}

	private Trytest getTest(String testId) {
		Trytest test = testProvider.getTest(testId);
		if (test == null) {
			throw new NotFoundException("Cannot find test with id: " + testId);
		}
		return test;
	}

}
