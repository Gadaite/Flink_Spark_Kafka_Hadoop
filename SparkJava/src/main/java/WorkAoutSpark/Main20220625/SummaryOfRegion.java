package WorkAoutSpark.Main20220625;

import java.io.Serializable;

/**
 * 统计的各个省的采集到轨迹相关信息
 * 关联轨迹信息通过relationID
 */
public class SummaryOfRegion implements Serializable {
    private String relationID;
    private String regionName;
    private Integer cityCode;
    private Integer trajectoryCount;
    private Double area;

    public SummaryOfRegion(String relationID, String regionName, Integer cityCode, Integer trajectoryCount, Double area) {
        this.relationID = relationID;
        this.regionName = regionName;
        this.cityCode = cityCode;
        this.trajectoryCount = trajectoryCount;
        this.area = area;
    }
    public SummaryOfRegion(){}

    public String getRelationID() {
        return relationID;
    }

    public void setRelationID(String relationID) {
        this.relationID = relationID;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getTrajectoryCount() {
        return trajectoryCount;
    }

    public void setTrajectoryCount(Integer trajectoryCount) {
        this.trajectoryCount = trajectoryCount;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "SummaryOfRegion{" +
                "relationID='" + relationID + '\'' +
                ", regionName='" + regionName + '\'' +
                ", cityCode=" + cityCode +
                ", trajectoryCount=" + trajectoryCount +
                ", area=" + area +
                '}';
    }
}
