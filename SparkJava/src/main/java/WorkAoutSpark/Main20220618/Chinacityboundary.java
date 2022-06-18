package WorkAoutSpark.Main20220618;


public class Chinacityboundary {

  private long acroutes;
  private long adcode;
  private org.locationtech.jts.geom.Geometry center;
  private org.locationtech.jts.geom.Geometry centroid;
  private long childrenNum;
  private org.locationtech.jts.geom.Geometry region;
  private String level;
  private String name;
  private long parentCenter;
  private long subFeatureIndex;


  public long getAcroutes() {
    return acroutes;
  }

  public void setAcroutes(long acroutes) {
    this.acroutes = acroutes;
  }


  public long getAdcode() {
    return adcode;
  }

  public void setAdcode(long adcode) {
    this.adcode = adcode;
  }


  public org.locationtech.jts.geom.Geometry getCenter() {
    return center;
  }

  public void setCenter(org.locationtech.jts.geom.Geometry center) {
    this.center = center;
  }


  public org.locationtech.jts.geom.Geometry getCentroid() {
    return centroid;
  }

  public void setCentroid(org.locationtech.jts.geom.Geometry centroid) {
    this.centroid = centroid;
  }


  public long getChildrenNum() {
    return childrenNum;
  }

  public void setChildrenNum(long childrenNum) {
    this.childrenNum = childrenNum;
  }


  public org.locationtech.jts.geom.Geometry getRegion() {
    return region;
  }

  public void setRegion(org.locationtech.jts.geom.Geometry region) {
    this.region = region;
  }


  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getParentCenter() {
    return parentCenter;
  }

  public void setParentCenter(long parentCenter) {
    this.parentCenter = parentCenter;
  }


  public long getSubFeatureIndex() {
    return subFeatureIndex;
  }

  public void setSubFeatureIndex(long subFeatureIndex) {
    this.subFeatureIndex = subFeatureIndex;
  }

  @Override
  public String toString() {
    return "Chinacityboundary{" +
            "acroutes=" + acroutes +
            ", adcode=" + adcode +
            ", center=" + center +
            ", centroid=" + centroid +
            ", childrenNum=" + childrenNum +
            ", region=" + region +
            ", level='" + level + '\'' +
            ", name='" + name + '\'' +
            ", parentCenter=" + parentCenter +
            ", subFeatureIndex=" + subFeatureIndex +
            '}';
  }
}
