package main;

import java.io.File;
import java.io.IOException;

import controller.DisplayController;
import model.Registry;
import view.IView;
import view.EnglishV;

public class ProgramMain {

	public static void main(String args[]) throws IOException {
		File file = new File("members.txt");
		file.createNewFile();

		Registry registry = new Registry();
		IView view = new EnglishV();

		DisplayController displayController = new DisplayController(view, registry);
		displayController.startMenu();
	}
}
