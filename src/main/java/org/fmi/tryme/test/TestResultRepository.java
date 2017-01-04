package org.fmi.tryme.test;

import org.fmi.tryme.test.beans.TestResult;
import org.springframework.data.repository.CrudRepository;

public interface TestResultRepository extends CrudRepository<TestResult, String> {

}
