import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

class BullsAndCows {

	int seed = 9;
	//Build the 4 digit random number
	private int genRandomNum() {
		Random rand = new Random();

		System.out.println("I'm generating a secret 4 digit number with no duplicate digits.");
		System.out.println("See if you can guess it.");
		//First digit
		String digit1 = Integer.toString(rand.nextInt(seed) + 1);
		
		//Second digit
		String digit2;
		do {
			digit2 = Integer.toString(rand.nextInt(seed) + 1);
		} while (digit1.equals(digit2));
		
		//Third digit
		String digit3;
		do {
			digit3 = Integer.toString(rand.nextInt(seed) + 1);
		} while (digit1.equals(digit3) || digit2.equals(digit3));
		
		//Fourth digit
		String digit4;
		do {
			digit4 = Integer.toString(rand.nextInt(seed) + 1);
		} while (digit1.equals(digit4) || digit2.equals(digit4) || digit3.equals(digit4));

		int r = Integer.parseInt(digit1 + digit2 + digit3 + digit4);
		return r;
	}
	
	private boolean checkGuess (int guess, int answer) {
		if (guess == answer) return true;
		else return false;
			
	}

	private boolean anyBulls(int guess, int answer) {
		String sGuess  = Integer.toString(guess);
		String sAnswer = Integer.toString(answer);
		int bulls = 0;
		String sBulls = "The bulls are: ";
		for (int i=0; i < 4; i++) {
			if (sGuess.charAt(i) == sAnswer.charAt(i)) {
				bulls++;
				sBulls += sGuess.charAt(i) + ", ";
				
			}
		}
		System.out.println(bulls + " bull(s). " + sBulls);
		return (bulls > 0);
	}

	private boolean anyCows(int guess, int answer) {

		String sGuess  = Integer.toString(guess);
		String sAnswer = Integer.toString(answer);
		int cows = 0;
		String sCows = "The cows are: ";
		for (int i=0; i < 4; i++) {
			String s = Character.toString(sGuess.charAt(i));
			if (sAnswer.contains(s) && sGuess.charAt(i) != sAnswer.charAt(i)) {
				cows++;	
				sCows += sGuess.charAt(i) + ", ";
			}
		}
		System.out.println(cows + " cows(s). " + sCows);
		return (cows > 0);
	}
	private int userGuess() {
		boolean badInput = true;
		int iGuess = 0;
		do {
			try {
				Scanner UserInput = new Scanner(System.in) ;
				System.out.print("Your guess? ");
				iGuess = UserInput.nextInt();
				if (isDigitZero(iGuess)) 
					throw new RuntimeException("<Error>: 0 is not a valid digit!!!");
				badInput = false;
			} catch (InputMismatchException e) {
				System.out.println("<Error>: Not a valid number!!!");
			} catch (RuntimeException e) {
				System.out.println(e);
			}
			
		} while (badInput);
		return iGuess;
	}

	private boolean isDigitZero(int guess) {
		String sGuess  = Integer.toString(guess);
		for (int i=0; i < sGuess.length(); i++) {
			if (sGuess.charAt(i) == '0') return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		BullsAndCows bc = new BullsAndCows();
		int answer = (bc.genRandomNum());
		//System.out.println("Answer is: " + answer);
		int guess;
		boolean b;
		do {
			guess = bc.userGuess();
			b = bc.anyCows(guess,answer);	
			b = bc.anyBulls(guess,answer);	
		} while (guess != answer);
		System.out.println(guess + " is the correct answer.");

	}
}
