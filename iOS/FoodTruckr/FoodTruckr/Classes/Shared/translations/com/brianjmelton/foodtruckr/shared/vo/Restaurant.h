//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: Android/FoodTruckr/app/src/main/java/com/brianjmelton/foodtruckr/shared/vo/Restaurant.java
//

#ifndef _ComBrianjmeltonFoodtruckrSharedVoRestaurant_H_
#define _ComBrianjmeltonFoodtruckrSharedVoRestaurant_H_

#import "JreEmulation.h"

@interface ComBrianjmeltonFoodtruckrSharedVoRestaurant : NSObject {
 @public
  NSString *name_;
  jlong id__;
  NSString *logo_;
  NSString *background_;
  NSString *link_;
  NSString *cuisine_;
  NSString *payment_;
  NSString *shortDescription_;
  NSString *longDescription_;
}

- (NSString *)getName;

- (void)setNameWithNSString:(NSString *)name;

- (jlong)getId;

- (void)setIdWithLong:(jlong)id_;

- (NSString *)getLogo;

- (void)setLogoWithNSString:(NSString *)logo;

- (NSString *)getBackground;

- (void)setBackgroundWithNSString:(NSString *)background;

- (NSString *)getLink;

- (void)setLinkWithNSString:(NSString *)link;

- (NSString *)getCuisine;

- (void)setCuisineWithNSString:(NSString *)cuisine;

- (NSString *)getPayment;

- (void)setPaymentWithNSString:(NSString *)payment;

- (NSString *)getShortDescription;

- (void)setShortDescriptionWithNSString:(NSString *)shortDescription;

- (NSString *)getLongDescription;

- (void)setLongDescriptionWithNSString:(NSString *)longDescription;

- (NSString *)description;

- (instancetype)init;

- (void)copyAllFieldsTo:(ComBrianjmeltonFoodtruckrSharedVoRestaurant *)other;

@end

__attribute__((always_inline)) inline void ComBrianjmeltonFoodtruckrSharedVoRestaurant_init() {}

J2OBJC_FIELD_SETTER(ComBrianjmeltonFoodtruckrSharedVoRestaurant, name_, NSString *)
J2OBJC_FIELD_SETTER(ComBrianjmeltonFoodtruckrSharedVoRestaurant, logo_, NSString *)
J2OBJC_FIELD_SETTER(ComBrianjmeltonFoodtruckrSharedVoRestaurant, background_, NSString *)
J2OBJC_FIELD_SETTER(ComBrianjmeltonFoodtruckrSharedVoRestaurant, link_, NSString *)
J2OBJC_FIELD_SETTER(ComBrianjmeltonFoodtruckrSharedVoRestaurant, cuisine_, NSString *)
J2OBJC_FIELD_SETTER(ComBrianjmeltonFoodtruckrSharedVoRestaurant, payment_, NSString *)
J2OBJC_FIELD_SETTER(ComBrianjmeltonFoodtruckrSharedVoRestaurant, shortDescription_, NSString *)
J2OBJC_FIELD_SETTER(ComBrianjmeltonFoodtruckrSharedVoRestaurant, longDescription_, NSString *)

#endif // _ComBrianjmeltonFoodtruckrSharedVoRestaurant_H_
