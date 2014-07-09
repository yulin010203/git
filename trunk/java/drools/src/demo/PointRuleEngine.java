package demo;

/**
 * @author 陈霖  2014-7-3
 */
public interface PointRuleEngine {
	
	/**
	 * initialize
	 */
	public void initEngine();
	
	/**
	 * refresh
	 */
	public void refreshEnginRule();
	
	/**
	 * @param pointDomain
	 */
	public void executeRuleEngine(final PointDomain pointDomain);
}
