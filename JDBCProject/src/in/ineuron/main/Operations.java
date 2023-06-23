package in.ineuron.main;

import java.util.Scanner;

public class Operations {
	
	Scanner scanner = null;
	
	public void chooseOperation() {
		
		scanner = new Scanner(System.in);
		
		System.out.println("STUDENT INFORMATION CRUD APP");
		System.out.println("============================");
		System.out.println("Press 1 for Insert operation");
		System.out.println("Press 2 for Select operation");
		System.out.println("Press 3 for Update operation");
		System.out.println("Press 4 for Delete operation");
		System.out.println("Press 5 for Exit");
		System.out.println("============================");
		
		int operation = scanner.nextInt();
		
		if(operation == 1) {
			Insert insert = new Insert();
			insert.insertOp();
		}else if(operation == 2) {
			Select select = new Select();
			select.SelectOp();
		}else if(operation == 3) {
			Update update = new Update();
			update.updateOp();
		}else if(operation == 4) {
			Delete delete = new Delete();
			delete.deleteOp();
		}else if(operation == 5) {
			System.out.println("App is closed...");
			System.exit(0);
		}else {
			System.out.println("Invalid input!!! Try Again");
			chooseOperation();
		}
		continueApp();
		
		
	}
	
	public void continueApp() {
		

		System.out.println("Do yo want to continue(Y[Yes]/N[No])");
		String input = scanner.next();
		if(input.equalsIgnoreCase("Y"))
			chooseOperation();
		else if(input.equalsIgnoreCase("N")) {
				if(scanner != null)
					scanner.close();
			System.out.println("App is closed...");	
			System.exit(0);
		}else {
			System.out.println("Invalid input! try again");
			continueApp();
		}

	}
}
