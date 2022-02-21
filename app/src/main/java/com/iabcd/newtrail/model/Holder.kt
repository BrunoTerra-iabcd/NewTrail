package com.iabcd.newtrail.model

class Holder(val name : String) {

    companion object{
        fun generateValues(): List<Holder> {
            val items = mutableListOf<Holder>()
            for (i in 0..45) {
                items.add(Holder("Planeta $i"))
            }
            return items
        }
    }

}