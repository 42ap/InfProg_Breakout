package model;

import java.util.ArrayList;

import model.object.Brick;

public class LevelGenerator {
	
	public static int MAXLEVEL = 1;
	
	
	public static ArrayList<Brick> getLevel(int index) {
		switch (index) {
		case 0:
			return level0();
		case 1:
			return level1();
		default:
			return null;
		}
	}
	
	
	
	
	private static ArrayList<Brick> level0() {
		ArrayList<Brick> bricks = new ArrayList<Brick>();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 2; j++)
				bricks.add(new Brick(0.075 + (0.15 * i), 0.05 + (0.08 * j), 0.1, 0.03));
		return bricks;
	}
	
	private static ArrayList<Brick> level1() {
		ArrayList<Brick> bricks = new ArrayList<Brick>();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 1; j++)
				bricks.add(new Brick(0.075 + (0.15 * i), 0.05 + (0.08 * j), 0.1, 0.03));
		return bricks;
	}
	
}
