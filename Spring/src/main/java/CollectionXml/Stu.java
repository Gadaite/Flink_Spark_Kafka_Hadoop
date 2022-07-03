package CollectionXml;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Stu {
    private String[] cource;

    private List<String> list;

    private Map<String,String> map;

    private Set<String> set;

    private List<Cource> courceList;

    public void setCource(String[] cource) {
        this.cource = cource;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setCourceList(List<Cource> courceList) {
        this.courceList = courceList;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "cource=" + Arrays.toString(cource) +
                ", list=" + list +
                ", map=" + map +
                ", set=" + set +
                ", courceList=" + courceList +
                '}';
    }
}
