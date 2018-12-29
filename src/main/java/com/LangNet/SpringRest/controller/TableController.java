package com.LangNet.SpringRest.controller;

import java.util.List;

import com.LangNet.SpringRest.TableData;
import com.LangNet.SpringRest.model.*;
import com.LangNet.SpringRest.model.month.RecPerMonth;
import com.LangNet.SpringRest.model.month.UserPerMonth;
import com.LangNet.SpringRest.model.month.UserReferencePerMonth;
import com.LangNet.SpringRest.model.top.TopCountries;
import com.LangNet.SpringRest.model.top.TopUsers;
import com.LangNet.SpringRest.model.total.TotalLang;
import com.LangNet.SpringRest.model.total.TotalTime;
import com.LangNet.SpringRest.model.total.TotalUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/internal-dashboard/")
@RestController
public class TableController {

    private TableData data;


    @Autowired
    public TableController(TableData data) {
        this.data = data;
    }


    @GetMapping("user-per-month")
    public List<UserPerMonth> getUserPerMonth() {
        return data.getUserPerMonth();
    }

    @GetMapping("rec-per-month")
    public List<RecPerMonth> getRecPerMonth() {
        return data.getRecPerMonth();
    }

    @GetMapping("total-lang")
    public List<TotalLang> getTotalLang() {
        return data.getTotalLang();
    }

    @GetMapping("total-lang-USD")
    public double getTotalLangUSD() {
        return data.getTotalLangUSD();
    }

    @GetMapping("total-users")
    public List<TotalUsers> getTotalUsers() {
        return data.getTotalUsers();
    }

    @GetMapping("total-countries")
    public int getTotalCountries() {
        return data.getTotalCountries();
    }

    @GetMapping("total-time")
    public List<TotalTime> getTotalTime() {
        return data.getTotalTime();
    }

    @GetMapping("top-countries")
    public List<TopCountries> getUserPerCountry() {
        return data.getTopCountries();
    }

//    @GetMapping("rec-per-country")
//    public List<RecPerCountry> getRecPerCountry(@RequestParam("limit") int limit) {
//        return data.getRecPerCountry(limit);
//    }

    @GetMapping("finished-campaign")
    public List<FinishedCampaign> getFinishedCampaign() {
        return data.getFinishedCampaign();
    }

//    @GetMapping("top-user-rec-count")
//    public List<TopUserRecCount> getTopUserRecCount(@RequestParam("limit") int limit) {
//        return data.getTopUserRecCount(limit);
//    }

    @GetMapping("top-users")
    public List<TopUsers> getTopUsers() {
        return data.getTopUsers();
    }

    @GetMapping("users-ref")
    public List<UserReferencePerMonth> UserReferencePerMonth() {
        return data.getUserReferencePerMonth();
    }

    @RequestMapping("user-list")
    public List<UserList> getUserList(@RequestParam("boolOne") String boolOne, @RequestParam("andOr") String andOr, @RequestParam("boolTwo") String boolTwo) {
        return data.getUserList(boolOne, andOr, boolTwo);
    }

    @GetMapping("referral")
     public List<Referral> getReferral() { return data.getReferral(); }


    @GetMapping("new-users")
    public List<NewUsersList> getNewUsersList() { return data.getNewUsersList(); }

    @GetMapping("last-recorders")
    public List<UserList> getLastRecorders() { return data.getLastRecorders(); }


}






