package com.example.demo.controller

import com.example.demo.model.Client
import com.example.demo.model.ClientDetailModel
import javafx.beans.property.Property
import tornadofx.Controller
import tornadofx.SortedFilteredList

class ClientController: Controller() {
    //    val people = FXCollections.observableArrayList<Client>()
    val people = SortedFilteredList<Client>()
    val selectedPerson = ClientDetailModel()


    init {
        // Add some test persons for the demo

        val p = Client()
        p.id = 0
        p.identity = "0012545063081"
        p.name = "Mr Lahey"

        people.add(p)
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
            return 0
        }
    }

    fun deleteClientByID(id: Property<Number>) {
        people.removeAt(getClientIndexByID(id.value.toInt()))
    }

    fun filterBy(s: String) {
        people.predicate = { it.name.contains(s)}
    }

    fun filterRemove() {
        people.predicate = { it.name.contains("") }
    }

}
