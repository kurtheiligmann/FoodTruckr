//
//  CalendarViewController.m
//  FoodTruckr
//
//  Created by Kurt Heiligmann on 1/17/15.
//  Copyright (c) 2015 Kurt. All rights reserved.
//

#import "Calendar.h"
#import "CalendarViewController.h"
#import "DataController.h"
#import "Restaurant.h"
#import "RestaurantCell.h"
#import "RestaurantDetailDelegateImpl.h"
#import "RestaurantListDelegateImpl.h"
#import "RestaurantListDispatch.h"
#import "RestaurantViewController.h"

static NSString * const kRestaurantDetailSequeIdentifier = @"restaurantDetail";

typedef NS_ENUM(NSUInteger, CalendarDay) {
    CalendarDayMonday,
    CalendarDayTuesday,
    CalendarDayWednesday,
    CalendarDayThursday,
    CalendarDayFriday,
    CalendarDay_count
};

@interface CalendarViewController () <UITableViewDelegate, UITableViewDataSource, UIScrollViewDelegate, FTRRestaurantListDispatch>
@property (nonatomic, weak) IBOutlet UITableView *truckTable;
@property (nonatomic, strong) NSMutableArray *restaurantCells;
@property (nonatomic, strong) FTRRestaurantListDelegateImpl *listDelegate;
@property (nonatomic, strong) FTRRestaurantDetailDelegateImpl *detailDelegate;
@property (nonatomic) long selectedRestaurantId;
@property (nonatomic) CGPoint tableviewContentOffset;
@end

@implementation CalendarViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.listDelegate = [[FTRRestaurantListDelegateImpl alloc] initWithFTRRestaurantListDispatch:self];
    self.restaurantCells = [NSMutableArray array];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    RestaurantViewController *restaurantViewController = (RestaurantViewController *)segue.destinationViewController;
    restaurantViewController.restaurantId = self.selectedRestaurantId;
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    if (self.tableviewContentOffset.y > 20) {
        return UIStatusBarStyleLightContent;
    } else {
        return UIStatusBarStyleDefault;
    }
}

- (FTRRestaurant *)restaurantForDay:(CalendarDay)day inCalendar:(FTRCalendar *)calendar {
    FTRRestaurant *selectedRestaurant = nil;
    switch (day) {
        case CalendarDayMonday: {
            selectedRestaurant = [[DataController getCalendar] getMonday];
            break;
        }
        case CalendarDayTuesday: {
            selectedRestaurant = [[DataController getCalendar] getTuesday];
            break;
        }
        case CalendarDayWednesday: {
            selectedRestaurant = [[DataController getCalendar] getWednesday];
            break;
        }
        case CalendarDayThursday: {
            selectedRestaurant = [[DataController getCalendar] getThursday];
            break;
        }
        case CalendarDayFriday: {
            selectedRestaurant = [[DataController getCalendar] getFriday];
            break;
        }
        case CalendarDay_count:
            break;
    }
    
    return selectedRestaurant;
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    FTRRestaurant *restaurant = [self restaurantForDay:indexPath.row inCalendar:[DataController getCalendar]];
    [self.listDelegate onRestaurantClickedWithLong:[restaurant getId]];
}

#pragma mark - UITableViewDataSource

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 150;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *cellIdentifier = @"RestaurantCell";
    RestaurantCell *cell = nil;
    
    if ([self.restaurantCells count] > indexPath.row) {
        cell = self.restaurantCells[indexPath.row];
    } else {
        cell = [[[NSBundle mainBundle] loadNibNamed:cellIdentifier owner:nil options:nil] firstObject];

        self.restaurantCells[indexPath.row] = cell;
    
        switch ((CalendarDay)indexPath.row) {
            case CalendarDayMonday: {
                cell.restaurant = [[DataController getCalendar] getMonday];
                cell.dayLabel.text = NSLocalizedString(@"Monday", @"Monday");
                break;
            }
            case CalendarDayTuesday: {
                cell.restaurant = [[DataController getCalendar] getTuesday];
                cell.dayLabel.text = NSLocalizedString(@"Tuesday", @"Tuesday");
                break;
            }
            case CalendarDayWednesday: {
                cell.restaurant = [[DataController getCalendar] getWednesday];
                cell.dayLabel.text = NSLocalizedString(@"Wednesday", @"Wednesday");
                break;
            }
            case CalendarDayThursday: {
                cell.restaurant = [[DataController getCalendar] getThursday];
                cell.dayLabel.text = NSLocalizedString(@"Thursday", @"Thursday");
                break;
            }
            case CalendarDayFriday: {
                cell.restaurant = [[DataController getCalendar] getFriday];
                cell.dayLabel.text = NSLocalizedString(@"Friday", @"Friday");
                break;
            }
            case CalendarDay_count: {
                break;
            }
        }
    }
    
    return cell;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return CalendarDay_count;
}

#pragma mark - UIScrollViewDelegate

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
    self.tableviewContentOffset = scrollView.contentOffset;
    [self setNeedsStatusBarAppearanceUpdate];
}

#pragma mark - FTRRestaurantListDispatch

- (void)onShowRestaurantDetailWithLong:(jlong)id_ {
    self.selectedRestaurantId = (long)id_;
    [self performSegueWithIdentifier:kRestaurantDetailSequeIdentifier sender:self];
}

@end
