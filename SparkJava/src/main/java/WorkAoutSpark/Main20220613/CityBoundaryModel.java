package WorkAoutSpark.Main20220613;


import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import java.io.Serializable;

public class CityBoundaryModel implements Serializable {
    private Integer adcode;
    private String name;
    private Point center;
    private Point centroid;
    private Integer childrenNum;
    private String level;
    private Integer parentCenter;
    private Integer subFeatureIndex;
    private Integer acroutes;
    private Polygon geometry;
    public CityBoundaryModel(){}
    public CityBoundaryModel(Integer adcode, String name, Point center, Point centroid, Integer childrenNum, String level, Integer parentCenter, Integer subFeatureIndex, Integer acroutes, Polygon geometry) {
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

    public Polygon getGeometry() {
        return geometry;
    }

    public void setGeometry(Polygon geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "CityBoundaryModel{" +
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
