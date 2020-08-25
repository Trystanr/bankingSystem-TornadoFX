package com.example.demo.model

import tornadofx.ItemViewModel
import tornadofx.getProperty
import tornadofx.property

class Account(id: Int, type: String, balance: Float) {
    var id by property(id)
    fun idProperty() = getProperty(Account::id)

    var type by property(type)
    fun typeProperty() = getProperty(Account::type)

    var balance by property(balance)
    fun balanceProperty() = getProperty(Account::balance)
}

class AccountModel : ItemViewModel<Account>(Account(0,"Gold Cheque", 0.0f)) {
    val id = bind(Account::idProperty)
    val type = bind(Account::typeProperty)
    val balance = bind(Account::balanceProperty)
}

class AccountDetailModel : ItemViewModel<Account>(Account(0,"Gold Cheque", 0.0f)) {
    val id = bind(Account::idProperty)
    val type = bind(Account::typeProperty)
    val balance = bind(Account::balanceProperty)
}