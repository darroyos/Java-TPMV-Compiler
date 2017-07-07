package tp.pr3.inst.conditionals;

import tp.pr3.elements.LexicalParser;

public class ConditionParser {
	
	private final static Condition[] conditions = 
		{ new Less(), new LessEq(), new Equal(), new NotEqual() };
	
	public static Condition parse(String t1, String op, String t2, LexicalParser parser) {
		Condition cond;
		
		for (Condition cd : conditions) {
			cond = cd.parse(t1, op, t2, parser);
			if (cond != null)
				return cond;
		}
		
		return null;
	}

}
