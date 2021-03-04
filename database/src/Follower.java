
public class Follower {
	protected String idol;
	protected String fan;
	
	public Follower() {}
	
	public Follower(String idol, String fan) {
		this.idol = idol;
		this.fan = fan;
	}
	
	public String getIdol() { return idol;}
	public void setIdol(String idol) { this.idol = idol;}
	
	public String getFan() { return fan;}
	public void setFan(String fan) { this.fan = fan;}
}
