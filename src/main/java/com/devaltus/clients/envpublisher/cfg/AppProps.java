/****************************************************************************
 * FILE: CtrailProps.java
 * DSCRPT: 
 ****************************************************************************/





package com.devaltus.clients.envpublisher.cfg;




import java.util.HashMap;
import java.util.Map;



import org.apache.commons.configuration2.XMLConfiguration;



import com.gcs.config.IProps;
import com.gcs.runtime.VersionInfo;



import lombok.Data;
import lombok.extern.slf4j.Slf4j;






@Data
@Slf4j
public class AppProps implements IProps
{
    private static final long serialVersionUID = 1306480900200475926L;

    private class Keys
    {
        private static final String APP_NAME  = "App.Id";
        private static final String REST_NAME = "Rest.Name";
        private static final String REST_PORT = "Rest.Port";
    }

    private String _appId;
    private String _restName;
    private int    _restPort;



    @Override
    public void loadFromXml(final XMLConfiguration cfg_)
    {
        setAppId(cfg_.getString(Keys.APP_NAME));
        setRestName(cfg_.getString(Keys.REST_NAME));
        setRestPort(cfg_.getInt(Keys.REST_PORT, 8080));
    }





    @Override
    public Map<String, String> toMap()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put(Keys.APP_NAME, getAppId());
        map.put(Keys.REST_NAME, getRestName());
        map.put(Keys.REST_PORT, Integer.toString(getRestPort()));
        return null;
    }





    public String getVersion()
    {
        return VersionInfo.calcVersion(AppProps.class);
    }

}
