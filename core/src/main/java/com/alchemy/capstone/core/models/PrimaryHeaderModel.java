package com.alchemy.capstone.core.models;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 *      @author Ambika
 * 
 *      Sling Model to fetch Header Primary Component properties
 *
 */

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PrimaryHeaderModel {
    @ValueMapValue
    private String trendingText;

    @ValueMapValue
    private String trendingDesc;

    private String todayDateStr;

    @PostConstruct
    public void init() {
        SimpleDateFormat format = new SimpleDateFormat("EEEE, d MMMM YYYY");
        todayDateStr = format.format(new Date());
    }
    
    public String getTrendingText() {
        return trendingText;
    }

    public String getTrendingDesc() {
        return trendingDesc;
    }

    public String getTodayDateStr() {
        return todayDateStr;
    }
}
