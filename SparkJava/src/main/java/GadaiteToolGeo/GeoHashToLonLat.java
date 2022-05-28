package GadaiteToolGeo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * made by Gadaite
 * 经纬度加密为hash编码，在数据量大时(尤其做联结的时候)用于指定hash值筛选以减少计算量
 */
public class GeoHashToLonLat implements Serializable {
    public final double Max_Lat = 90;
    public final double Min_Lat = -90;
    public final double Max_Lon = 180;
    public final double Min_Lon = -180;
    /**
     * 经纬度二值串
     */
    public static List<String> lonList = new ArrayList<>();
    public static List<String> latList = new ArrayList<>();
    /**
     *  字符集
     */
    private static final char[] CHARS =
            {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    final static HashMap<Character,Integer> lookup=new HashMap<>();
    static {
        int i=0;
        for (char c:CHARS){
            lookup.put(c,i++);
        }
    }
    /**
     * 将base32串转为合并的二值串
     * @param str base32串
     * @return 合并的二值串
     */
    private String base32Decode(String str) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            buffer.append(lookup.get(str.charAt(i)) + " ");
        }
        return buffer.toString();
    }
    /**
     * 将10进制数转为count个二进制数
     * @param decode32 空格拼接的10进制字符串
     * @return 五个二进制数(base32编码5位二进制即可)
     */
    private String convertToIndex(String decode32) {
        StringBuilder str = new StringBuilder();
        String[] nums = decode32.split(" ");
        for (String num : nums) {
            StringBuilder sb = new StringBuilder(Integer.toBinaryString(Integer.parseInt(num)));
            int length = sb.length();
            if (length < 5) {
                for (int i = 0; i < 5 - length; i++) {
                    sb.insert(0, "0");
                }
            }
            str.append(sb);
        }
        return str.toString();
    }
    /**
     * 分离经度与纬度串
     * @param latAndLngStr 经纬度二值串
     * 奇数位：纬度，偶数位：经度
     */
    public void SplitLatAndLng(String latAndLngStr) {
        for (int i = 0; i < latAndLngStr.length(); i++) {
            if (i % 2 == 1) {
                latList.add(String.valueOf(latAndLngStr.charAt(i)));
            } else {
                lonList.add(String.valueOf(latAndLngStr.charAt(i)));
            }
        }
    }
    /**
     * 将二值串转换为经纬度值
     * @param min  区间最小值
     * @param max  区间最大值
     * @param list 二值串列表
     */
    public double RevertToLonLat(double min, double max, List<String> list) {
        double value = 0;
        double mid;
        if (list.size() <= 0) {
            return (max + min) / 2.0;
        }
        for (String flag : list) {
            mid = (max + min) / 2;
            if ("0".equals(flag)) {
                max = mid;
            }
            if ("1".equals(flag)) {
                min = mid;
            }
            value = (max + min) / 2;
        }
        return value;
    }
    /**
     * 通过geohash值，来获取空间坐标
     * @param geoHashCode
     * @return
     */
    public String GetSpaceCoordinate(String geoHashCode) {
        String decode32 = base32Decode(geoHashCode);
        String str = convertToIndex(decode32);
        SplitLatAndLng(str);
        double lat = RevertToLonLat(Min_Lat, Max_Lat, latList);
        double lon = RevertToLonLat(Min_Lon, Max_Lon, lonList);
        return String.valueOf(lon) + "," + String.valueOf(lat);
    }

}
