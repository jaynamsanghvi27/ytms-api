package com.yash.ytms.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrfWithNominationDto {
	private TrainingRequestFormDto  trainingRequestFormDto;
	private List<NominationDto> nominationList;
	
	
}
