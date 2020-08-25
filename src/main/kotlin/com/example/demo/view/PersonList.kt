package com.example.demo.view

import javafx.scene.control.TableView
import tornadofx.*

class PersonList : View("My View") {
    val controller: PersonController by inject()

    override val root = borderpane() {
        center {
            tableview(controller.people) {
                column("Id", Person::idProperty)
                column("Name", Person::nameProperty)
                bindSelected(controller.selectedPerson)
                smartResize()
            }
        }
        bottom {
            hbox(spacing = 10) {
                button("Add Customer").action {
                    // Create a new model and set some default values
                    val newPerson = PersonModel()
                    newPerson.name.value = "New name"
                    newPerson.id.value = "0"
                    // Create a new scope and inject the PersonModel into that scope
                    // You can also add other objects that could work as parameters for the PersonEditor
                    val newScope = Scope(newPerson)

                    // Find the PersonEditor in the new scope and open a modal - wait for the result
                    find<PersonEditorModal>(newScope).openModal(block = true)

                    println(newPerson)

                    controller.people.add(newPerson.item)
                }
            }

        }
    }
//    override val root =

}

