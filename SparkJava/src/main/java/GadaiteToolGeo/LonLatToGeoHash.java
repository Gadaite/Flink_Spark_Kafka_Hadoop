package GadaiteToolGeo;

import java.util.ArrayList;
import java.util.List;

public class LonLatToGeoHash {
    public final double Max_Lat = 90;
    public final double Min_Lat = -90;
    public final double Max_Lng = 180;
    public final double Min_Lng = -180;
    /**
     * 纬度二值串长度
     */
    private static int latLength;
    /**
     * 经度二值串长度
     */
    private static int lngLength;
    private final double latUnit = (Max_Lat - Min_Lat) / (1 << latLength);
    private final double lngUnit = (Max_Lng - Min_Lng) / (1 << lngLength);
    private final String[] base32Lookup = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "b", "c", "d", "e", "f", "g", "h", "j", "k",
            "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };
    /**
     * 二值化：对经纬度二分逼近，大于中间值的为1，小于中间值的为0，将其转为长度为length的二值串
     *
     * @param min   区间最小值
     * @param max   区间最大值
     * @param value 经度或纬度
     * @param count 二分次数
     * @param list  二值串
     */
    private void convert(double min, double max, double value, int count, List list) {
        if (list.size() > (count - 1)) {
            return;
        }
        double mid = (max + min) / 2;
        if (value < mid) {
            list.add('0');
            convert(min, mid, value, count, list);
        } else {
            list.add('1');
            convert(mid, max, value, count, list);
        }
    }
    /**
     * 将合并的二值串转为base32串
     *
     * @param str 合并的二值串
     * @return base32串
     */
    private String base32Encode(final String str) {
        String unit = "";
        StringBuilder sb = new StringBuilder();
        for (int start = 0; start < str.length(); start = start + 5) {
            unit = str.substring(start, start + 5);
            sb.append(base32Lookup[convertToIndex(unit)]);
        }
        return sb.toString();
    }
    /**
     * 每五个一组将二进制转为十进制
     *
     * @param str 五个为一个unit
     * @return 十进制数
     */
    private int convertToIndex(String str) {
        int length = str.length();
        int result = 0;
        for (int index = 0; index < length; index++) {
            result += str.charAt(index) == '0' ? 0 : 1 << (length - 1 - index);
        }
        return result;
    }
    /**
     * 经纬度二值串合并：偶数位放经度，奇数位放纬度，把2串编码组合生成新串
     *
     * @param lat 纬度
     * @param lng 经度
     */
    public String encode(double lat, double lng) {
        if (latLength < 1 || lngLength < 1) {
            return "";
        }
        List latList = new ArrayList<>(latLength);
        List lngList = new ArrayList<>(lngLength);
        // 获取维度二值串
        convert(Min_Lat, Max_Lat, lat, latLength, latList);
        // 获取经度二值串
        convert(Min_Lng, Max_Lng, lng, lngLength, lngList);
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < latList.size(); index++) {
            sb.append(lngList.get(index)).append(latList.get(index));
        }
//        如果二者长度不一样，说明要求的精度为奇数，经度长度比纬度长度大1
        if (lngLength != latLength) {
            sb.append(lngList.get(lngList.size() - 1));
        }
        return base32Encode(sb.toString());
    }
    /**
     * 根据精度获取GeoHash串
     *
     * @param precise 精度
     * @return GeoHash串
     */
    public String getGeoHash(double lon, double lat, int precise) {
        if (precise < 1 || precise > 9) {
            return "";
        }
        latLength = (precise * 5) / 2;
        if (precise % 2 == 0) {
            lngLength = latLength;
        } else {
            lngLength = latLength + 1;
        }
        return encode(lat, lon);
    }
}
