package fr.upem.poo.td1;

public class Connection {
	private final long start;
	private final long end;
	
	public long getStart() {
		return start;
	}

	public long getEnd() {
		return end;
	}

	public Connection(long start,long end) {
		if (end < start) {
			throw new IllegalArgumentException("end < start ");
		}
		
		this.start=start;
		this.end=end;
	}
	
	public long getConnectionTime() {
		return end-start;
	}

	@Override 
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(start);
		sb.append(";");
		sb.append(end);
		sb.append(")");
		return sb.toString();
	}
}
