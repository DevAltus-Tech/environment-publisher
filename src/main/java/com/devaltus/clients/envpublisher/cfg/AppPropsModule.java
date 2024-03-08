/****************************************************************************
 * FILE: PropsModule.java
 * DSCRPT: 
 ****************************************************************************/





package com.devaltus.clients.envpublisher.cfg;





import javax.inject.Singleton;



import org.apache.commons.configuration2.ex.ConfigurationException;



import com.gcs.config.ConfigFile;
import com.gcs.config.ConfigUtils;
import com.gcs.metrics.AppMetrics;



import dagger.Module;
import dagger.Provides;
import lombok.SneakyThrows;





@Module
public class AppPropsModule
{
    @Singleton
    @Provides
    @SneakyThrows
    public ConfigFile cfgFile()
    {
        return new ConfigFile("envpublisher_cfg", "envpublisher.xml");
    }





    @Singleton
    @Provides
    @SneakyThrows
    public AppProps buildAppProps(final ConfigFile cfg_) 
    {
        try 
        {
	    final var props = cfg_.loadPropertiesFromConfig(AppProps.class);
            ConfigUtils.logToTrace(props);
            return props;
        }
        catch(ConfigurationException ex_)
        {
            throw new RuntimeException(ex_);
        }
    }





    @Singleton
    @Provides
    @SneakyThrows
    public static AppMetrics buildAppMetrics(final ConfigFile cfg_)
    {
        AppMetrics metrics = AppMetrics.initFromConfig(cfg_.getConfig());
        return metrics;
    }

}
