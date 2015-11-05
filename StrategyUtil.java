import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class StrategyUtil {
	public static HashMap <Type, Integer> counterMap = new HashMap <Type, Integer>();
	
	public static Type lastMove = null;
	
	public static boolean isTest = false;
	
	/*
	 * Function keeps track of last move that player played
	 * And depending on what the last move is, the computer will select the move
	 * that will beat the last move
	 */
	public static Type getLastMovePlay() {
		if (lastMove == null) {
			if (!isTest) {
				return getRandomMove();
			}
			else {
				return Type.P;
			}
		}
		else {
			return Type.valueOf(lastMove.getBeatMove());
		}
	}
	
	/*
	 * Function keeps track of frequently used move player played
	 * Depending on what the most played move, the computer will select the move
	 * that will beat it
	 */
	public static Type getFavouriteMovePlay() {
		if (counterMap.isEmpty()) {
			if (!isTest) {
				return getRandomMove();
			}
			else {
				return Type.P;
			}
		}
		
		int max = 0;
		Type favouriteMove = getRandomMove();
		
		for (Map.Entry<Type, Integer> entry : counterMap.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				favouriteMove = entry.getKey();
			}
		}
		
		return Type.valueOf(favouriteMove.getBeatMove());
	}
	
	/*
	 * Function keeps track of least used moves player played
	 * Depending on what the least used move, the computer will select the move
	 * that will beat it
	 */
	public static Type getLeastUsed() {
		if (counterMap.isEmpty()) {
			if (!isTest) {
				return getRandomMove();
			}
			else {
				return Type.P;
			}
		}
		
		int min = Integer.MAX_VALUE;
		Type leastUsed = getRandomMove();
		
		// Only go through the counterMap
		if (counterMap.size() == 3) {
			for (Map.Entry<Type, Integer> entry : counterMap.entrySet()) {
				if (entry.getValue() < min) {
					min = entry.getValue();
					leastUsed = entry.getKey();
				}
			}
		}
		else {
			if (!counterMap.containsKey(Type.P)) {
				leastUsed = Type.P;
			}
			else if (!counterMap.containsKey(Type.R)) {
				leastUsed = Type.R;
			}
			else {
				leastUsed = Type.S;
			}
		}
		
		return Type.valueOf(leastUsed.getBeatMove());
	}
	
	/*
	 * In case of the first move, where you don't know the 
	 * other player's data, computer chooses a random move
	 */
	private static Type getRandomMove() {
		Random rand = new Random();
		int randomNum = rand.nextInt((3-1) + 1) + 1;
		
		if (randomNum == 1) {
			return Type.R;
		}
		else if (randomNum == 2) {
			return Type.P;
		}
		else {
			return Type.S;
		}
	}
}
