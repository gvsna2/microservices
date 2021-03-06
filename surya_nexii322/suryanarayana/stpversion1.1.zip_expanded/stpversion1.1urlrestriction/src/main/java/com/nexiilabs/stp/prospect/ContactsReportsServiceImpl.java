package com.nexiilabs.stp.prospect;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactsReportsServiceImpl implements ContactReportsService {
	@Autowired
	ContactReportsRepository contactReportsRepository;

	@Override
	public List<ProspectListResposeDTO> getTodayContactList(int userId, int userTypeId) {
		return contactReportsRepository.getTodayContactList(userId, userTypeId);
	}

	@Override
	public ContactCountResponseDTO getContactCount(int userId, int userTypeId) {
		ContactCountResponseDTO contactCountResponseDTO = new ContactCountResponseDTO();
		LocalDate today = LocalDate.now();
		LocalDate monday = today;
		while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
			monday = monday.minusDays(1);
		}
		LocalDate sunday = today;
		while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
			sunday = sunday.plusDays(1);
		}
		int todayCount = contactReportsRepository.getContactCount(userId, userTypeId);
		int weekCount = contactReportsRepository.getBetweenCount(monday.toString(), sunday.toString(), userId,
				userTypeId);
		int monthCount = contactReportsRepository.getBetweenCount(today.withDayOfMonth(1).toString(), today.toString(),
				userId, userTypeId);
		int dateFilterCount = contactReportsRepository.getBetweenCount(
				today.minusMonths(1).withDayOfMonth(1).toString(), today.toString(), userId, userTypeId);
		contactCountResponseDTO.setWeeklyCount(weekCount);
		contactCountResponseDTO.setMonthlyCount(monthCount);
		contactCountResponseDTO.setTodayCount(todayCount);
		contactCountResponseDTO.setDateFilterCount(dateFilterCount);
		contactCountResponseDTO.setStartDate(today.minusMonths(1).withDayOfMonth(1).toString());
		contactCountResponseDTO.setEndDate(today.toString());
		return contactCountResponseDTO;
	}

	@Override
	public int getFilterdDateContactCount(String startDate, String endDate, int userId, int userTypeId) {
		return contactReportsRepository.getBetweenCount(startDate, endDate, userId, userTypeId);
	}

	@Override
	public List<ProspectListResposeDTO> getWeeklyContactList(int userId, int userTypeId) {
		LocalDate today = LocalDate.now();
		LocalDate monday = today;
		while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
			monday = monday.minusDays(1);
		}
		LocalDate sunday = today;
		while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
			sunday = sunday.plusDays(1);
		}
		return contactReportsRepository.getBetweenDatesContactList(monday.toString(), sunday.toString(), userId,
				userTypeId);
	}

	@Override
	public List<ProspectListResposeDTO> getMonthlyContactList(int userId, int userTypeId) {
		LocalDate today = LocalDate.now();
		return contactReportsRepository.getBetweenDatesContactList(today.withDayOfMonth(1).toString(), today.toString(),
				userId, userTypeId);
	}

	@Override
	public List<ProspectListResposeDTO> getFilteredDateContactList(int userId, String startDate, String endDate,
			int userTypeId) {
		return contactReportsRepository.getBetweenDatesContactList(startDate, endDate, userId, userTypeId);
	}

}
