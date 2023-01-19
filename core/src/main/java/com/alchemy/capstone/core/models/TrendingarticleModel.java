package com.alchemy.capstone.core.models;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.alchemy.capstone.core.services.TrendingArticleService;

@Model(adaptables = {Resource.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TrendingarticleModel {

    List<ArticleBannerModel> bannerList;

    @ValueMapValue
    private String title;

    @OSGiService
    TrendingArticleService trendingArticleService;

    @PostConstruct
    public void init(){
        bannerList = trendingArticleService.getTrendingArticles();
    }

    public List<ArticleBannerModel> getBannerList() {
        return bannerList;
    }

    public String getTitle() {
        return title;
    }
}
