package com.epam.bench.dao;

import java.util.Map;

import com.epam.bench.model.Locker;
import com.epam.bench.model.User;

public interface LockerDao {
	boolean freeUpLocker(int lockerId, String pass);
	boolean reserveLocker(User user, int lockerId, String pass);
	Map<Integer, Locker> getLockers();
}
