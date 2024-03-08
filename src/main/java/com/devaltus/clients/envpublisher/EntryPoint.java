/****************************************************************************
 * FILE: TxFileParserEntryPoint.java
 * DSCRPT: 
 ****************************************************************************/





package com.devaltus.clients.envpublisher;





import javax.inject.Inject;



import com.devaltus.clients.envpublisher.cfg.AppMainComponent;
import com.devaltus.clients.envpublisher.cfg.DaggerAppMainComponent;



import com.devaltus.clients.envpublisher.cfg.AppProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;





@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class EntryPoint
{

    //
    // dagger
    //
    final static AppMainComponent _comp;

    static
    {
        _comp = DaggerAppMainComponent.create();
    }




    //
    // my objects of interest
    //
    private final AppProps _props;





    //
    // actual entry point
    //
    public static void main(String[] args_)
    {
        EntryPoint ep = _comp.entryPoint();
    }

}
