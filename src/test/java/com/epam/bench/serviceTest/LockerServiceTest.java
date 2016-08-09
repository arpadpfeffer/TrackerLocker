package com.epam.bench.serviceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.bench.dao.imp.LockerDaoImpl;
import com.epam.bench.dao.imp.TempDataBase;
import com.epam.bench.model.Locker;
import com.epam.bench.model.User;
import com.epam.bench.service.LockerService;

public class LockerServiceTest {
	
	private static final String USER_NAME = "User name";
	private static final int LOCKER_ID = 3;
	private static final String PASS = "Pass";

	@Mock
	private LockerDaoImpl lockerDaoImpl;
	@Mock
	private TempDataBase tempDataBase;
	
	@InjectMocks
	private LockerService lockerService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		lockerService = new LockerService(lockerDaoImpl);
	}
	
	@Test
	public void shouldReserveLockerIfUserDoesNotHaveAny() {
		//GIVEN
		given(lockerDaoImpl.getLockers()).willReturn(new HashMap<>());
		//WHEN
		boolean actual = lockerService.reserveLocker(USER_NAME, LOCKER_ID, PASS);
		//THEN
		assertTrue(actual);
	}
	
	@Test
	public void shouldNotReserveLockerIfUserDoesHaveAny() {
		//GIVEN
		Map<Integer, Locker> lockers = new HashMap<>();
		lockers.put(LOCKER_ID, new Locker.Builder().withId(LOCKER_ID).withUser(new User.Builder().withName(USER_NAME).build()).build());
		given(lockerDaoImpl.getLockers()).willReturn(lockers);
		//WHEN
		boolean actual = lockerService.reserveLocker(USER_NAME, LOCKER_ID, PASS);
		//THEN
		assertFalse(actual);
	}
	
	@Test
	public void shouldNotFreeUpLockerIfPasswordNull() {
		//GIVEN
		String pass = null;
		//WHEN
		boolean actual = lockerService.freeUpLocker(LOCKER_ID, pass);
		//THEN
		assertFalse(actual);
	}
	
	@Test
	public void shouldFreeUpLockerIfUserDoesHaveAny() {
		//GIVEN
		User testUser= new User.Builder().withName("test-user").build();
		given(tempDataBase.getLocker(LOCKER_ID)).willReturn(new Locker.Builder().withUser(testUser).withId(LOCKER_ID).withPass(PASS).build());
		//WHEN
		boolean actual = lockerService.freeUpLocker(LOCKER_ID, PASS);
		//THEN
		assertTrue(actual);
	}
	
	@Test
	public void shouldNotFreeUpLockerIfUserDoesNotHaveAny() {
		//GIVEN
		given(tempDataBase.getLocker(LOCKER_ID)).willReturn(new Locker.Builder().withUser(null).build());
		//WHEN
		boolean actual = lockerService.freeUpLocker(LOCKER_ID, PASS);
		//THEN
		assertTrue(actual);
	}
}
