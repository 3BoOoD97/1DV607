package view;

import java.util.List;

import model.Boat;
import model.Member;

public interface IView {
	public MainmenuOptions mainMenuOptions();

	public void displayCompactList(List<Member> members);

	public void displayVerboseList(List<Member> members);
	
	public int readMemberId();

	public Member displayAddMember();

	public int displayDeleteMember();
	
	public int displayEditMemberRead();

	public void displayEditMember(Member member);

	public Boat displayAddBoat();

	public int displayDeleteBoat();

	public int displayEditBoatRead();
	
	public void displayEditBoat(Boat boat);

	public void displayChosenMember(Member member);

	public void displayMissingUserError();

	public void displayIncorrectLengthError(int length);

	public void displayInvalidChoiceError();

	public void displayMissingBoatError(String name, int boatNumber);

	public void displayWelcome();

	public void displayEndMessage();

	public String getStringInput();

	public int getIntInput();

}
