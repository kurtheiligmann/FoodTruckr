//
//  CalendarViewController.m
//  FoodTruckr
//
//  Created by Kurt Heiligmann on 1/17/15.
//  Copyright (c) 2015 Kurt. All rights reserved.
//

#import "CalendarViewController.h"
#import "DataController.h"

@interface CalendarViewController () <UITableViewDelegate, UITableViewDataSource>
@property (nonatomic, weak) IBOutlet UITableView *truckTable;
@property (nonatomic, strong) NSArray *foodTrucks;
@end

@implementation CalendarViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.foodTrucks = [DataController getFoodTrucks];
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
}

#pragma mark - UITableViewDataSource

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *cellIdentifier = @"truckCellId";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellIdentifier];
    }
    
    
    
    return cell;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self.foodTrucks count];
}

@end
