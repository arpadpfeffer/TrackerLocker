package com.epam.bench;

import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.bench.model.Locker;
import com.epam.bench.model.ReserveLockerQueryParams;
import com.epam.bench.model.UnlockQueryParams;
import com.epam.bench.service.LockerService;

@Controller
public class LockerTrackerController {
	private static final String ERROR_MESSAGE = "Not valid input.";

	private static final Logger logger = LoggerFactory.getLogger(LockerTrackerController.class);

	private final LockerService lockerService;

	@Autowired
	public LockerTrackerController(LockerService lockerService) {
		this.lockerService = lockerService;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/locker", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Map<Integer, Locker> lockers = lockerService.getLockers();

		model.addAttribute("lockers", lockers);

		return "home";
	}

	/*
	 * @RequestMapping(value = "/reserve", method = RequestMethod.POST) public
	 * String reserveLocker(@ModelAttribute ReserveLockerQueryParams
	 * queryParams, Model model) {
	 * lockerService.reservLocker(queryParams.getName(), queryParams.getId(),
	 * queryParams.getPasswd()); Map<Integer, Locker> lockers =
	 * lockerService.getLockers();
	 * 
	 * model.addAttribute("lockers", lockers);
	 * 
	 * return "home"; }
	 */
	@RequestMapping(value = "/reserve", method = RequestMethod.POST)
	public String reserveLocker(@ModelAttribute @Valid ReserveLockerQueryParams queryParams, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.info("result:{}",result);
			model.addAttribute("errorMessage", ERROR_MESSAGE);
		} else {

			// form input is ok
			logger.info("result:{}",result);
			lockerService.reservLocker(queryParams.getName(), queryParams.getId(), queryParams.getPasswd());
			Map<Integer, Locker> lockers = lockerService.getLockers();

			model.addAttribute("lockers", lockers);
		}
		return "home";
	}

	@RequestMapping(value = "/unlock", method = RequestMethod.POST)
	public String unLocker(@ModelAttribute UnlockQueryParams queryParams, Model model) {
		lockerService.freeUpLocker(queryParams.getId(), queryParams.getPasswd());
		Map<Integer, Locker> lockers = lockerService.getLockers();

		model.addAttribute("lockers", lockers);

		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return "home";
	}
}
