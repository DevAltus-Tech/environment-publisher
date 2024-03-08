/****************************************************************************
 * FILE: MktDataPublisherComponent.java
 * DSCRPT: 
 ****************************************************************************/





package com.devaltus.clients.envpublisher.cfg;





import javax.inject.Singleton;



import com.gcs.metrics.AppMetrics;

import com.devaltus.clients.envpublisher.EntryPoint;



import dagger.Component;





@Singleton
@Component(modules =
{
        AppPropsModule.class,
        EntryPointModule.class
})
public interface AppMainComponent
{
    public EntryPoint entryPoint();





    public AppProps appProps();





    public AppMetrics appMetrics();


}
