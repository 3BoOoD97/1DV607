package model;

public class Boat {
	private int length;
	private BoatType type;
	
	public enum BoatType {
		Canoe,
		Motorsailer,
		Sailboat,
		Other
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public BoatType getType() {
		return type;
	}

	public void setType(BoatType type) {
		this.type = type;
	}
}
