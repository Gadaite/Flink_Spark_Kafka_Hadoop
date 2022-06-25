package WorkAoutSpark.Main20220625;

import org.locationtech.jts.geom.Geometry;

import java.io.Serializable;

public class SummaryOfTrajectoryInCity implements Serializable {
    private String relationID;
    private String currentCity;
    private Double distanceInCity;
    private Double distanceToProvincialCapital;
    private Double distanceToProvincialCenter;
    private Geometry lineString;

    public SummaryOfTrajectoryInCity(String relationID, String currentCity, Double distanceInCity, Double distanceToProvincialCapital, Double distanceToProvincialCenter, Geometry lineString) {
        this.relationID = relationID;
        this.currentCity = currentCity;
        this.distanceInCity = distanceInCity;
        this.distanceToProvincialCapital = distanceToProvincialCapital;
        this.distanceToProvincialCenter = distanceToProvincialCenter;
        this.lineString = lineString;
    }
    public SummaryOfTrajectoryInCity(){}

    public String getRelationID() {
        return relationID;
    }

    public void setRelationID(String relationID) {
        this.relationID = relationID;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public Double getDistanceInCity() {
        return distanceInCity;
    }

    public void setDistanceInCity(Double distanceInCity) {
        this.distanceInCity = distanceInCity;
    }

    public Double getDistanceToProvincialCapital() {
        return distanceToProvincialCapital;
    }

    public void setDistanceToProvincialCapital(Double distanceToProvincialCapital) {
        this.distanceToProvincialCapital = distanceToProvincialCapital;
    }

    public Double getDistanceToProvincialCenter() {
        return distanceToProvincialCenter;
    }

    public void setDistanceToProvincialCenter(Double distanceToProvincialCenter) {
        this.distanceToProvincialCenter = distanceToProvincialCenter;
    }

    public Geometry getLineString() {
        return lineString;
    }

    public void setLineString(Geometry lineString) {
        this.lineString = lineString;
    }

    @Override
    public String toString() {
        return "SummaryOfTrajectoryInCity{" +
                "relationID='" + relationID + '\'' +
                ", currentCity='" + currentCity + '\'' +
                ", distanceInCity=" + distanceInCity +
                ", distanceToProvincialCapital=" + distanceToProvincialCapital +
                ", distanceToProvincialCenter=" + distanceToProvincialCenter +
                ", lineString=" + lineString +
                '}';
    }
}
