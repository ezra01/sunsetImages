
public class LikeInfo {
protected int imgId;
protected int likeCount;
protected boolean boolResult;
public LikeInfo(){};
public LikeInfo(int imgId, int likeCount, boolean boolResult) {
	this.imgId = imgId;
	this.likeCount = likeCount;
	this.boolResult = boolResult;
}
public int getImgId() {
	return imgId;
}
public void setImgId(int imgId) {
	this.imgId = imgId;
}
public int getLikecount() {
	return likeCount;
}
public void setLikecount(int likeCount) {
	this.likeCount = likeCount;
}
public boolean isBoolResult() {
	return boolResult;
}
public void setBoolResult(boolean boolResult) {
	this.boolResult = boolResult;
}



}