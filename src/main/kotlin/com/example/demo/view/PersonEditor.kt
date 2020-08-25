package com.example.demo.view

import com.example.demo.controller.ClientController
import com.example.demo.model.Account
import com.example.demo.model.AccountModel
import com.example.demo.model.ClientModel
import javafx.scene.control.TableView

//import no.tornadofx.fxsamples.withfxproperties.model.PhoneNumber
import tornadofx.*
import java.text.DecimalFormat

class PersonEditor : View() {
    val controller: ClientController by inject()
    var accountsTable: TableView<Account> by singleAssign()

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
            button("Delete") {
                setOnAction {
                    controller.deleteClientByID(controller.selectedPerson.id)
                }
            }
        }
        fieldset("Accounts") {
            vbox(5.0) {
                tableview<Account> {
                    accountsTable = this
                    isEditable = false
                    smartResize()
                    column("Type", Account::typeProperty)
                    column("Balance", Account::balanceProperty)
                    itemsProperty().bind(controller.selectedPerson.accounts)
                }
                button("Add account") {
                    setOnAction {
//                        val newNumber = Account("", "")
//                        controller.selectedPerson.accounts.value.add(newNumber)
//                        accountsTable.selectionModel.select(newNumber)
//                        accountsTable.edit(accountsTable.items.size - 1, accountsTable.columns.first())

                        val newAccount = AccountModel()

//                        newPerson.name.value = "New name"
//                        newPerson.id.value = controller.getNewClientID()

                        // Create a new scope and inject the PersonModel into that scope
                        // You can also add other objects that could work as parameters for the PersonEditor
                        val newScope = Scope(newAccount)

                        // Find the PersonEditor in the new scope and open a modal - wait for the result
                        find<AccountEditorModal>(newScope).openModal(block = true)

//                        controller.selectedPerson.accounts.add(newAccount.item)
//                        controller.add(newAccount.item)
                        controller.selectedPerson.accounts.value.add(newAccount.item)
                        accountsTable.selectionModel.select(newAccount.item)
                        accountsTable.edit(accountsTable.items.size - 1, accountsTable.columns.first())
                    }
                }
            }
        }
    }
}

class PersonEditorModal : Fragment("Add Customer") {
    val person: ClientModel by inject()

    override val root = form {
        fieldset {
            field("Name") {
                textfield(person.name).required()
            }

            field("ID") {
                textfield {
                    bind(person.id, format = DecimalFormat("0"))
                }
            }

        }
        button("Save and close") {
            isDefaultButton = true
            action {
                person.commit {
                    close()
                }
            }
        }
    }
}

class AccountEditorModal : Fragment("Add Customer") {
//    val person: ClientModel by inject()
    val account: AccountModel by inject()

    override val root = form {
        fieldset {
            field("Type") {
                textfield(account.type).required()
            }

            field("Balance") {
                textfield {
                    bind(account.balance, format = DecimalFormat("0.00"))
                }
            }

        }
        button("Save and close") {
            isDefaultButton = true
            action {
                account.commit {
                    close()
                }
            }
        }
    }
}