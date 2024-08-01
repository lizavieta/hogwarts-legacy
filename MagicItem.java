package a11847898;

/**
 * MagicItems are items that can cause magic effects on other objects. So they are a source
 * of magic. As items, they can be traded, and they also can be the target of magic effects
 */
public abstract class MagicItem implements Tradeable, MagicEffectRealization, MagicSource, Localizable{

	//TASK 1 from test 2
	String origin;
	//
	/**
	 * Must not be null or empty
	 * */
	private String name;
	/**
	 * Number of usages remaining; must not be negative
	 */
	private int usages;
	/**
	 * Must not be negative
	 */
	private int price;
	/**
	 * must not be negative
	 */
	private int weight;

	/**
	 * @param name name
	 * @param usages number of usages still left
	 * @param price price
	 * @param weight weight
	 */
	public MagicItem(String name, int usages, int price, int weight, String origin) {
		if (name == null || name.isEmpty() || usages < 0 || price < 0 || weight < 0) {
			throw new IllegalArgumentException("Invalid parameter for MagicItem");
		}
		this.name = name;
		this.usages = usages;
		this.price = price;
		this.weight = weight;
		//Task 1 for test 2
		this.origin = origin;
	}

	public MagicItem(String name, int usages, int price, int weight) {
		this(name, usages, price, weight, "Hogwarts");
	}

	//Task 1 for test 2

	@Override
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Override
	public String getOrigin() {
		return origin;
	}

    /**
     * Returns value of usages (for access from deriving classes)
     * @return value of instance variable usages
     */
	public int getUsages() {
		return usages;
	}

    /**
     * If usages > 0 reduce usage by 1 and return true, otherwise return false
     * @return returns true if usage is still possible
     */
	public boolean tryUsage() {
		if (usages > 0) {
			--usages;
			return true;
		}
		return false;
	}

    /**
     * Returns "use" if usages is equal to 1, "uses" otherwise
     * @return "use" or "uses" depending on the value of usages
     */
	public String usageString() {
		return (usages == 1) ? "use" : "uses";
	}

    /**
     * returns empty string. Is overridden in deriving classes as needed
     * @return ""
     */
	public String additionalOutputString() {
		return "";
	}

    /**
     * Formats this object according to
     * "['name'; 'weight' g; 'price' 'currencyString'; 'usages' 'usageString''additionalOutputString']"
     * 'currencyString' is "Knut" if price is 1, "Knuts" otherwise
     * e.g. (when additionalOutput() returns an empty string)
     * "[Accio Scroll; 1 g; 1 Knut; 5 uses]" or "[Alohomora Scroll; 1 g; 10 Knuts; 1 use]"
     * @return "['name'; 'weight' g; 'price' 'currencyString'; 'usages' 'usageString''additionalOutputString']"
     */
	//Changed for Task 1 from test 2
	@Override 
	public String toString() {
		String currencyString = (price == 1) ? "Knut" : "Knuts";
        //return "[" + name + "; " + weight + " g; " + price + " " + currencyString + "; " + usages + " " + usageString()  + additionalOutputString() + "]";
		return "[" + name + " (" + getOriginFormatted() + "); " + weight + " g; " + price + " " + currencyString + "; " + usages + " " + usageString()  + additionalOutputString() + "]";
	}

	//Tradeable Interface:

	/**
	 * Returns price of the object
	 * @return value of instance variable price
	 */
	@Override
	public int getPrice() {
		return price;
	}

    /**
     * Returns weight of the object
     * @return value of instance variable weight
     */
	@Override    
	public int getWeight() {
		return weight;
	}
	  
	//MagicSource Interface:

    /**
     * Always returns true; no Exceptions needed
     */
	@Override
	public boolean provideMana(MagicLevel levelNeeded, int amount) {
		if (levelNeeded == null || amount < 0) //unit test doesn't pass without throwing exception
			throw new IllegalArgumentException("Invalid parameter");
		return true;
	}

	//MagicEffectRealization Interface:
	
	/**
	 * Reduce usages to usages*(1-percentage/100.)
	 */
	@Override
	public void takeDamagePercent(int percentage) {
		if (percentage < 0 || percentage > 100)
			throw new IllegalArgumentException("Invalid percentage of damage: less than 0 or more than 100");
		usages = (int)(usages * (1 - percentage/100.0));
	}

	//Task 2

	public String getName() {
		return name;
	}
}