package a11847898;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Magic spells that do some sort of damage to a target
 */
public class AttackingSpell extends Spell {
	/**
	 * Defines, if HP or MP is affected.
	 * <p>
	 * type == true: affects HP
	 * <p>
	 * type == false: affects MP
	 */
	private boolean type;
	/**
	 * Defines, if amount is interpreted as an absolute value or as a percentage.
	 * <p>
	 * percentage == true: deduct value to subtract as 'amount' percentage of basic value
	 * <p>
	 * percentage == false: subtract amount directly 
	 */
	private boolean percentage;
	/**
	 * Has to be non-negative.
	 * <p>
	 * if percentage==true, amount must be in the interval [0,100]
	 */
	private int amount;
	
	/**
	 * @param name name
	 * @param manaCost manaCost
	 * @param levelNeeded levelNeeded
	 * @param type defines if health or mana is affected
	 * @param percentage defines if amount is an absolute or a percentage value
	 * @param amount amount
	 */
	public AttackingSpell(String name,	int manaCost, MagicLevel levelNeeded, boolean type, boolean percentage, int amount) {
		super(name, manaCost, levelNeeded);
		if (amount < 0)
			throw new IllegalArgumentException("Negative amount");
		if (percentage && amount > 100)
			throw new IllegalArgumentException("Percentage is bigger than 100");
		this.type = type;
		this.percentage = percentage;
		this.amount = amount;
	}

	/**
	 * If the target is protected against this spell (isProtected), then protection against
	 * exactly this spell is removed (removeProtection).
	 * Otherwise, use one of the functions takeDamage, takeDamagePercent, weakenMagic or
	 * weakenMagicPercent on target according to the flags type and percentage.
	 * @param target target that takes the damage
	*/
	@Override
	public void doEffect(MagicEffectRealization target) {
		if (target.isProtected(this)) {
			Set<AttackingSpell> temporarySet = new HashSet<>();
			temporarySet.add(this);
			target.removeProtection(temporarySet);
		}
		else {
			if (type) {
				if (percentage) {
					target.takeDamagePercent(amount);
				} else {
					target.takeDamage(amount);
				}
			} else {
				if (percentage) {
					target.weakenMagicPercent(amount);
				} else {
					target.weakenMagic(amount);
				}
			}
		}
	}

	/**
	 * Returns "; -'amount' 'percentage' 'HPorMP'".
	 * where 'percentage' is a '%'-sign if percentage is true,
	 * empty otherwise and HPorMP is HP if type is true, MP otherwise.
	 * E.g. "; -10 MP" or "; -50 % HP"
	 * @return "; -'amount' 'percentage' 'HPorMP'".
	 */
	@Override
	public String additionalOutputString() {
		String sign = percentage ? " %" : "";
		String signature = type ? "HP" : "MP";
		return "; -" + amount + sign + " " + signature;
	}

	//Task 4
	@Override
	public boolean isAttack() {
		return true;
	}
}