package poker;
// Generated Nov 21, 2014 5:20:30 PM by Hibernate Tools 3.4.0.CR1

public class Pokerresults implements java.io.Serializable {

	private int resultid;
	private int handstrength;
	private int hihand;
	private int lohand;
	private int kicker;

	public Pokerresults() {
	}

	public Pokerresults(int resultid, int handstrength, int hihand, int lohand,
			int kicker) {
		this.resultid = resultid;
		this.handstrength = handstrength;
		this.hihand = hihand;
		this.lohand = lohand;
		this.kicker = kicker;
	}

	public int getResultid() {
		return this.resultid;
	}

	public void setResultid(int resultid) {
		this.resultid = resultid;
	}

	public int getHandstrength() {
		return this.handstrength;
	}

	public void setHandstrength(int handstrength) {
		this.handstrength = handstrength;
	}

	public int getHihand() {
		return this.hihand;
	}

	public void setHihand(int hihand) {
		this.hihand = hihand;
	}

	public int getLohand() {
		return this.lohand;
	}

	public void setLohand(int lohand) {
		this.lohand = lohand;
	}

	public int getKicker() {
		return this.kicker;
	}

	public void setKicker(int kicker) {
		this.kicker = kicker;
	}

}
