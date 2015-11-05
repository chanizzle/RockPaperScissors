import java.util.Scanner;
import java.util.regex.*;

public class RockPaperScissors {
	private static int[] result = new int[3];
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please select strategy type (\"LAST_MOVE\", \"FAVOURITE\", \"LEAST_USED\")");
		
		String strategy = input.nextLine().toUpperCase();
		Strategy stratType = getStrategy(strategy);
		
		System.out.println("You are playing against strategy \'" + strategy + "\'.");
		
		while (true) {
			Type computerChoice = null;
			
			if (stratType == Strategy.LAST_MOVE) {
				computerChoice = StrategyUtil.getLastMovePlay();
			}
			else if (stratType == Strategy.FAVOURITE) {
				computerChoice = StrategyUtil.getFavouriteMovePlay();
			}
			else if (stratType == Strategy.LEAST_USED) {
				computerChoice = StrategyUtil.getLeastUsed();
			}
			
			System.out.println("Please enter \"R\"-Rock, \"P\"-Paper, \"S\"-Scissors, \"q\"-Quit");
			Type playerChoice = getPlayerChoice(stratType);
			System.out.println(getResult(computerChoice, playerChoice));
		}
	}
	
	/*
	 * get the subsequent strategy, if user enters an input that doesn't exist
	 * game will exit out
	 */
	private static Strategy getStrategy(String strategy) {
		Strategy strat = null;
		try {
			strat = Strategy.valueOf(strategy);
		}
		catch (IllegalArgumentException e) {
			System.out.println("You must enter one of the strategies listed. The game will exit out now.");
			System.exit(1);
		}
		return strat;
	}
	
	/*
	 * get what the player's move is
	 */
	private static Type getPlayerChoice(Strategy strategy) {
		//instantiate keyboard
		Scanner keyboard = new Scanner(System.in);
		Pattern p = Pattern.compile("R|r|P|p|S|s|Q|q");
		String input;
		
		//check if the input is valid
		if (!keyboard.hasNext(p)) {
			System.out.println("You must enter one of the choices listed. The game will exit out now.");
			System.exit(1);
		}
		
		//store value entered and convert to uppercase;
		input = keyboard.next().toUpperCase();
		
		//if q, quit the game
		if (input.equals("Q")) {
			System.out.println("Thanks for playing");
			System.exit(1);
		}
		
		final Type playerMoveType = Type.valueOf(input);
		
		if (strategy == Strategy.LAST_MOVE) {
			StrategyUtil.lastMove = playerMoveType;
		}
		else if (strategy == Strategy.FAVOURITE || strategy == Strategy.LEAST_USED) {
			if (StrategyUtil.counterMap.containsKey(playerMoveType)) {
				StrategyUtil.counterMap.put(playerMoveType, StrategyUtil.counterMap.get(playerMoveType) + 1);
			}
			else {
				StrategyUtil.counterMap.put(playerMoveType, 1);
			}
		}
		
		//convert player's choice to the enum Type
		return playerMoveType;
	}
	
	/*
	 * function keeps track of the result in an array
	 * formulates the string that will output the result
	 */
	private static String getResult (Type computerMove, Type playerMove) {
		final StringBuilder sb = new StringBuilder();
		String beatComputerMove = computerMove.getBeatMove();
		String beatPlayerMove = playerMove.getBeatMove();
		
		sb.append("I chose " + computerMove.getMoveType() + ". ");
		
		if (computerMove == playerMove) {
			result[2] = result[2] + 1;
			sb.append("We tied!\n");
		}
		else if (playerMove.toString().equals(beatComputerMove)) {
			result[1] = result[1] + 1;
			sb.append("You win!\n");
		}
		else if (computerMove.toString().equals(beatPlayerMove)) {
			result[0] = result[0] + 1;
			sb.append("I win!\n");
		}
		
		sb.append("you won " + result[1] + " times.\n");
		sb.append("I won " + result[0] + " times.\n");
		sb.append("we tied " + result[2] + " times.\n");
		return sb.toString();
	}
}
