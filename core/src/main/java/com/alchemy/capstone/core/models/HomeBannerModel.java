package com.alchemy.capstone.core.models;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 *      @author Ambika
 * 
 *      Sling Model to fetch Banner Home Component properties
 *
 */
@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HomeBannerModel {
    
    @ValueMapValue
    private String[] articleLinks;

    @SlingObject
    ResourceResolver resolver;

    List<ArticleBannerModel> bannerList;

    /**
	 *     Post Construct method to read 5 articles properties
	 *     to display in Banner Home Component
	 */
    @PostConstruct
    public void init() {
        if(articleLinks != null) {
            bannerList = new ArrayList<>();
            for(String articleLink : articleLinks) {
                Resource articleResource = resolver.getResource(articleLink+"/jcr:content/root/container/article_banner");
                if(articleResource != null) {
                    ArticleBannerModel articleBanner = articleResource.adaptTo(ArticleBannerModel.class);
                    if(articleBanner != null) {
                        articleBanner.setPagePath(articleLink);
                        if(bannerList.size() < 5) {
                            bannerList.add(articleBanner);
                        }
                    }
                }
            }
        }
    }

    public List<ArticleBannerModel> getBannerList() {
        return bannerList;
    }

    public String[] getArticleLinks() {
        return articleLinks;
    }
}
