
public enum Type {
	R("Rock", "P"),
	P("Paper", "S"),
	S("Scissors", "R");
	
	private String moveType;
	private String beatMove;
	
	Type(String moveType, String beatMove) {
		this.moveType = moveType;
		this.beatMove = beatMove;
	}
	
	public String getMoveType() {
		return moveType;
	}
	
	public String getBeatMove() {
		return beatMove;
	}
	
	
}
