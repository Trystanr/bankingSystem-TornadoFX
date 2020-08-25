package com.example.demo.view

import com.example.demo.controller.ClientController
import com.example.demo.model.Account
import com.example.demo.model.AccountModel
import com.example.demo.model.ClientModel
import javafx.scene.control.TableView
import javafx.scene.control.TextField

//import no.tornadofx.fxsamples.withfxproperties.model.PhoneNumber
import tornadofx.*
import java.text.DecimalFormat

class ClientEditor : View() {
    val controller: ClientController by inject()
    var accountsTable: TableView<Account> by singleAssign()

    override val root = form {
        fieldset("Personal Information") {
            field("ID") {
                label(controller.selectedPerson.id)
            }

            field("Name") {
                textfield(controller.selectedPerson.name)
            }

            field("Identity Number") {
                textfield(controller.selectedPerson.identity)
            }

            hbox(spacing = 10) {

                style {
                    padding = box(10.px, 0.px, 10.px, 0.px)
                }

                button("Save") {
                    setOnAction {
                        controller.selectedPerson.commit()
                    }
                }
                button("Delete") {
                    setOnAction {
                        if (controller.selectedPerson.id.value !== null) {
                            controller.deleteClientByID(controller.selectedPerson.id)
                        }

                        println(controller.selectedPerson.id)
                    }
                }
            }
        }
        fieldset("Accounts") {
            vbox(5.0) {
                tableview<Account> {
                    accountsTable = this
                    isEditable = false
                    smartResize()
                    column("ID", Account::idProperty)
                    column("Type", Account::typeProperty)
                    column("Balance", Account::balanceProperty)
                    itemsProperty().bind(controller.selectedPerson.accounts)
                    bindSelected(controller.selectedAccount)
                }
                button("Add account") {
                    setOnAction {

                        val newAccount = AccountModel()

                        newAccount.id.value = controller.getNewAccountID()

                        val newScope = Scope(newAccount)

                        find<AccountEditorModal>(newScope).openModal(block = true)

                        controller.selectedPerson.accounts.value.add(newAccount.item)
//                        accountsTable.selectionModel.select(newAccount.item)
//                        accountsTable.edit(accountsTable.items.size - 1, accountsTable.columns.first())
                    }
                }
            }
        }

        fieldset("Selected Account") {
            add<AccountEditor>()
        }
    }
}