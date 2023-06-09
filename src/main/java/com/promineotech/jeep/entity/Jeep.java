package com.promineotech.jeep.entity;

import java.math.BigDecimal;
import java.util.Comparator; //Cannot invoke "java.util.List.sort(java.util.Comparator)" because "list" is null

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jeep implements Comparable<Jeep> {
	private Long modelPK;
	private JeepModel modelId;
	private String trimLevel;
	private int numDoors;
	private int wheelSize;
	private BigDecimal basePrice;
	
	@JsonIgnore
	public Long getModelPk() {
		return modelPK;
	}

	@Override
	public int compareTo(Jeep that) {
		return Comparator
				.comparing(Jeep::getModelId)
				.thenComparing(Jeep::getTrimLevel)
				.thenComparing(Jeep::getNumDoors)
				.compare(this, that);
								
				
	}

}	
	

