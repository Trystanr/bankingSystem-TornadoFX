package com.example.demo.view

import com.example.demo.controller.ClientController
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
    }
}