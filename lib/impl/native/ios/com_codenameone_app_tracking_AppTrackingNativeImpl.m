#import "com_codenameone_app_tracking_AppTrackingNativeImpl.h"
#import "com_codenameone_app_tracking_AppTracking.h"

@implementation com_codenameone_app_tracking_AppTrackingNativeImpl

-(void)requestTrackingAuthorization:(int)param{
    dispatch_async(dispatch_get_main_queue(), ^{
#if __IPHONE_OS_VERSION_MAX_ALLOWED >= __IPHONE_14_0
    if (@available(iOS 14, *)) {
        ATTrackingManagerAuthorizationStatus status = ATTrackingManager.trackingAuthorizationStatus;
        
        if (status == ATTrackingManagerAuthorizationStatusNotDetermined) {
            [ATTrackingManager requestTrackingAuthorizationWithCompletionHandler:^(ATTrackingManagerAuthorizationStatus status) {
                
                com_codenameone_app_tracking_AppTracking_requestTrackingAuthorizationCallback___int_int(CN1_THREAD_GET_STATE_PASS_ARG (JAVA_INT)param, (JAVA_INT)status);
            }];
        } else {
            com_codenameone_app_tracking_AppTracking_requestTrackingAuthorizationCallback___int_int(CN1_THREAD_GET_STATE_PASS_ARG (JAVA_INT)param, (JAVA_INT)status);
        }
    } else {
        // Fallback on earlier versions
        com_codenameone_app_tracking_AppTracking_requestTrackingAuthorizationCallback___int_int(CN1_THREAD_GET_STATE_PASS_ARG (JAVA_INT)param, (JAVA_INT)3);
    }
#else
    com_codenameone_app_tracking_AppTracking_requestTrackingAuthorizationCallback___int_int(CN1_THREAD_GET_STATE_PASS_ARG (JAVA_INT)param, (JAVA_INT)3); // Authorized
#endif
    });

}

-(int)getTrackingAuthorizationStatus{
    __block int result = 3;
#if __IPHONE_OS_VERSION_MAX_ALLOWED >= __IPHONE_14_0
    dispatch_sync(dispatch_get_main_queue(), ^{
        if (@available(iOS 14, *)) {
            result = (JAVA_INT)ATTrackingManager.trackingAuthorizationStatus;
        } else {
            result = 3;
        }
    });
#endif
    return result;
}

-(BOOL)isSupported{
    return YES;
}

@end
