package com.epam.bench.dao.impTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.bench.LockerTrackerController;
import com.epam.bench.dao.imp.LockerDaoImpl;
import com.epam.bench.dao.imp.TempDataBase;
import com.epam.bench.model.Locker;
import com.epam.bench.model.User;
import com.epam.bench.model.User.Builder;

public class LockerDaoImplTest {
	private static final String USER_NAME = "Eva";
	private static final int LOCKER_ID = 1;
	private static final String PASSWORD = "qwer";

	@Mock//amit használ
	private TempDataBase tempDataBase;
	
	@InjectMocks//amit akarok tesztelni
	private LockerDaoImpl lockerDaoImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		lockerDaoImpl = new LockerDaoImpl(tempDataBase);
	}
	
	@Test
	public void shouldNotReserveAlreadyLockedLocker() {
		// GIVEN
		User testUser= new User.Builder().withName("test-user").build();
		given(tempDataBase.getLocker(LOCKER_ID)).willReturn(new Locker.Builder().withUser(testUser).build());
		// WHEN
		boolean actual = lockerDaoImpl.reserveLocker(testUser, LOCKER_ID, PASSWORD);
		// THEN
		assertFalse(actual);
	}
	
	@Test
	public void sholudReserveFreeLocker() {
		//GIVEN
		User testUser= new User.Builder().withName("test-user").build();
		given(tempDataBase.getLocker(LOCKER_ID)).willReturn(new Locker.Builder().withUser(null).build());
		//WHEN
		boolean actual = lockerDaoImpl.reserveLocker(testUser, LOCKER_ID, PASSWORD);
		//THEN
		assertTrue(actual);
	}
	
	@Test
	public void shouldNotFreeUpAlreadyLockedLocker() {
		// GIVEN
		User testUser= new User.Builder().withName("test-user").build();
		given(tempDataBase.getLocker(LOCKER_ID)).willReturn(new Locker.Builder().withUser(testUser).withPass(PASSWORD).build());
		// WHEN
		boolean actual = lockerDaoImpl.freeUpLocker(LOCKER_ID, PASSWORD);
		// THEN
		assertTrue(actual);
	}
	
	@Test
	public void shouldFreeUpAlreadyLockedLocker() {
		// GIVEN
		given(tempDataBase.getLocker(LOCKER_ID)).willReturn(new Locker.Builder().withUser(null).build());
		// WHEN
		boolean actual = lockerDaoImpl.freeUpLocker(LOCKER_ID, PASSWORD);
		// THEN
		assertFalse(actual);
	}
}
