package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.Boat;
import model.Member;
import model.Boat.BoatType;

public class EnglishV implements IView {
	private Scanner scan;

	public EnglishV() {
		this.scan = new Scanner(System.in);
	}

	@Override
	public MainmenuOptions mainMenuOptions() {
		System.out.println(" ------------ CHOOSE FROM BELOW ------------ ");
		System.out.println("1. Register a member");
		System.out.println("2. Show a compact list ");
		System.out.println("3. Show a verbose list");
		System.out.println("4. Delete a member");
		System.out.println("5. Edit a member");
		System.out.println("6. View a member");
		System.out.println("7. Register aboat");
		System.out.println("8. Delete a boat");
		System.out.println("9. Edit a boat");
		System.out.println("0. Quit");
		System.out.print("\nYour choice: ");

		switch (getIntInput()) {
		case (1):
			return MainmenuOptions.RegisterMember;
		case (2):
			return MainmenuOptions.showCompactList;
		case (3):
			return MainmenuOptions.ShowVerboseList;
		case (4):
			return MainmenuOptions.DeleteMember;
		case (5):
			return MainmenuOptions.EditMember;
		case (6):
			return MainmenuOptions.ViewMember;
		case (7):
			return MainmenuOptions.RegisterBoat;
		case (8):
			return MainmenuOptions.DeleteBoat;
		case (9):
			return MainmenuOptions.EditBoat;
		case (0):
			return MainmenuOptions.Quit;
		default:
			return mainMenuOptions();
		}
	}

	@Override
	public void displayCompactList(List<Member> list) {
		System.out.println(" Compact list of members:");
		System.out.println("____________________________");

		for (int i = 0; i < list.size(); ++i) {
			Member member = list.get(i);
			System.out.println("\nName: " + member.getName());
			System.out.println("MemberID: " + member.getMemberId());
			System.out.println("Number of boats: " + member.getAmountBoats());
		}
	}

	@Override
	public void displayVerboseList(List<Member> list) {
		System.out.println(" Verbose list of members:");
		System.out.println("____________________________");

		for (int i = 0; i < list.size(); ++i) {
			Member member = list.get(i);
			System.out.println("\nName: " + member.getName());
			System.out.println("Personal number: " + member.getPersonalNr());
			System.out.println("MemberID: " + member.getMemberId());

			System.out.println("\nOwned boats: ");
			if (member.getAmountBoats() == 0) {
				System.out.println("None");
			} else {
				for (Boat boat : member.getBoats()) {
					System.out.println("Length: " + boat.getLength());
					System.out.println("Type: " + boat.getType());
				}
			}
			System.out.println("____________________________");
		}
	}
	
	@Override
	public int readMemberId() {
		System.out.println(" Find the member: ");
		System.out.println("____________________________");
		int memberId = 0;
		boolean isNumber = false;
		while (!isNumber) {
			try {
				memberId = scan.nextInt();
				isNumber = true;
				break;
			} catch (Exception exp) {
				scan.nextLine();
				System.out.println("Invalid number, try one more");
			}
		}
		scan.nextLine();
		return memberId;
	}

	@Override
	public Member displayAddMember() {
		System.out.println(" Register a new member: ");
		System.out.println("____________________________");

		Member member = new Member();
		setName(member);
		setPersonalNumber(member);
		return member;
	}
	
	private void setName(Member member) {
		System.out.println("New Name: ");
		member.setName(getStringInput());
	}
	
	private void setPersonalNumber(Member member) {
		System.out.println("New Personal number: ");
		member.setPersonalNr(getStringInput());
	}

	@Override
	public int displayDeleteMember() {
		System.out.println(" Remove a member: ");
		System.out.println("____________________________");
		System.out.println(" Id: ");
		return readMemberId();
	}
	
	@Override
	public int displayEditMemberRead() {
		System.out.println(" Edit a member: ");
		System.out.println("____________________________");
		System.out.println(" Id: ");
		return readMemberId();
	}

	@Override
	public void displayEditMember(Member member) {
		System.out.println("\nName: ");
		setName(member);
		setPersonalNumber(member);
	}

	@Override
	public Boat displayAddBoat() {
		System.out.println(" Register a new boat: ");
		System.out.println("____________________________");
		Boat boat = new Boat();
		setLength(boat);
		setType(boat);
		return boat;
	}
	
	public void setLength(Boat boat) {
		System.out.println("\nLength of boat: ");
		int length = getIntInput();
		boat.setLength(length);
	}
	
	public void setType(Boat boat) {
		System.out.println("\nType of boat: ");
		String type = getStringInput();
		boat.setType(validateType(type));
	}

	@Override
	public int displayDeleteBoat() {
		System.out.println(" Remove a boat: ");
		System.out.println("____________________________");
		return getIntInput();
	}

	@Override
	public int displayEditBoatRead() {
		System.out.println(" Enter boat id: ");
		System.out.println("____________________________");
		return getIntInput();
	}
	
	@Override
	public void displayEditBoat(Boat boat) {
		System.out.println(" Edit a boat: ");
		System.out.println("____________________________");
		setLength(boat);
		setType(boat);
	}

	@Override
	public void displayChosenMember(Member member) {
		System.out.println("\nName: " + member.getName());
		System.out.println("Personal number: " + member.getPersonalNr());
		System.out.println("Member ID: " + member.getMemberId());
		System.out.println("\nBoats:");

		if (member.getAmountBoats() == 0) {
			System.out.println("None");
		} else {
			int i = 0;
			for (Boat boat : member.getBoats()) {
				System.out.println(i + ". Type: " + boat.getType() + "   Length: " + boat.getLength());
				i++;
			}
		}
	}

	@Override
	public void displayMissingUserError() {
		System.out.println("There is no member in this id");

	}

	@Override
	public void displayIncorrectLengthError(int length) {
		System.out.println("Incorrect length of the boat: " + length);

	}

	@Override
	public void displayInvalidChoiceError() {
		System.out.println("Invalid choice");

	}

	@Override
	public void displayMissingBoatError(String name, int boatNumber) {
		System.out.println("The member " + name + " has no boat with he number " + boatNumber);
	}

	@Override
	public void displayWelcome() {
		System.out.println("--------- Welcome TO BOAT CLUB --------- ");
	}

	@Override
	public void displayEndMessage() {
		System.out.println(" Exiting the program...");
	}

	@Override
	public String getStringInput() {
		String input = scan.nextLine();
		return input;
	}

	@Override
	public int getIntInput() {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int input = -1;
		try {
			input = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		}

		return input;
	}

	public BoatType validateType(String type) {
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
