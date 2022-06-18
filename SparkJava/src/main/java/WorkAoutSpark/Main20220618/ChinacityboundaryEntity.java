package WorkAoutSpark.Main20220618;

import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import java.io.Serializable;

public class ChinacityboundaryEntity implements Serializable {
    private Integer acroutes;
    private Integer adcode;
    private Point center;
    private Point centroid;
    private Integer childrenNum;
    private Polygon region;
    private String level;
    private String name;
    private Integer parentCenter;
    private Integer subFeatureIndex;
    public ChinacityboundaryEntity(){}
    public ChinacityboundaryEntity(Integer acroutes, Integer adcode, Point center, Point centroid, Integer childrenNum, Polygon region, String level, String name, Integer parentCenter, Integer subFeatureIndex) {
        this.acroutes = acroutes;
        this.adcode = adcode;
        this.center = center;
        this.centroid = centroid;
        this.childrenNum = childrenNum;
        this.region = region;
        this.level = level;
        this.name = name;
        this.parentCenter = parentCenter;
        this.subFeatureIndex = subFeatureIndex;
    }

    public Integer getAcroutes() {
        return acroutes;
    }

    public void setAcroutes(Integer acroutes) {
        this.acroutes = acroutes;
    }

    public Integer getAdcode() {
        return adcode;
    }

    public void setAdcode(Integer adcode) {
        this.adcode = adcode;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public Integer getChildrenNum() {
        return childrenNum;
    }

    public void setChildrenNum(Integer childrenNum) {
        this.childrenNum = childrenNum;
    }

    public Polygon getRegion() {
        return region;
    }

    public void setRegion(Polygon region) {
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

    @Override
    public String toString() {
        return "ChinacityboundaryEntity{" +
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
