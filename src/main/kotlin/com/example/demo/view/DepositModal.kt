package com.example.demo.view

import com.example.demo.model.DepositModel
import tornadofx.*

class DepositModal: Fragment("Deposit") {
    val dep: DepositModel by inject()

    override val root = form {
        fieldset {

            field("Value") {
                textfield {
                    bind(dep.amount)
                }
            }

            button("Deposit") {
                isDefaultButton = true
                action {
                    dep.commit {
                        close()
                    }
                }
            }

        }
    }
}