/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ragna.wl1036.web.support;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;

/**
 *
 * @author npadilha
 */
public class Version implements Serializable {

    private static final long serialVersionUID = 336795660532373035L;

    private static final String DEFAULT_VERSION = "N/A";
    private static final String DEFAULT_APP_NAME = "SYNCH_LEG";
    private String appName;
    private String version;
    private String vendor;

    public Version(String versionString, String vendor) {
        try {
            this.vendor = vendor;
            appName = versionString.split("v\\.")[0].trim();
            version = versionString.split("v\\.")[1].trim();
        } catch (NullPointerException e) {
            defaultValues();
        } catch (ArrayIndexOutOfBoundsException e) {
            defaultValues();
        }
    }

    public String getVendor() {
        return vendor;
    }

    public String getAppName() {
        return appName;
    }

    public String getVersion() {
        return version;
    }

    private void defaultValues() {
        appName = DEFAULT_APP_NAME;
        version = DEFAULT_VERSION;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("appName", appName)
            .add("version", version).add("vendor", vendor).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(appName, version, vendor);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Version) {
            Version that = (Version) object;
            return Objects.equal(this.appName, that.appName)
                && Objects.equal(this.version, that.version)
                && Objects.equal(this.vendor, that.vendor);
        }
        return false;
    }
}