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

    var freeTransactions = -1
    var transactionFee = 0.0f
    var annualInterestRate: Float = 0.0f


    fun initValues() {
        if (type == "Gold Cheque") {
            freeTransactions = 10
            transactionFee = 15.0f
        } else if (type == "Diamond Cheque") {
            freeTransactions = 30
            transactionFee = 20.0f
        } else if (type == "Tax Free Savings") {
            transactionFee = 40.0f
            annualInterestRate = 0.12f
        } else {
            // Easy Access Savings
            transactionFee = 30.0f
            annualInterestRate = 0.8f
        }
    }

    fun generateInterest() {
        initValues()

        println(annualInterestRate)
        println(balance *12)

        val add = (balance*(annualInterestRate/12))
        val form = "%.2f".format(add)
        val newBal = "%.2f".format(form.toFloat()+balance)
        balance = newBal.toFloat()
    }

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