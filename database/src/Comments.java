
public class Comments {
	protected String email;
	protected int imgId;
	protected String detail;
	
	public Comments() {}
	
	public Comments( String email, int imgId) {
		this.email = email;
		this.imgId = imgId;
	}
	
	public String getEmail() { return email;}
	public void setEmail(String email) { this.email = email;}
	
	public int getImgId() { return imgId;}
	public void setImgId(int imgId) { this.imgId = imgId;}
	
	public String getDetail() { return detail;}
	public void setDetail(String detail) { this.detail = detail;}
}
