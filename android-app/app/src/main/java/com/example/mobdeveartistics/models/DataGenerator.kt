package com.example.mobdeveartistics.models

import com.example.mobdeveartistics.R

object DataGenerator {
    fun generateData(): ArrayList<Data> {
        val tempList = ArrayList<Data>()
        tempList.add(
            Data(
                R.drawable.media_background_1,
                R.drawable.profile,
                10,
                50,
                "John Doe",
                "This is a sample caption"
            )
        )
        tempList.add(
            Data(
                R.drawable.bake_no_hana,
                R.drawable.profile,
                20,
                100,
                "Jane Doe",
                "This is another sample caption"
            )
        )
        tempList.add(
            Data(
                R.drawable.kitty,
                R.drawable.profile,
                30,
                150,
                "Bob Smith",
                "This is yet another sample caption"
            )
        )
        tempList.add(
            Data(
                R.drawable.kimi_no_yoru_wo_kure,
                R.drawable.profile,
                40,
                200,
                "Alice Johnson",
                "This is a sample caption with a long text"
            )
        )
        tempList.add(
            Data(
                R.drawable.idsmile,
                R.drawable.profile,
                50,
                250,
                "Mike Brown",
                "This is a sample caption with a very long text that will wrap to multiple lines"
            )
        )
        return tempList
    }
}