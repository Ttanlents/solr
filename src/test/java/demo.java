import entity.Goods;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 余俊锋
 * @date 2020/11/16 15:11
 * @Description
 */
public class demo {
    private HttpSolrServer solrServer;
    @Before
    public void before(){
        solrServer=new HttpSolrServer("http://localhost:8983/solr/collection1");
    }
    @After
    public void afert() throws IOException, SolrServerException {
        solrServer.commit();
    }

    @Test  //key :value  q查询
    public void test01() throws IOException, SolrServerException {
        Goods goods=new Goods();
        goods.setId("1001");
        goods.setName("张全蛋生产手机");
        goods.setTitle("富士康流水线手机");
        goods.setPic("baidu.com");
        goods.setPrice(200.0);
        UpdateResponse updateResponse = solrServer.addBean(goods);
        System.out.println(updateResponse);
    }

    @Test  //插入更新  存在就更新
    public void query01() throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery("keywords:手机");
        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList results = response.getResults();
        System.out.println(results.getNumFound());
        List<Goods> goodsList = response.getBeans(Goods.class);
        goodsList.forEach(n->{
            System.out.println(n);
        });
    }

    @Test  //条件+过滤+排序+分页+指定查询的属性  查询
    public void query02() throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery("keywords:手机");
        solrQuery.setFilterQueries("price:[2000 TO 8999]");
        solrQuery.setSort(SolrQuery.SortClause.desc("price"));
        solrQuery.setStart(0);
        solrQuery.setRows(5);
        solrQuery.setFields("id,keywords,price");

        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("name");
        solrQuery.addHighlightField("title");
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");


        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList results = response.getResults();
        System.out.println(results.getNumFound());
        List<Goods> goodsList = response.getBeans(Goods.class);
        goodsList.forEach(n->{
            System.out.println(n);
        });

        //获取高亮数据   要额外获取
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        goodsList.forEach(n->{
            Map<String, List<String>> map = highlighting.get(n.getId());
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println(entry.getValue());
            }
        });
    }
}
