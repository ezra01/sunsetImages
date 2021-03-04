
public class Image {
	protected int imgId;
	protected String url;
	protected String details;
	protected String created;
	
	public Image() {}
	public Image(int imgId, String url,String details, String created) {
		this.imgId = imgId;
		this.url = url;
		this.details = details;
		this.created = created;
	}

	public int getImgId() { return imgId;}
	public void setImgId(int imgId) { this.imgId = imgId;}

	public String geturl() { return url;}
	public void seturl(String url) { this.url = url;}

	public String getdetails() { return details;}
	public void setdetails(String details) { this.details = details;}

	public String getcreated() { return created;}
	public void setcreated(String created) { this.created = created;}
}
