package com.nexiilabs.stp.prospect;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ContactReportsRepository {

	List<ProspectListResposeDTO> getTodayContactList(int userId, int userTypeId);

	int getContactCount(int userId, int userTypeId);

	int getBetweenCount(String startDate, String endDate, int userId, int userTypeId);

	List<ProspectListResposeDTO> getBetweenDatesContactList(String startDate, String endDate, int userId,
			int userTypeId);

}
