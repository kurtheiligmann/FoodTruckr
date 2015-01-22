//
//  RestaurantViewController.m
//  FoodTruckr
//
//  Created by Kurt Heiligmann on 1/21/15.
//  Copyright (c) 2015 Kurt. All rights reserved.
//

#import "Calendar.h"
#import "DataController.h"
#import "Restaurant.h"
#import "RestaurantViewController.h"
#import "RestaurantDetailDelegateImpl.h"

@interface RestaurantViewController ()
@property (nonatomic, strong) FTRRestaurantDetailDelegateImpl *detailDelegate;
@property (nonatomic, strong) IBOutlet UIImageView *backgroundImage;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *nameLabelWidthConstraint;
@property (nonatomic, strong) IBOutlet UILabel *nameLabel;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *shortDescriptionLabelHeightConstraint;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *shortDescriptionLabelWidthConstraint;
@property (nonatomic, strong) IBOutlet UILabel *shortDescriptionLabel;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *longDescriptionLabelHeightConstraint;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *longDescriptionLabelWidthConstraint;
@property (nonatomic, strong) IBOutlet UILabel *longDescriptionLabel;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *paymentLabelHeightConstraint;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *paymentLabelWidthConstraint;
@property (nonatomic, strong) IBOutlet UILabel *paymentLabel;


@end

@implementation RestaurantViewController

- (void)awakeFromNib {
    self.detailDelegate = [[FTRRestaurantDetailDelegateImpl alloc] initWithFTRRestaurantDetailDispatch:self];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    [self.detailDelegate onRestaurantDetailShownWithLong:self.restaurantId];
    
    NSString *backgroundImagePath = [[[DataController getCalendar] getRestaurantForIdWithLong:self.restaurantId] getBackground];
    self.backgroundImage.image = [UIImage imageWithData:[NSData dataWithContentsOfURL:[NSURL URLWithString:backgroundImagePath]]];
}

- (IBAction)backButtonPressed {
    [self.navigationController popViewControllerAnimated:YES];
}
#pragma mark - FTRRestaurantDetailDispatch

- (void)onDisplayNameWithLong:(jlong)id_ {
    self.nameLabel.text = [[[DataController getCalendar] getRestaurantForIdWithLong:self.restaurantId] getName];
    self.nameLabelWidthConstraint.constant = self.view.frame.size.width - (self.nameLabel.frame.origin.x * 2);
}

- (void)onDisplayLogoWithLong:(jlong)id_ {
    NSLog(@"%s", __PRETTY_FUNCTION__);
}

- (void)onDisplayLinkWithLong:(jlong)id_  {
    NSLog(@"%s", __PRETTY_FUNCTION__);
}

- (void)onDisplayCuisineWithLong:(jlong)id_ {
    NSLog(@"%s", __PRETTY_FUNCTION__);
}

- (void)onDisplayPaymentWithLong:(jlong)id_ {
    NSString *payment = [[[DataController getCalendar] getRestaurantForIdWithLong:self.restaurantId] getPayment];
    self.paymentLabel.text = payment;
    self.paymentLabelHeightConstraint.constant = [self heightOfLabelFittingText:self.paymentLabel];
    self.paymentLabelWidthConstraint.constant = self.view.frame.size.width - (self.paymentLabel.frame.origin.x * 2);
}

- (void)onDisplayShortDescriptionWithLong:(jlong)id_ {
    NSString *shortDescription = [[[DataController getCalendar] getRestaurantForIdWithLong:self.restaurantId] getShortDescription];
    self.shortDescriptionLabel.text = shortDescription;
    self.shortDescriptionLabelHeightConstraint.constant = [self heightOfLabelFittingText:self.shortDescriptionLabel];
    self.shortDescriptionLabelWidthConstraint.constant = self.view.frame.size.width - (self.shortDescriptionLabel.frame.origin.x * 2);

}

- (void)onDisplayLongDescriptionWithLong:(jlong)id_ {
    NSString *longDescription = [[[DataController getCalendar] getRestaurantForIdWithLong:self.restaurantId] getLongDescription];
    self.longDescriptionLabel.text = longDescription;
    self.longDescriptionLabelHeightConstraint.constant = [self heightOfLabelFittingText:self.longDescriptionLabel];
    self.longDescriptionLabelWidthConstraint.constant = self.view.frame.size.width - (self.longDescriptionLabel.frame.origin.x * 2);
}

- (CGFloat)heightOfLabelFittingText:(UILabel *)label {
    CGRect textRect = [label.text boundingRectWithSize:CGSizeMake(self.view.frame.size.width - (label.frame.origin.x * 2), CGFLOAT_MAX)
                                               options:NSStringDrawingUsesLineFragmentOrigin
                                            attributes:@{ NSFontAttributeName : label.font }
                                               context:nil];
    return textRect.size.height + 10;
}

@end
