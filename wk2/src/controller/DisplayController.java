package controller;

import model.Boat;
import model.Member;
import model.Registry;
import view.IView;
import view.MainmenuOptions;

public class DisplayController {
	private IView view;
	private Registry registry;

	public DisplayController(IView view, Registry registry) {
		this.view = view;
		this.registry = registry;
	}

	// Menu options
	public void startMenu() {
		while (true) {
			MainmenuOptions option = view.mainMenuOptions();
			switch (option) {
			case showCompactList:
				showCompact();
				break;
			case ShowVerboseList:
				showVerbose();
				break;
			case RegisterMember:
				addMember();
				break;
			case DeleteMember:
				DeleteMember();
				break;
			case EditMember:
				EditMember();
				break;
			case ViewMember:
				ViewMember();
				break;
			case RegisterBoat:
				RegisterBoat();
				break;
			case EditBoat:
				EditBoat();
				break;
			case DeleteBoat:
				DeleteBoat();
				break;
			case Quit:
				Quit();
				break;
			default:
				break;
			}
		}

	}

	private void showCompact() {
		view.displayCompactList(registry.getMembers());
	}

	private void showVerbose() {
		view.displayVerboseList(registry.getMembers());
	}

	private void addMember() {
		registry.addNewMember(view.displayAddMember());
	}

	private void DeleteMember() {
		int memberId = view.displayDeleteMember();
		try {
			Member member = registry.getMember(memberId);
			registry.deleteMember(member);
		} catch (Exception exp) {
			view.displayMissingUserError();
		}
	}

	private void EditMember() {
		int memberId = view.displayEditMemberRead();
		try {
			Member member = registry.getMember(memberId);
			view.displayEditMember(member);
			registry.editMember(member);
		} catch (Exception exp) {
			view.displayMissingUserError();
		}
	}

	private void EditBoat() {
		try {
			Member member = registry.getMember(view.readMemberId());
			int boatNumber = view.displayEditMemberRead();
			Boat boat = member.findBoatById(boatNumber);
			view.displayEditBoat(boat);
			registry.editBoat();
		} catch (Exception exp) {
			view.displayMissingUserError();
		}
	}

	private void ViewMember() {
		try {
			Member member = registry.getMember(view.readMemberId());
			view.displayChosenMember(member);
		} catch (Exception exp) {
			view.displayMissingUserError();
		}
	}

	private void RegisterBoat() {
		Boat boat = view.displayAddBoat();
		int memberId = view.displayDeleteMember();
		try {
			Member member = registry.getMember(memberId);
			registry.addNewBoat(member, boat);
		} catch (Exception exp) {
			view.displayMissingUserError();
		}
	}

	private void DeleteBoat() {
		Member member;
		int boatNumber;
		try {
			member = registry.getMember(view.readMemberId());
			boatNumber = view.displayDeleteBoat();
		} catch (Exception exp) {
			view.displayMissingUserError();
			return;
		}
		try {
			registry.deleteBoat(member, boatNumber);
		} catch (Exception exp) {
			view.displayMissingBoatError(member.getName(), boatNumber);
			return;
		}
	}

	private void Quit() {
		view.displayEndMessage();
	}
}
