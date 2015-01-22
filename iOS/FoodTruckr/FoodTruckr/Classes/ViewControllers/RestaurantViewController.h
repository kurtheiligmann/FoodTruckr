//
//  RestaurantViewController.h
//  FoodTruckr
//
//  Created by Kurt Heiligmann on 1/21/15.
//  Copyright (c) 2015 Kurt. All rights reserved.
//

#import "RestaurantDetailDispatch.h"
#import <UIKit/UIKit.h>

@class FTRRestaurantDetailDelegateImpl;

@interface RestaurantViewController : UIViewController <FTRRestaurantDetailDispatch>

@property (nonatomic) long restaurantId;

@end
