package com.example.demo.view

import com.example.demo.controller.ClientController
import com.example.demo.model.DepositModel
import com.example.demo.model.TransferModel
import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import tornadofx.*

class AccountEditor: View() {

    private val controller: ClientController by inject()

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

                        find<SingleValueModal>(newScope).openModal(block = true)

                        println(newDeposit.item.amount)

                        controller.deposit(controller.selectedAccount.id.value, newDeposit.item.amount)

                        alert(
                                type = Alert.AlertType.INFORMATION,
                                header = "Successfully Deposited R${newDeposit.item.amount}",
                                actionFn = {
                                    btnType ->
                                    if (btnType.buttonData == ButtonBar.ButtonData.OK_DONE) {
                                        println("OK")
                                    }
                                }
                        )




                    }
                }
            }

            button("Withdraw") {
                action {

                    val newDeposit = DepositModel()
                    val newScope = Scope(newDeposit)

                    find<SingleValueModal>(newScope).openModal(block = true)

                    println(newDeposit.item.amount)

//                    controller.deposit(controller.selectedAccount.id.value, newDeposit.item.amount)

                    if (controller.withdraw(controller.selectedAccount.id.value, newDeposit.item.amount)) {

                        alert(
                                type = Alert.AlertType.INFORMATION,
                                header = "Successfully Withdrew R${newDeposit.item.amount}",
                                actionFn = {
                                    btnType ->
                                    if (btnType.buttonData == ButtonBar.ButtonData.OK_DONE) {
                                        println("OK")
                                    }
                                }
                        )
                    } else {
                        alert(
                                type = Alert.AlertType.ERROR,
                                header = "Could Not Withdraw R${newDeposit.item.amount}",
                                actionFn = {
                                    btnType ->
                                    if (btnType.buttonData == ButtonBar.ButtonData.OK_DONE) {
                                        println("OK")
                                    }
                                }
                        )
                    }



                }
            }

            button("Transfer") {
                action {
                    val newTransfer = TransferModel()
                    val newScope = Scope(newTransfer)

                    find<AccountTransferModal>(newScope).openModal(block = true)

                    if (controller.accountExists(newTransfer.item.accountID)) {
                        println("The account does exist")

                        if (controller.withdraw(controller.selectedAccount.id.value, newTransfer.item.amount)) {
                            println("You can transfer the amount")

                            controller.deposit(newTransfer.item.accountID, newTransfer.item.amount)
                        }
                    }

                    println("Account: ${newTransfer.item.accountID}")
                    println("Amount: ${newTransfer.item.amount}")
                }
            }
        }
    }
}