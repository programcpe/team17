package com.sut.se61.g17;

import com.sut.se61.g17.entity.CarData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@DataJpaTest

public class DemoApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;
	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}


	@Test
	public void testModelCannotBeNull() {
		CarData carData = new CarData();
		carData.setModel(null);
		carData.setcC("12345cc");
		try {
			entityManager.persist(carData);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}
	@Test
	public void testCcCannotBeNull() {
		CarData carData = new CarData();
		carData.setModel("e600");
		carData.setcC(null);

		try {
			entityManager.persist(carData);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testModelSizeCannotLowerThanMin() {
		CarData carData = new CarData();
		carData.setModel("uu");
		carData.setcC("123456cc");

		try {
			entityManager.persist(carData);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testModelSizeCannotHigherThanMax() {
		CarData carData = new CarData();
		carData.setModel("AAAAAAAAAAAAAAAAAAAAAAAAAA");
		carData.setModel("12345690cc");
		try {
			entityManager.persist(carData);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testCcSizeCannotLowerThanMin() {
		CarData carData = new CarData();
		carData.setModel("uuuuu");
		carData.setcC("1cc");

		try {
			entityManager.persist(carData);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testCcSizeCannotHigherThanMax() {
		CarData carData = new CarData();
		carData.setModel("AAAAAAAAAAAAAA");
		carData.setcC("1234567890145cc");
		try {
			entityManager.persist(carData);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	@Test
	public void testCcCannotMatchPattern() {
		CarData carData = new CarData();
		carData.setModel("e600");
		carData.setcC("011111ss");

		try {
			entityManager.persist(carData);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

}

