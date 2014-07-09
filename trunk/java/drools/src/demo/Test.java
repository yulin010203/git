package demo;

public class Test {

	public static void main(String[] args) {
		long start = System.nanoTime();
		PointRuleEngine pointRuleEngine = new PointRuleEngineImpl();
		pointRuleEngine.initEngine();
		long end = System.nanoTime();
		System.out.println(end - start);
		System.out.println("initialize...");
		final PointDomain pointDomain = new PointDomain();
		pointDomain.setUserName("hello kity");
		pointDomain.setBackMondy(100d);
		pointDomain.setBuyMoney(500d);
		pointDomain.setBackNums(1);
		pointDomain.setBuyNums(5);
		pointDomain.setBillThisMonth(5);
		pointDomain.setBirthDay(true);
		pointDomain.setPoint(0l);

		pointRuleEngine.executeRuleEngine(pointDomain);

		System.out.println("执行完毕BillThisMonth：" + pointDomain.getBillThisMonth());
		System.out.println("执行完毕BuyMoney：" + pointDomain.getBuyMoney());
		System.out.println("执行完毕BuyNums：" + pointDomain.getBuyNums());

		System.out.println("执行完毕规则引擎决定发送积分：" + pointDomain.getPoint());
	}
}
