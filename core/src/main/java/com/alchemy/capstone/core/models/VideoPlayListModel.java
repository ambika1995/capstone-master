package com.alchemy.capstone.core.models;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.alchemy.capstone.core.dto.YoutubeVideoResponse;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VideoPlayListModel {
    @ValueMapValue
    private String[] youTubeLinks;

    private List<YoutubeVideoResponse> videoList;

    @PostConstruct
    public void init() {
        if(youTubeLinks != null) {
            videoList = new ArrayList<>();
            try(CloseableHttpClient httpClient = HttpClients.createDefault()){
                for(String youTubeLink : youTubeLinks) {
                    HttpGet request = new HttpGet("https://www.youtube.com/oembed?url="+youTubeLink);
                    try(CloseableHttpResponse response = httpClient.execute(request)){
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            String result = EntityUtils.toString(entity);
                            try(JsonReader jsonReader =  Json.createReader(new StringReader(result))){
                                    JsonObject youtubeResponse = jsonReader.readObject();
                                    YoutubeVideoResponse youtubeResponseDto = new YoutubeVideoResponse();
                                    youtubeResponseDto.setHtml(youtubeResponse.getString("html"));
                                    youtubeResponseDto.setTitle(youtubeResponse.getString("title"));
                                     youtubeResponseDto.setThumbnail_url(youtubeResponse.getString("thumbnail_url"));
                                    videoList.add(youtubeResponseDto);

                            }
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();                
            }
        }
    }

    public List<YoutubeVideoResponse> getVideoList() {
        return videoList;
    }
}
