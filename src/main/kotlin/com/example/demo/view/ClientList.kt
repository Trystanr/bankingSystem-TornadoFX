package com.example.demo.view

import com.example.demo.controller.ClientController
import com.example.demo.model.Client
import com.example.demo.model.ClientModel
import tornadofx.*

class ClientList : View("My View") {
    val controller: ClientController by inject()

    override val root = borderpane() {
        top {
            form {
                hbox {
                    val t = textfield("Name or ID")

                    button("Filter List") {
                        setOnAction {
                            controller.filterBy(t.text.toString())
                        }
                    }

                    button("Clear Filter") {
                        setOnAction {
                            controller.filterRemove()
                        }
                    }
                }
            }
        }
        center {
            tableview(controller.people) {
                column("Id", Client::idProperty)
                column("Name", Client::nameProperty)
                bindSelected(controller.selectedPerson)
                smartResize()
            }
            style {
                padding = box(10.px)
            }
        }
        bottom {
            hbox(spacing = 10) {
                button("Add Customer").action {

                    // Create a new model and set some default values
                    val newPerson = ClientModel()

                    newPerson.name.value = "New name"
                    newPerson.id.value = controller.getNewClientID()

                    // Create a new scope and inject the PersonModel into that scope
                    // You can also add other objects that could work as parameters for the PersonEditor
                    val newScope = Scope(newPerson)

                    // Find the PersonEditor in the new scope and open a modal - wait for the result
                    find<PersonEditorModal>(newScope).openModal(block = true)

                    controller.people.add(newPerson.item)
                }

                button("Print Clients") {
                    setOnAction {
//                        controller.getClientByID("52")
                    }
                }


            }

        }
    }
}

