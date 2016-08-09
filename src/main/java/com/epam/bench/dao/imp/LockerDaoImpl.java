package com.epam.bench.dao.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.bench.dao.LockerDao;
import com.epam.bench.model.Locker;
import com.epam.bench.model.User;

/**
 * The LockerDaoImpl class implements the lockers reservation, release and listing.
 * @author Arpad_Pfeffer
 * @version 1.0
 */

@Repository
public class LockerDaoImpl implements LockerDao {
	
	private final TempDataBase tempDataBase;
	
	/**
	 * The constructor initialize lockers to null.
	 */
	@Autowired
	public LockerDaoImpl(TempDataBase tempDataBase) {
		this.tempDataBase = tempDataBase;
	}
	
	/**
	 * This method is reserve a locker. 
	 * 
	 * @param user This user is who reserve for the locker.
	 * @param lockerId This number is the locker's number what want to reserve.
	 * @param pass This is the password to authenticate the user. 
	 */
	@Override
	public boolean reserveLocker(User user, int lockerId, String pass) {
		Locker locker = tempDataBase.getLocker(lockerId);
		if (locker.getUser() == null) {
			Locker reserveLocker = new Locker.Builder().withId(lockerId).withPass(pass).withUser(user).build();
			tempDataBase.putLocker(lockerId, reserveLocker);
			return true;
		}
		return false;
	}

	/**
	 * This method is free up a locker. 
	 * 
	 * @param lockerId This number is the locker's number what want to free up.
	 * @param pass This is the password to authenticate the user. 
	 */
	@Override
	public boolean freeUpLocker(int lockerId, String pass) {
		Locker locker = tempDataBase.getLocker(lockerId);
		if (locker.getUser() != null && locker.getPass().equals(pass)) {
			Locker freeLocker = new Locker.Builder().withId(lockerId).withPass("").withUser(null).build();
			tempDataBase.putLocker(lockerId, freeLocker);
			return true;
		}
		return false;
	}

	/**
	 * This method returns a map of lockers.
	 * @return Map List of lockers.
	 */
	@Override
	public Map<Integer, Locker> getLockers() {
		return tempDataBase.getAllLocker();
	}

}
