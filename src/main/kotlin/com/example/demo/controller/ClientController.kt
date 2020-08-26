package com.example.demo.controller

import com.example.demo.model.Account
import com.example.demo.model.AccountDetailModel
import com.example.demo.model.Client
import com.example.demo.model.ClientDetailModel
import javafx.beans.property.Property
import tornadofx.Controller
import tornadofx.SortedFilteredList

class ClientController: Controller() {
    //    val people = FXCollections.observableArrayList<Client>()
    val people = SortedFilteredList<Client>()
    val selectedPerson = ClientDetailModel()
    val selectedAccount = AccountDetailModel()


    init {
        // Add some test persons for the demo

//        val p = Client()
//        p.id = 0
//        p.identity = "0012545063081"
//        p.name = "Mr Lahey"
//
//
//        people.add(p)


    }

    fun getClientIndexByID(id: Int) :Int {
        var i = -1
        people.forEachIndexed { index, client ->
            if (client.id == id) {
                i = index
            }
        }
        return i
    }

    fun getNewClientID() : Int {
        if (people.size > 0) {
            return people.last().id + 1
        } else {
            return 1
        }
    }

    fun getNewAccountID() : Int {
        return if (selectedPerson.accounts.value.size > 0) {
            // Gets the length of the user ID and subtracts user ID from the previous account ID

            val userIDLen = selectedPerson.id.value.toString().length
            val i = selectedPerson.accounts.value.last().id.toString().removeRange(0,userIDLen)
            val n = i.toInt() + 1

            ((selectedPerson.id.value).toString() + (n).toString()).toInt()
        } else {
            ((selectedPerson.id.value).toString() + (1).toString()).toInt()
        }
    }

    fun deleteClientByID(id: Property<Number>) {
        people.removeAt(getClientIndexByID(id.value.toInt()))
    }

    fun filterBy(s: String, field: String) {
        if (field == "Name") {
            people.predicate = { it.name.contains(s)}
        } else if (field == "ID Number") {
            people.predicate = { it.identity.contains(s)}
        } else {
            filterRemove()
        }
    }

    fun filterRemove() {
        people.predicate = { it.name.contains("") }
        people.predicate = { it.identity.contains("") }
    }

    fun printAll() {
        for (person in people) {
            println(person.name)
            for (account in person.accounts) {
                println("  "+account.balance)
            }
        }
    }

    fun generateMonthlyInterest() {
        for (person in people) {
            println(person.name)
            for (account in person.accounts) {
                println("  "+account.balance)
                account.generateInterest()
                account.resetTransactions()
            }
        }
    }


    fun deposit(accountID: Int, amount: Float) {
        selectedPerson.accounts.value.forEachIndexed { index, account ->
            if (account.id == accountID) {
                selectedPerson.accounts.value[index].deposit(amount)
            }
        }
    }

    fun withdraw(accountID: Int, amount: Float):Boolean {

        var bRes = false

        selectedPerson.accounts.value.forEachIndexed { index, account ->
            if (account.id == accountID) {
                bRes = selectedPerson.accounts.value[index].canWithdraw(amount)

                if (bRes) {
                    selectedPerson.accounts.value[index].withdraw(amount)
                }
            }
        }

        return bRes
    }


}
