package CollectionXml;

import java.util.List;

public class ListPojo {
   private List<Integer> list;

    public void setList(List<Integer> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ListPojo{" +
                "list=" + list +
                '}';
    }
}
