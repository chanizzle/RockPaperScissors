
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

public class StrategyUtilTest {
	@Test
	public void testGetLastMovePlay() {
		StrategyUtil.isTest = true;
		Assert.assertEquals(Type.P, StrategyUtil.getLastMovePlay());
		
		StrategyUtil.lastMove = Type.P;
		Assert.assertEquals(Type.S, StrategyUtil.getLastMovePlay());
	}
	
	@Test
	public void testGetFavouriteMovePlay() {
		StrategyUtil.isTest = true;
		Assert.assertEquals(Type.P, StrategyUtil.getFavouriteMovePlay());
		
		final HashMap <Type, Integer> mapTest = new HashMap <Type, Integer>();
		mapTest.put(Type.P, 2);
		StrategyUtil.counterMap = mapTest;
		Assert.assertEquals(Type.S, StrategyUtil.getFavouriteMovePlay());
		
		mapTest.put(Type.R, 3);
		Assert.assertEquals(Type.P, StrategyUtil.getFavouriteMovePlay());
	}
	
	@Test
	public void testGetLeastUsed() {
		StrategyUtil.counterMap = new HashMap <Type, Integer>();
		StrategyUtil.isTest = true;
		Assert.assertEquals(Type.P, StrategyUtil.getLeastUsed());
		
		final HashMap<Type, Integer> mapTest = new HashMap <Type, Integer>();
		mapTest.put(Type.P, 1);
		mapTest.put(Type.R, 1);
		StrategyUtil.counterMap = mapTest;
		Assert.assertEquals(Type.R, StrategyUtil.getLeastUsed());
		
		mapTest.put(Type.P, 1);
		mapTest.put(Type.R, 3);
		mapTest.put(Type.S, 6);
		StrategyUtil.counterMap = mapTest;
		Assert.assertEquals(Type.S, StrategyUtil.getLeastUsed());
	}
}
