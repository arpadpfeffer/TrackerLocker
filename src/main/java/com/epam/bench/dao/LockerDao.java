package com.epam.bench.dao;

import java.util.Map;

import com.epam.bench.model.Locker;
import com.epam.bench.model.User;

public interface LockerDao {
	void freeUpLocker(int lockerId, String pass);
	void reserveLocker(User user, int lockerId, String pass);
	Map<Integer, Locker> getLockers();
}
