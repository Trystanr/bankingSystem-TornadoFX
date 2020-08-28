package com.example.demo.view

import com.example.demo.model.DepositModel
import tornadofx.*

class SingleValueModal: Fragment("Modal") {
    private val dep: DepositModel by inject()

    override val root = form {
        fieldset {

            field("Value") {
                textfield {
                    bind(dep.amount)
                }
            }

            button("Accept") {
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