package demo;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * @author 陈霖 2014-7-3
 */
public class PointRuleEngineImpl implements PointRuleEngine {
	/**
	 * kBase
	 */
	private KnowledgeBase kBase;

	@Override
	public void initEngine() {
		System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
		kBase = readKnowledgeBase();
	}

	@Override
	public void refreshEnginRule() {
		initEngine();
	}

	@Override
	public void executeRuleEngine(final PointDomain pointDomain) {
		if (kBase == null) {
			return;
		}
		StatefulKnowledgeSession ksession = kBase.newStatefulKnowledgeSession();
		ksession.insert(pointDomain);

		// fire
		ksession.fireAllRules();
		ksession.dispose();
	}

	/**
	 * @return KnowledgeBase
	 */
	private static KnowledgeBase readKnowledgeBase() {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newFileResource("rules/addpoint.drl"), ResourceType.DRL);
		kbuilder.add(ResourceFactory.newFileResource("rules/subpoint.drl"), ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}
}
