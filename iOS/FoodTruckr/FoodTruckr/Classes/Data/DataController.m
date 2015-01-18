//
//  DataController.m
//  FoodTruckr
//
//  Created by Kurt Heiligmann on 1/17/15.
//  Copyright (c) 2015 Kurt. All rights reserved.
//

#import "DataController.h"

static NSString * const kTruckListAddress = @"http://brianjmelton.com/food-truckr/static/trucks.json";

@implementation DataController

+ (NSArray *)getFoodTrucks {
    NSMutableArray *foodTrucks = [NSMutableArray array];
    
    return foodTrucks;
}

@end
