package WorkAoutSpark.Main20220613;


import java.io.Serializable;

public class ChinaCityBean implements Serializable {
    private Integer adcode;
    private String name;
    private String center;
    private String centroid;
    private Integer childrenNum;
    private String level;
    private Integer parentCenter;
    private Integer subFeatureIndex;
    private Integer acroutes;
    private String geometry;

    public ChinaCityBean(Integer adcode, String name, String center, String centroid, Integer childrenNum, String level, Integer parentCenter, Integer subFeatureIndex, Integer acroutes, String geometry) {
        this.adcode = adcode;
        this.name = name;
        this.center = center;
        this.centroid = centroid;
        this.childrenNum = childrenNum;
        this.level = level;
        this.parentCenter = parentCenter;
        this.subFeatureIndex = subFeatureIndex;
        this.acroutes = acroutes;
        this.geometry = geometry;
    }
    public ChinaCityBean(){}


    public Integer getAdcode() {
        return adcode;
    }

    public void setAdcode(Integer adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getCentroid() {
        return centroid;
    }

    public void setCentroid(String centroid) {
        this.centroid = centroid;
    }

    public Integer getChildrenNum() {
        return childrenNum;
    }

    public void setChildrenNum(Integer childrenNum) {
        this.childrenNum = childrenNum;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getParentCenter() {
        return parentCenter;
    }

    public void setParentCenter(Integer parentCenter) {
        this.parentCenter = parentCenter;
    }

    public Integer getSubFeatureIndex() {
        return subFeatureIndex;
    }

    public void setSubFeatureIndex(Integer subFeatureIndex) {
        this.subFeatureIndex = subFeatureIndex;
    }

    public Integer getAcroutes() {
        return acroutes;
    }

    public void setAcroutes(Integer acroutes) {
        this.acroutes = acroutes;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "ChinaCityBean{" +
                "adcode=" + adcode +
                ", name='" + name + '\'' +
                ", center='" + center + '\'' +
                ", centroid='" + centroid + '\'' +
                ", childrenNum=" + childrenNum +
                ", level='" + level + '\'' +
                ", parentCenter=" + parentCenter +
                ", subFeatureIndex=" + subFeatureIndex +
                ", acroutes=" + acroutes +
                ", geometry='" + geometry + '\'' +
                '}';
    }
}
