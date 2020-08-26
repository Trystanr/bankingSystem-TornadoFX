package com.example.demo.model

import tornadofx.ItemViewModel
import tornadofx.getProperty
import tornadofx.property

class Deposit(amount: Float) {
    var amount by property(amount)
    fun amountProperty() = getProperty(Deposit::amount)
}

class DepositModel(): ItemViewModel<Deposit>(Deposit(0.00f)) {
    val amount = bind(Deposit::amountProperty)
}