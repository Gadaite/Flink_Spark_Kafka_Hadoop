package GadaiteToolGeo;

/**
 * made by Gadaite
 * 定义直线模型
 */
public class GeoLineStraight {
    /**
     * 斜率，截距
     */
    private double slope;
    private double intercept;
    private double verticallon;

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getIntercept() {
        return intercept;
    }

    public void setIntercept(double intercept) {
        this.intercept = intercept;
    }

    public double getVerticallon() { return verticallon; }

    public void setVerticallon(double verticallon) { this.verticallon = verticallon; }

    public GeoLineStraight(){}
    public GeoLineStraight(double slope, double intercept,double verticallon) {
        this.slope = slope;
        this.intercept = intercept;
        this.verticallon = verticallon;
    }
}
