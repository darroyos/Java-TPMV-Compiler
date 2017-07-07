package tp.pr3.bc.jumps;

import tp.pr3.bc.ByteCode;

public abstract class Jump implements ByteCode {
	protected int instruction;
	
	public Jump(int n) {
		this.instruction = n;
	}
	
	@Override
	public ByteCode parse(String[] s) {
		if (s.length == 2) {
			try {
				return this.parseJump(s[0], Integer.parseInt(s[1]));
			} catch (NumberFormatException e) {
				return null;
			}
		}
		else
			return null;
	}
	
	protected abstract ByteCode parseJump(String s, int n);

}
