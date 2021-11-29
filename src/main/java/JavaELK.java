import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Map;

public class JavaELK {
     public static void main(String[] args) throws FileNotFoundException{
        //String filepath="output.txt";
        //File csvFile =new File("output.csv");
        File buildcsvFile =new File("build_output.csv");
        File sonarcsvFile =new File("sonar_output.csv");
        File cdcsvFile =new File("cd_output.csv");



        //for connector
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        SearchRequest buildsearchRequest = new SearchRequest();
        buildsearchRequest.indices("build_data");
        SearchSourceBuilder buildsearchSourceBuilder = new SearchSourceBuilder();
        buildsearchSourceBuilder.query(QueryBuilders.matchAllQuery());
        buildsearchRequest.source(buildsearchSourceBuilder);
        Map<String, Object> buildmap=null;

        try {
            SearchResponse buildsearchResponse = null;
            buildsearchResponse =client.search(buildsearchRequest, RequestOptions.DEFAULT);
            if (buildsearchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = buildsearchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    buildmap = hit.getSourceAsMap();
                    System.out.println("buildmap:"+ Arrays.toString(buildmap.entrySet().toArray()));

                    try(PrintWriter out = new PrintWriter(buildcsvFile)) {
                        out.println(Arrays.toString(buildmap.entrySet().toArray()));
                    }
                    catch(Exception e) {
                        System.out.println("Something went wrong." + e.getMessage());
                    }
                }

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //System.exit(0);

        // +++++++++++++++++++++++++++++++++++++ SOnar ++++++++++++++++++++++++++++++++++++++++++++++++++++

        SearchRequest sonarsearchRequest = new SearchRequest();
        sonarsearchRequest.indices("sonar_data");
        SearchSourceBuilder sonarsearchSourceBuilder = new SearchSourceBuilder();
        sonarsearchSourceBuilder.query(QueryBuilders.matchAllQuery());
        sonarsearchRequest.source(sonarsearchSourceBuilder);
        Map<String, Object> sonarmap=null;

        try {
            SearchResponse sonarsearchResponse = null;
            sonarsearchResponse =client.search(sonarsearchRequest, RequestOptions.DEFAULT);
            if (sonarsearchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = sonarsearchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    sonarmap = hit.getSourceAsMap();
                    System.out.println("Sonarmap:"+ Arrays.toString(sonarmap.entrySet().toArray()));

                    try(PrintWriter out = new PrintWriter(sonarcsvFile)) {
                        out.println(Arrays.toString(sonarmap.entrySet().toArray()));
                    }
                    catch(Exception e) {
                        System.out.println("Something went wrong." + e.getMessage());
                    }
                }

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //System.exit(0);

        // +++++++++++++++++++++++++++++++++++ CD ++++++++++++++++++++++++++++++++++++++++++++++++++
        SearchRequest cdsearchRequest = new SearchRequest();
        cdsearchRequest.indices("cd_data");
        SearchSourceBuilder cdsearchSourceBuilder = new SearchSourceBuilder();
        cdsearchSourceBuilder.query(QueryBuilders.matchAllQuery());
        cdsearchRequest.source(cdsearchSourceBuilder);
        Map<String, Object> cdmap=null;

        try {
            SearchResponse cdsearchResponse = null;
            cdsearchResponse =client.search(cdsearchRequest, RequestOptions.DEFAULT);
            if (cdsearchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = cdsearchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    cdmap = hit.getSourceAsMap();
                    System.out.println("cdmap:"+ Arrays.toString(cdmap.entrySet().toArray()));

                    try(PrintWriter out = new PrintWriter(cdcsvFile)) {
                        out.println(Arrays.toString(cdmap.entrySet().toArray()));
                    }
                    catch(Exception e) {
                        System.out.println("Something went wrong." + e.getMessage());
                    }
                }

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);

    }


}
