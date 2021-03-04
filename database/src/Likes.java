
public class Likes {
	protected String email;
	protected int imgId;
	
	public Likes() {}
	
	public Likes( String email, int imgId) {
		this.email = email;
		this.imgId = imgId;
	}
	
	public String getEmail() { return email;}
	public void setEmail(String email) { this.email = email;}
	
	public int getImgId() { return imgId;}
	public void setImgId(int imgId) { this.imgId = imgId;}
}
