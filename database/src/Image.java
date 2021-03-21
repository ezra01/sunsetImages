
public class Image {
	protected int imgId;
	protected String url;
	protected String details;
	protected String created;
	protected String poster;
	
	public Image() {}
	public Image(int imgId, String url,String details, String created, String poster) {
		this.imgId = imgId;
		this.url = url;
		this.details = details;
		this.created = created;
		this.poster = poster;
	}
	
	public Image(String url,String details, String poster) {
		this.url = url;
		this.details = details;
		this.poster = poster;
	}

	public int getImgId() { return imgId;}
	public void setImgId(int imgId) { this.imgId = imgId;}

	public String geturl() { return url;}
	public void seturl(String url) { this.url = url;}

	public String getdetails() { return details;}
	public void setdetails(String details) { this.details = details;}

	public String getcreated() { return created;}
	public void setcreated(String created) { this.created = created;}

	public String getposter() { return poster;}
	public void setposter(String poster) { this.poster = poster;}
}
