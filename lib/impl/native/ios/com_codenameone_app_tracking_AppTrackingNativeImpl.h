#import <Foundation/Foundation.h>
#if __IPHONE_OS_VERSION_MAX_ALLOWED >= __IPHONE_14_0
#import <AppTrackingTransparency/ATTrackingManager.h>
#endif

@interface com_codenameone_app_tracking_AppTrackingNativeImpl : NSObject {
}

-(void)requestTrackingAuthorization:(int)param;
-(int)getTrackingAuthorizationStatus;
-(BOOL)isSupported;
@end
