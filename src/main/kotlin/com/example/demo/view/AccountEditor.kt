package com.example.demo.view

import com.example.demo.controller.ClientController
import com.example.demo.model.ClientModel
import com.example.demo.model.DepositModel
import javafx.stage.Modality
import javafx.stage.StageStyle
import tornadofx.*

class AccountEditor: View() {

    val controller: ClientController by inject()

    override val root = vbox {
        field("ID") {
            label(controller.selectedAccount.id)
        }

        field("Type") {
            label(controller.selectedAccount.type)
        }

        field("Balance") {
            label(controller.selectedAccount.balance)
        }

        hbox(10) {
            style {
                padding = box(10.px, 0.px, 10.px, 0.px)
            }

            button("Deposit") {
                action {
                    if (controller.selectedAccount.id.value !== null) {
                        println("Account selected")

                        val newDeposit = DepositModel()
                        val newScope = Scope(newDeposit)

                        find<DepositModal>(newScope).openModal(block = true)

                        println(newDeposit.item.amount)

                        controller.deposit(controller.selectedAccount.id.value, newDeposit.item.amount)
                    }
                }
            }

            button("Withdraw") {
                action {

                }
            }

            button("Transfer") {
                action {

                }
            }
        }
    }
}