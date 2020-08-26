package com.example.demo.view

import com.example.demo.model.TransferModel
import tornadofx.*


class AccountTransferModal: Fragment("Transfer") {
    val trans: TransferModel by inject()

    override val root = form {
        fieldset {

            field("Account ID") {
                textfield {
                    bind(trans.accountID)
                }
            }

            field("Value") {
                textfield {
                    bind(trans.amount)
                }
            }

            button("Accept") {
                isDefaultButton = true
                action {
                    trans.commit {
                        close()
                    }
                }
            }

        }
    }
}