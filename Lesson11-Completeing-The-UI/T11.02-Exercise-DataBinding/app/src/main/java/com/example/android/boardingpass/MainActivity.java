package com.example.android.boardingpass;

/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.boardingpass.databinding.ActivityMainBinding;
import com.example.android.boardingpass.utilities.FakeDataUtils;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //DONE (3) Create a data binding instance called mBinding of type ActivityMainBinding
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // DONE (4) Set the Content View using DataBindingUtil to the activity_main layout
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // DONE (5) Load a BoardingPassInfo object with fake data using FakeDataUtils
        BoardingPassInfo boardingPassInfo = FakeDataUtils.generateFakeBoardingPassInfo();

        // DONE (9) Call displayBoardingPassInfo and pass the fake BoardingInfo instance
        displayBoardingPassInfo(boardingPassInfo);

    }

    private void displayBoardingPassInfo(BoardingPassInfo info) {

        // DONE (6) Use mBinding to set the Text in all the textViews using the data in info
        activityMainBinding.textViewPassengerName.setText(info.passengerName);
        activityMainBinding.textViewFlightCode.setText(info.flightCode);
        activityMainBinding.textViewOriginAirport.setText(info.originCode);
        activityMainBinding.textViewDestinationAirport.setText(info.destCode);
        activityMainBinding.textViewGate.setText(info.departureGate);
        activityMainBinding.textViewTerminal.setText(info.departureTerminal);
        activityMainBinding.textViewSeat.setText(info.seatNumber);

        // DONE (7) Use a SimpleDateFormat formatter to set the formatted value in time text views
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        activityMainBinding.textViewBoardingTime.setText(simpleDateFormat.format(info.boardingTime));
        activityMainBinding.textViewDepartureTime.setText(simpleDateFormat.format(info.departureTime));
        activityMainBinding.textViewArrivalTime.setText(simpleDateFormat.format(info.arrivalTime));

        // DONE (8) Use TimeUnit methods to format the total minutes until boarding
        long minutesToBoarding = info.getMinutesUntilBoarding();
        long hoursToBoarding = TimeUnit.MINUTES.toHours(minutesToBoarding);
        long minuteInHour = minutesToBoarding - TimeUnit.HOURS.toMinutes(hoursToBoarding);

        String boardingInCountdown = getResources().getString(R.string.countDownFormat, hoursToBoarding, minuteInHour);

        activityMainBinding.textViewBoardingInCountdown.setText(boardingInCountdown);

    }
}

