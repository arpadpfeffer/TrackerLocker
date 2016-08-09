package com.epam.bench.dao.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.epam.bench.model.Locker;

@Component
public class TempDataBase {
	private static final int NUM_OF_LOCKERS = 20;
	private Map<Integer, Locker> lockers;
	
	public TempDataBase() {
		lockers = new HashMap<Integer, Locker>();
		for (int i = 0; i < NUM_OF_LOCKERS; i++) {
			lockers.put(i, new Locker.Builder().withId(i).withPass("").withUser(null).build());
		}
	}
	
	public Locker getLocker(int lockerId) {
		return lockers.get(lockerId);
	}
	
	public void putLocker(int lockerId, Locker locker) {
		lockers.put(lockerId, locker);
	}
	
	public Map<Integer, Locker> getAllLocker() {
		return lockers;
	}

}
