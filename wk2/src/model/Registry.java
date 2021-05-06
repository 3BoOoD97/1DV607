package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import model.Boat.BoatType;


public class Registry {
	private List<Member> members = new ArrayList<Member>();

	public Registry() {
		getSavedMembers();
	}

	// Adds a new Member
	public void addNewMember(Member member) {
		if (members.size() != 0) {
			member.setMemberId(members.get(members.size() - 1).getMemberId() + 1);
		} else
			member.setMemberId(1); // If there are no Members yet then set ID 1 to the first Member

		members.add(member);

		saveMembersToFile();
	}

	// Deletes a member
	public void deleteMember(Member member) {
		members.remove(member);
		saveMembersToFile();
	}

	// Edits a member
	public void editMember(Member member) {
		saveMembersToFile();
	}

	// Returns a member based on a memberID
	public Member getMember(int memberId) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getMemberId() == memberId) {
				return members.get(i);
			}
		}
		throw new NoSuchElementException();
	}

	public List<Member> getMembers() {
		return members;
	}

	// Returns a name based on a memberID
	public String getNameFromMemberId(int memberId) {
		String name = "";

		for (Member member : members) {
			if (member.getMemberId() == memberId) {
				name = member.getName();
				return name;
			}
		}
		return name;
	}

	// Adds a new boat to a member
	public void addNewBoat(Member member, Boat boat) {
		member.addBoat(boat);
		saveMembersToFile();
	}

	// Deletes a boat
	public void deleteBoat(Member member, int boatNumber) {
		member.removeBoat(boatNumber);
		saveMembersToFile();
	}

	// Edits a boat
	public void editBoat() {
		saveMembersToFile();
	}

	// Saves all the current member information to a text file
	private void saveMembersToFile() {
		PrintWriter out = null;
		String filePath = "members.txt";

		try {
			out = new PrintWriter(filePath);

			for (Member member : this.members) {
				out.println(member.getName());
				out.println(member.getPersonalNr());
				out.println(member.getMemberId());
				out.println(member.getAmountBoats());
				for (Boat boat : member.getBoats()) {
					out.println(boat.getType());
					out.println(boat.getLength());
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File \"" + filePath + "\" not found");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	// Gets all member information from text file and saves it to the members
	// ArrayList
	private void getSavedMembers() {
		BufferedReader reader = null;
		String filePath = "members.txt";

		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();

			int counter = 0;
			String name = "";
			String personalNr = "";
			int memberId = 0;
			int amountBoats = 0;

			// Checks each line in the text file for certain information about each member
			// and then adds the member to the members ArrayList
			while (line != null) {
				if (counter == 0) {
					name = line;
					counter++;
				} else if (counter == 1) {
					personalNr = line;
					counter++;
				} else if (counter == 2) {
					memberId = Integer.parseInt(line);
					Member member = new Member();
					member.setName(name);
					member.setPersonalNr(personalNr);
					member.setMemberId(memberId);
					members.add(member);
					counter++;
				} else if (counter == 3) {
					// Adds all boats owned by the current member
					amountBoats = Integer.parseInt(line);
					for (int i = 0; i < amountBoats; i++) {
						line = reader.readLine();
						String boatType = line;
						line = reader.readLine();
						int boatLength = Integer.parseInt(line);
						Boat boat = new Boat();
						boat.setLength(boatLength);
						boat.setType(validateType(boatType));
						members.get(members.size() - 1).addBoat(boat);
					}
					counter = 0;
				}
				line = reader.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println("File \"" + filePath + "\" not found");
		} catch (IOException e) {
			System.out.println("I/O error");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private BoatType validateType(String type) {
		if (type.toLowerCase().equals(BoatType.Canoe.toString().toLowerCase())) {
			return BoatType.Canoe;
		} else if (type.toLowerCase().equals(BoatType.Motorsailer.toString().toLowerCase())) {
			return BoatType.Motorsailer;
		} else if (type.toLowerCase().equals(BoatType.Sailboat.toString().toLowerCase())) {
			return BoatType.Sailboat;
		} else {
			return BoatType.Other;
		}
	}
}
