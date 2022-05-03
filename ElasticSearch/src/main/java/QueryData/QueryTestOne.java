package QueryData;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class QueryTestOne {
    public static void main(String[] args) throws Exception{
        //  建立远程连接到ElastiicSearch
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("192.168.1.10", 9200,"http"));
        //  使用高级API操作连接对象创建连接
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        //  搜索资源构造器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(1000);
        //  符合查询条件对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //  查询
        BoolQueryBuilder must = boolQueryBuilder.must(QueryBuilders.matchQuery("state", "KY"));
        SearchSourceBuilder query = searchSourceBuilder.query(must);
        SearchRequest bank = new SearchRequest("bank").source(query);
        //  响应
        SearchResponse search = restHighLevelClient.search(bank, RequestOptions.DEFAULT);
        SearchHit[] hits = search.getHits().getHits();
        for (SearchHit hit:hits){
            String str = hit.toString();
            //  这里可以转换为json格式
            JSONObject jsonObject = JSONObject.parseObject(str);
            JSONObject source = jsonObject.getJSONObject("_source");
            String account_number = source.getString("account_number");
            Double balance = source.getDouble("balance");
            String firstname = source.getString("firstname");
            String lastname = source.getString("lastname");
            Integer age = source.getInteger("age");
            String gender = source.getString("gender");
            String address = source.getString("address");
            String employer = source.getString("employer");
            String email = source.getString("email");
            String city = source.getString("city");
            String state = source.getString("state");
            System.out.println(account_number+" "+balance.toString()+" "+firstname+" "+lastname+" "+age.toString()+" "+gender+" "+
                    address+" "+employer+" "+email+" "+city+" "+state);
        }
        restHighLevelClient.close();

    }
}
