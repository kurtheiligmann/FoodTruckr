//
//  RestaurantCell.m
//  FoodTruckr
//
//  Created by Kurt Heiligmann on 1/20/15.
//  Copyright (c) 2015 Kurt. All rights reserved.
//

#import "Restaurant.h"
#import "RestaurantCell.h"

@interface RestaurantCell ()
@property (nonatomic, weak) IBOutlet UIImageView *backgroundImage;
@property (nonatomic, weak) IBOutlet UILabel *nameLabel;
@property (nonatomic, weak) IBOutlet UILabel *dayLabel;
@end

@implementation RestaurantCell

- (void)setRestaurant:(FTRRestaurant *)restaurant {
    _restaurant = restaurant;
    NSData *imageData = [NSData dataWithContentsOfURL:[NSURL URLWithString:[restaurant getBackground]]];
    self.backgroundImage.image = [UIImage imageWithData:imageData];
    
    self.nameLabel.text = [restaurant getName];
}

- (NSString *)reuseIdentifier {
    return [[self class] description];
}

@end
