package WorkAoutSpark.Main20220625;

import org.locationtech.jts.geom.Geometry;

import java.io.Serializable;

public class ChinaCityBoundary implements Serializable {
    private Integer adcode;
    private String name;
    private Geometry center;
    private Geometry centroid;
    private Integer childrenNum;
    private String level;
    private Integer parentCenter;
    private Integer subFeatureIndex;
    private Integer acroutes;
    private Geometry geometry;

    public ChinaCityBoundary(Integer adcode, String name, Geometry center, Geometry centroid, Integer childrenNum, String level, Integer parentCenter, Integer subFeatureIndex, Integer acroutes, Geometry geometry) {
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
    public ChinaCityBoundary(){}

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

    public Geometry getCenter() {
        return center;
    }

    public void setCenter(Geometry center) {
        this.center = center;
    }

    public Geometry getCentroid() {
        return centroid;
    }

    public void setCentroid(Geometry centroid) {
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

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "ChinaCityBoundary{" +
                "adcode=" + adcode +
                ", name='" + name + '\'' +
                ", center=" + center +
                ", centroid=" + centroid +
                ", childrenNum=" + childrenNum +
                ", level='" + level + '\'' +
                ", parentCenter=" + parentCenter +
                ", subFeatureIndex=" + subFeatureIndex +
                ", acroutes=" + acroutes +
                ", geometry=" + geometry +
                '}';
    }
}
