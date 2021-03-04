
public class Contain {
	protected int imgId;
	protected int tagId;
	
	public Contain() {}
	
	public Contain( int imgId, int tagId) {
		this.imgId = imgId;
		this.tagId = tagId;
	}
	
	public int getImgId() { return imgId;}
	public void setImgId(int imgId) { this.imgId = imgId;}
	
	public int getTagId() { return tagId;}
	public void setTagId(int tagId) { this.tagId = tagId;}
}
