package org.fmi.tryme.test;

import java.util.List;

import org.fmi.tryme.admin.beans.Trytest;
import org.fmi.tryme.test.beans.AnsweredQuestion;
import org.fmi.tryme.test.beans.TestScore;

public interface TestScorer {

	TestScore score(Trytest test, List<AnsweredQuestion> answers);
	
}
