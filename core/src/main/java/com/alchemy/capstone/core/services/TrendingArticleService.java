package com.alchemy.capstone.core.services;
import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import com.alchemy.capstone.core.models.ArticleBannerModel;

public interface TrendingArticleService {

    public ResourceResolver getResourceResolver();
    
    public List<ArticleBannerModel> getTrendingArticles();

}
