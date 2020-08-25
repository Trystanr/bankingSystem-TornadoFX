package com.example.demo.view

import com.example.demo.controller.ClientController
import com.example.demo.model.Account
import com.example.demo.model.Client
import javafx.scene.control.TableView
import tornadofx.*

class PersonDetail : View() {
    val controller: ClientController by inject()
    var numbersTable: TableView<Account> by singleAssign()

    override val root = form {
        fieldset("Personal Information") {
            field("Name") {
                textfield(controller.selectedPerson.name)
            }
            button("Save") {
                setOnAction {
                    controller.selectedPerson.commit()
                }
            }
        }
        fieldset("Phone Numbers") {
            vbox(5.0) {
                tableview<Account> {
                    numbersTable = this
                    isEditable = true
                    smartResize()
                    column("Type", Account::typeProperty).makeEditable()
                    column("Balance", Account::balanceProperty).makeEditable()
                    itemsProperty().bind(controller.selectedPerson.accounts)
                }
                button("Add number") {
                    setOnAction {
                        val newNumber = Account("", "")
                        controller.selectedPerson.accounts.value.add(newNumber)
                        numbersTable.selectionModel.select(newNumber)
                        numbersTable.edit(numbersTable.items.size - 1, numbersTable.columns.first())
                    }
                }
            }
        }
    }
}
