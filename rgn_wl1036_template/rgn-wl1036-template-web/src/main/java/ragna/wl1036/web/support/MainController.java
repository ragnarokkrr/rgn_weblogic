/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragna.wl1036.web.support;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author npadilha
 */
@Controller
public class MainController {

    @Autowired
    private Environment env;

    @RequestMapping(value = "/version")
    public @ResponseBody
    Map<String, String> versionMap() {

        Version version = retrieveVersion();

        return ImmutableMap.<String, String>builder()
            .put("appName", version.getAppName())
            .put("version", version.getVersion())
            .put("vendor", version.getVendor()).build();
    }

    private Version retrieveVersion() {
        Version version = new Version("SYNCH", "edweb.vendor");
        return version;
    }
    
    @RequestMapping(value = "/index.html")
    public String home(){
        
        return "index";
    }

}
