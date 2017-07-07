package tp.pr3.inst.assignments;

public class TermParser {
	
	private final static Term[] terms =
		{ new Variable(""), new Number(0) };
	
	public static Term parse(String st) {
		Term tm;
		
		for (Term t : terms) {
			tm = t.parse(st);
			if (tm != null)
				return tm;
		}
		
		return null;
	}

}
