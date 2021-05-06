package model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Member {
	private String name;
	private String personalNr;
	private int memberId;
	private List<Boat> boats = new ArrayList<Boat>();

	public Member() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonalNr() {
		return personalNr;
	}

	public void setPersonalNr(String personalNr) {
		this.personalNr = personalNr;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getAmountBoats() {
		return boats.size();
	}

	public List<Boat> getBoats() {
		return boats;
	}

	public void addBoat(Boat boat) {
		boats.add(boat);
	}

	public void removeBoat(int boatNumber) {
		if (boatNumber < boats.size()) {
			boats.remove(boatNumber);
		}
		throw new NoSuchElementException();
	}
	
	public Boat findBoatById(int boatNumber) {
		return boats.get(boatNumber);
	}
}
