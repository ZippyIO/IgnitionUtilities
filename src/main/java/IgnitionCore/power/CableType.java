package IgnitionCore.power;

public enum CableType {
	
	BLOCK,
	BIG_CENTER,
	RIBBON_BOTTOM,
	NOTHING;

	public static boolean isCompatible(CableType a, CableType b) {
		if(a == b)return true;
		if(a == BLOCK || b == BLOCK)return true;
		if((a == RIBBON_BOTTOM && b == RIBBON_BOTTOM) || (b == BIG_CENTER && a == BIG_CENTER))return true;
		return false;
	}
}
