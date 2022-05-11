package com.codenameone.app.tracking;

import com.codename1.io.Log;
import com.codename1.ui.CN;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import java.util.Map;


public class AppTrackingNativeImpl implements com.codenameone.app.tracking.AppTrackingNative {
    private static boolean appleTrackingUsageDescriptionChecked = false;
    private static int status = 0;
    
    public void requestTrackingAuthorization(int param) {
        if (CN.isSimulator()) {
            checkAppleTrackingUsageDescription();
        }
        if (!isSupported()) {
            AppTracking.requestTrackingAuthorizationErrorCallback(param, "AppTracking API is not supported on this platform.  Try using an iOS skin", 0);
            return;
        }
        Command deny = new Command("Deny");
        Command approve = new Command("Approve");
        Command result = Dialog.show("Tracking Request", "This app would like permission to track you.", new Command[]{deny, approve});
        if (result == approve) {
            AppTracking.requestTrackingAuthorizationCallback(param, 3);
        } else {
            AppTracking.requestTrackingAuthorizationCallback(param, 2);
        }
        
        
    }
    
    

    public int getTrackingAuthorizationStatus() {
        if (CN.isSimulator()) {
            checkAppleTrackingUsageDescription();
        }
        return status;
    }

    public boolean isSupported() {
        if (CN.isSimulator()) {
            checkAppleTrackingUsageDescription();
        }
        return CN.isSimulator() && CN.getPlatformName().equals("ios");
    }
    
    private void checkAppleTrackingUsageDescription() {
        if (!appleTrackingUsageDescriptionChecked) {
            appleTrackingUsageDescriptionChecked = true;
            
            Log.p("[AppTracking] You should customize your app's app tracking usage description using the ios.NSUserTrackingUsageDescription build hint, which can get set either directly in codenameone_settings.properties, or using the build hints section of Codename One Preferences");
            Log.p("[AppTracking] For more information about Apple's App Tracking policies see https://developer.apple.com/documentation/apptrackingtransparency");
            
            Map<String, String> m = Display.getInstance().getProjectBuildHints();
            if(m != null) {
                if(!m.containsKey("ios.NSUserTrackingUsageDescription")) {
                    Display.getInstance().setProjectBuildHint("ios.NSUserTrackingUsageDescription", "Some functionality of the application involves tracking your access.");
                }
            }
        }
    }
    
    private String getTrackingUsageDescription() {
        Map<String, String> m = Display.getInstance().getProjectBuildHints();
        if (m.containsKey("ios.NSUserTrackingUsageDescription")) {
            return m.get("ios.NSUserTrackingUsageDescription");
        } else {
            return "Some functionality of the application involves tracking your access.";
        }
    }

}
