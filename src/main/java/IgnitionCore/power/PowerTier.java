package IgnitionCore.power;

public enum PowerTier {

	Tier1(40),
	Tier2(100),
	Tier3(200),
	Tier4(1000);

	private static PowerTier[] t = {null,Tier1,Tier2,Tier3,Tier4};
	private int flow;
	
	private PowerTier(int flow){
		this.flow = flow;
	}
	
	/**
	 * max transfer rate for energy 
	 * @return
	 */
	public int getFlow(){
		return flow;
	}
	
	/**
	 * 
	 * @param l position of the tier +1 
	 * tier 1 => 1
	 * tier 2 => 2
	 * tier 3 => 3
	 * tier 4 => 4
	 * @return
	 */
	public static PowerTier getTier(int l){
		return t[l];
	}

	/**
	 * 
	 * @param tier
	 * @return position of the tier (tier1 => 1,etc)
	 */
	public static int getPosition(PowerTier tier) {
		return tier.ordinal()+1;
	}
}

