package com.example.demo.model

import tornadofx.ItemViewModel
import tornadofx.getProperty
import tornadofx.property

class Deposit(amount: Float) {
    var amount: Float by property(amount)
    fun amountProperty() = getProperty(Deposit::amount)
}

class DepositModel: ItemViewModel<Deposit>(Deposit(0.00f)) {
    val amount = bind(Deposit::amountProperty)
}

class Transfer(amount: Float, accountID: Int) {
    var amount: Float by property(amount)
    fun amountProperty() = getProperty(Transfer::amount)

    var accountID: Int by property(accountID)
    fun accountIDProperty() = getProperty(Transfer::accountID)
}

class TransferModel: ItemViewModel<Transfer>(Transfer(0.00f, 0)) {
    val amount = bind(Transfer::amountProperty)
    val accountID = bind(Transfer::accountIDProperty)
}