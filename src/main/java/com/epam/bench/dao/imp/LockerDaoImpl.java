package com.epam.bench.dao.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.epam.bench.dao.LockerDao;
import com.epam.bench.model.Locker;
import com.epam.bench.model.User;

@Repository
public class LockerDaoImpl implements LockerDao {
	private static final int NUM_OF_LOCKERS = 20;

	private Map<Integer, Locker> lockers;

	public LockerDaoImpl() {
		lockers = new HashMap<Integer, Locker>();
		for (int i = 0; i < NUM_OF_LOCKERS; i++) {
			lockers.put(i, new Locker.Builder().withId(i).withPass("").withUser(null).build());
		}
	}

	@Override
	public void reserveLocker(User user, int lockerId, String pass) {
		Locker locker = lockers.get(lockerId);
		if (locker.getUser() == null) {
			Locker reservLocker = new Locker.Builder().withId(lockerId).withPass(pass).withUser(user).build();
			lockers.put(lockerId, reservLocker);
		}
	}

	@Override
	public void freeUpLocker(int lockerId, String pass) {
		Locker locker = lockers.get(lockerId);
		if (locker.getUser() != null && locker.getPass().equals(pass)) {
			Locker freeLocker = new Locker.Builder().withId(lockerId).withPass("").withUser(null).build();
			lockers.put(lockerId, freeLocker);
		}
	}

	@Override
	public Map<Integer, Locker> getLockers() {
		return lockers;
	}

}
