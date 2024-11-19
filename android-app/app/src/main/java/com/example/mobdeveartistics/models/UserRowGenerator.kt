package com.example.mobdeveartistics.models

import com.example.mobdeveartistics.R

class UserRowGenerator {
    companion object{
        fun generateFollowingData(): ArrayList<UserRow> {
            var tempList = ArrayList<UserRow>()
            tempList.add(UserRow(R.drawable.user_icon_female, "USERS_FOLLOWING_BELOW", 130))
            tempList.add(UserRow(R.drawable.user_icon_female, "maxine_feliciano", 120))
            tempList.add(UserRow(R.drawable.user_icon_male, "matthew_cuaresma", 115))
            tempList.add(UserRow(R.drawable.user_icon_male, "gelo_limcangco", 110))
            tempList.add(UserRow(R.drawable.user_icon_male, "nate_recto", 100))
            return tempList
        }

        fun generateFollowersData(): ArrayList<UserRow> {
            var tempList = ArrayList<UserRow>()
            tempList.add(UserRow(R.drawable.user_icon_female, "USERS_FOLLOWERS_BELOW", 130))
            tempList.add(UserRow(R.drawable.user_icon_female, "maxine_feliciano", 120))
            tempList.add(UserRow(R.drawable.user_icon_male, "matthew_cuaresma", 115))
            tempList.add(UserRow(R.drawable.user_icon_male, "gelo_limcangco", 110))
            tempList.add(UserRow(R.drawable.user_icon_male, "nate_recto", 100))
            return tempList
        }
    }
}