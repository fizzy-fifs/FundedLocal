package com.example.holidayplanner.holiday;

import com.example.holidayplanner.interfaces.ControllerInterface;
import com.example.holidayplanner.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1.0/holidays")
public class HolidayController implements ControllerInterface<Holiday> {

    private final HolidayService holidayService;

    @Autowired
    public HolidayController(HolidayService holidayService) { this.holidayService = holidayService; }

    @Override
    @PostMapping(path = "/newholiday")
    public String create(Holiday holiday, Errors errors) { return holidayService.create(holiday); }

    @GetMapping(path = "/addholidaymaker/holiday={holidayId}&user={userId}")
    public String addHolidayMaker(@PathVariable("holidayId") String holidayId, @PathVariable("userId") String userId) {
        return holidayService.addHolidayMaker(holidayId, userId);
    }

    @GetMapping(path = "/getbudgetaggregates/{holidayId}")
    public String[] getBudgetAggregates(@PathVariable("holidayId") String holidayId) {
        return holidayService.aggregateHolidayBudgets(holidayId);
    }

    @Override
    public List<Holiday> getAll() {
        return null;
    }

    @Override
    public String update(String id, Holiday newInfo) {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }
}
