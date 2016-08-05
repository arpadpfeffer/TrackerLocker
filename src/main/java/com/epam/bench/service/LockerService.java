package com.epam.bench.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.bench.dao.LockerDao;
import com.epam.bench.model.Locker;
import com.epam.bench.model.User;
import com.epam.bench.utils.StringUtil;

@Service
public class LockerService {

	private final LockerDao lockerDaoImpl;

	@Autowired
	public LockerService(LockerDao lockerDaoImpl) {
		this.lockerDaoImpl = lockerDaoImpl;
	}

	public void reservLocker(String userName, int lockerId, String pass) {
		if (!StringUtil.isEmpty(userName) && !StringUtil.isEmpty(pass) && lockerId > -1 && lockerId < 20 && !isUserHasLocker(userName)) {
			lockerDaoImpl.reserveLocker(new User.Builder().withName(userName).build(), lockerId, pass);
		}
		
	}

	public void freeUpLocker(int lockerId, String pass) {
		if (lockerId > -1 && lockerId < 20 && !StringUtil.isEmpty(pass)) {
			lockerDaoImpl.freeUpLocker(lockerId, pass);
		}
	}

	public Map<Integer, Locker> getLockers() {
		return lockerDaoImpl.getLockers();
	}
	
	private boolean isUserHasLocker(String userName) {
		Map<Integer, Locker> lockers = lockerDaoImpl.getLockers();
		for (Locker locker : lockers.values()) {
			if(locker.getUser() != null && locker.getUser().getName().equals(userName)) {
				return true;
			}
		}
		return false;
		//return lockers.values().stream().filter(locker -> userName.equals(locker.getUser().getName())).findFirst().isPresent();
	}
}
