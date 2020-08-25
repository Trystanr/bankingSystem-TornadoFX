package com.example.demo.view

import com.example.demo.controller.ClientController
import tornadofx.*

class MainView : View("Banking System") {
    private val controller: ClientController by inject()

    override val root = hbox {
        add<ClientList>()
        add<PersonEditor>()
    }
}