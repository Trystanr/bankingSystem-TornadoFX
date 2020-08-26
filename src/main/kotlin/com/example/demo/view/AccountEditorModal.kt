package com.example.demo.view

import com.example.demo.model.AccountModel
import javafx.collections.FXCollections
import tornadofx.*
import java.text.DecimalFormat

class AccountEditorModal : Fragment("Add Customer") {
    val account: AccountModel by inject()

    override val root = form {
        fieldset {

            field("ID") {
                label {
                    bind(account.id)
                }
            }

            val accountTypes = FXCollections.observableArrayList(
                    "Gold Cheque",
                    "Diamond Cheque",
                    "Tax Free Savings",
                    "Easy Access Savings"
            )

            field("Account Type") {
                combobox(account.type, accountTypes)
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