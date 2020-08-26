package com.example.demo.view

import com.example.demo.controller.ClientController
import com.example.demo.model.Client
import com.example.demo.model.ClientModel
import javafx.collections.FXCollections
import tornadofx.*

class ClientList : View("My View") {
    val controller: ClientController by inject()

    override val root = borderpane() {
        top {
            hbox(spacing = 10) {

                style {
                    padding = box(0.px, 0.px, 10.px, 0.px)
                }

                val t = textfield("")

                val filterTypes = FXCollections.observableArrayList(
                        "Name",
                        "ID Number"
                )

                val c = combobox(values = filterTypes)

                button("Filter List") {
                    setOnAction {
                        if (c.value !== null) {
                            controller.filterBy(t.text.toString(), c.value)
                        }
                    }
                }

                button("Clear Filter") {
                    setOnAction {
                        t.text = ""
                        controller.filterRemove()
                    }
                }

            }
        }
        center {
            tableview(controller.people) {
                column("Id", Client::idProperty)
                column("Name", Client::nameProperty)
                column("ID Number", Client::identityProperty)
                bindSelected(controller.selectedPerson)
                smartResize()
            }
            style {
                padding = box(10.px)
            }
        }
        bottom {
            hbox(spacing = 10) {

                style {
                    padding = box(10.px, 0.px, 0.px, 0.px)
                }

                button("Add Customer").action {

                    // Create a new model and set some default values
                    val newPerson = ClientModel()

                    newPerson.name.value = "New name"
                    newPerson.id.value = controller.getNewClientID()

                    // Create a new scope and inject the PersonModel into that scope
                    // You can also add other objects that could work as parameters for the PersonEditor
                    val newScope = Scope(newPerson)

                    // Find the PersonEditor in the new scope and open a modal - wait for the result
                    find<ClientEditorModal>(newScope).openModal(block = true)

                    println(newPerson.item.name)

                    if (newPerson.item.name !== null ) {
                        controller.people.add(newPerson.item)
                    }
                }

                button("Generate Monthly Interest").action {
                    controller.generateMonthlyInterest()
                }


                }

        }
    }
}

