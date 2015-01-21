//
//  RestaurantCell.h
//  FoodTruckr
//
//  Created by Kurt Heiligmann on 1/20/15.
//  Copyright (c) 2015 Kurt. All rights reserved.
//

#import <UIKit/UIKit.h>

@class FTRRestaurant;

@interface RestaurantCell : UITableViewCell

@property (nonatomic, strong) FTRRestaurant *restaurant;
@property (nonatomic, weak, readonly) UILabel *dayLabel;

@end
