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
#import "RestaurantCell.h"

typedef NS_ENUM(NSUInteger, CalendarDay) {
    CalendarDayMonday,
    CalendarDayTuesday,
    CalendarDayWednesday,
    CalendarDayThursday,
    CalendarDayFriday,
    CalendarDay_count
};

@interface CalendarViewController () <UITableViewDelegate, UITableViewDataSource>
@property (nonatomic, weak) IBOutlet UITableView *truckTable;
@property (nonatomic, strong) FTRCalendar *calendar;
@property (nonatomic, strong) NSMutableArray *restaurantCells;
@end

@implementation CalendarViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.calendar = [DataController getCalendar];
    self.restaurantCells = [NSMutableArray array];
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
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
                cell.restaurant = [self.calendar getMonday];
                cell.dayLabel.text = NSLocalizedString(@"Monday", @"Monday");
                break;
            }
            case CalendarDayTuesday: {
                cell.restaurant = [self.calendar getTuesday];
                cell.dayLabel.text = NSLocalizedString(@"Tuesday", @"Tuesday");
                break;
            }
            case CalendarDayWednesday: {
                cell.restaurant = [self.calendar getWednesday];
                cell.dayLabel.text = NSLocalizedString(@"Wednesday", @"Wednesday");
                break;
            }
            case CalendarDayThursday: {
                cell.restaurant = [self.calendar getThursday];
                cell.dayLabel.text = NSLocalizedString(@"Thursday", @"Thursday");
                break;
            }
            case CalendarDayFriday: {
                cell.restaurant = [self.calendar getFriday];
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

@end
