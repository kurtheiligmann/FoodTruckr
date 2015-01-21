//
//  DataController.m
//  FoodTruckr
//
//  Created by Kurt Heiligmann on 1/17/15.
//  Copyright (c) 2015 Kurt. All rights reserved.
//

#import "Calendar.h"
#import "DataController.h"
#import "Restaurant.h"

static NSString * const kTruckListAddress = @"http://brianjmelton.com/food-truckr/static/trucks.json";
static NSString * const kBackgroundKey = @"background";
static NSString * const kCuisineKey = @"cuisine";
static NSString * const kIdKey = @"id";
static NSString * const kLinkKey = @"link";
static NSString * const kLogoKey = @"logo";
static NSString * const kLongDescriptionKey = @"long_description";
static NSString * const kShortDescriptionKey = @"short_description";
static NSString * const kNameKey = @"name";
static NSString * const kPaymentKey = @"payment";

static NSString * const kMonday = @"monday";
static NSString * const kTuesday = @"tuesday";
static NSString * const kWednesday = @"wednesday";
static NSString * const kThursday = @"thursday";
static NSString * const kFriday = @"friday";

@implementation DataController

+ (FTRCalendar *)getCalendar {
    NSData *calendarData = [NSData dataWithContentsOfURL:[NSURL URLWithString:kTruckListAddress]];
    NSDictionary *parsedCalendar = [NSJSONSerialization JSONObjectWithData:calendarData options:kNilOptions error:nil];
    NSLog(@"%@", parsedCalendar);
    
    FTRCalendar *calendar = [[FTRCalendar alloc] init];
    for (NSString *day in [parsedCalendar allKeys]) {
        NSDictionary *restaurantValues = parsedCalendar[day];
        FTRRestaurant *restaurant = [[FTRRestaurant alloc] init];
        [restaurant setBackgroundWithNSString:restaurantValues[kBackgroundKey]];
        [restaurant setCuisineWithNSString:restaurantValues[kCuisineKey]];
        [restaurant setIdWithLong:[restaurantValues[kIdKey] longValue]];
        [restaurant setLinkWithNSString:restaurantValues[kLinkKey]];
        [restaurant setLogoWithNSString:restaurantValues[kLogoKey]];
        [restaurant setLongDescriptionWithNSString:restaurantValues[kLongDescriptionKey]];
        [restaurant setShortDescriptionWithNSString:restaurantValues[kShortDescriptionKey]];
        [restaurant setNameWithNSString:restaurantValues[kNameKey]];
        [restaurant setPaymentWithNSString:restaurantValues[kPaymentKey]];
        
        if ([[day lowercaseString] isEqualToString:kMonday]) {
            [calendar setMondayWithFTRRestaurant:restaurant];
        } else if ([[day lowercaseString] isEqualToString:kTuesday]) {
            [calendar setTuesdayWithFTRRestaurant:restaurant];
        } else if ([[day lowercaseString] isEqualToString:kWednesday]) {
            [calendar setWednesdayWithFTRRestaurant:restaurant];
        } else if ([[day lowercaseString] isEqualToString:kThursday]) {
            [calendar setThursdayWithFTRRestaurant:restaurant];
        } else if ([[day lowercaseString] isEqualToString:kFriday]) {
            [calendar setFridayWithFTRRestaurant:restaurant];
        }
    }

    return calendar;
}

@end
