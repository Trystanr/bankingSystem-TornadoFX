package com.example.demo.model

import tornadofx.ItemViewModel
import tornadofx.getProperty
import tornadofx.property

class Account(type: String, balance: String) {
    var type by property(type)
    fun typeProperty() = getProperty(Account::type)

    var balance by property(balance)
    fun balanceProperty() = getProperty(Account::balance)
}

class AccountModel : ItemViewModel<Account>(Account("gold", "100")) {
    val type = bind(Account::typeProperty)
    val balance = bind(Account::balanceProperty)
}