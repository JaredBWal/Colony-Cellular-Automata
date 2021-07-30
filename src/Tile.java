/*
 * 
 * Author Jared Wal
 */

import java.awt.Color;

public class Tile {

	
	//Tile Variables

	final public int xCord, yCord;
	
	public boolean isWater, isEmpty=true;
	
	
	// Unit Variables
	
	public int unitAge=0, unitAgeLimit=0, unitStrength=0, unitBirthPercent=0, teamId=0;
	
	public String unitTeam="none";
	
	public Color unitColor;
	
	public boolean unitHaveDisease=false;
	
	
	//Constructor
	
	public Tile(int xCord ,int yCord, boolean isWater) {
		this.xCord = 0;
		this.yCord = yCord;
		this.isWater = isWater;
		
	}
	
	@Override
	public String toString() {
		if (this.isWater) {
			return "True";
		}else {
			return "False";
		}
	}
	
	
	//// setters
	
	public void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	public void setUnitAge(int unitAge) {
		this.unitAge = unitAge;
	}
	
	public void setUnitAgeLimit(int unitAgeLimit) {
		this.unitAgeLimit = unitAgeLimit;
	}
	
	public void setUnitStrength(int unitStrength) {
		this.unitStrength = unitStrength;
	}
	
	public void setUnitBirthPercent(int unitBirthPercent) {
		this.unitBirthPercent = unitBirthPercent;
	}
	
	public void setUnitTeam(String unitTeam) {
		this.unitTeam = unitTeam;
	}
	
	public void setUnitHaveDisease(boolean unitHaveDisease) {
		this.unitHaveDisease = unitHaveDisease;
	}
	
	public void setUnitColor(Color unitColor) {
		this.unitColor = unitColor;
	}
	
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	
	
	//// getters
	
	public boolean getIsEmpty() {
		return this.isEmpty;
	}
	public int getUnitAge() {
		return this.unitAge;
	}
	public int getUnitAgeLimit() {
		return this.unitAgeLimit;
	}
	public int getUnitStrength() {
		return this.unitStrength;
	}
	public int getUnitBirthPercent() {
		return this.unitBirthPercent;
	}
	public String getUnitTeam() {
		return this.unitTeam;
	}
	public boolean getUnitHaveDisease() {
		return this.unitHaveDisease;
	}
	public Color getUnitColor() {
		return this.unitColor;
	}
	public int getTeamId() {
		return this.teamId;
	}	
	public boolean getIsWater() {
		return this.isWater;
	}
	
}
