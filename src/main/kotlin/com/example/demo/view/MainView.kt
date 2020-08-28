package com.example.demo.view

import com.example.demo.controller.ClientController
import tornadofx.*

class MainView : View("Banking System") {
    override val root = hbox {
        add<ClientList>()
        add<ClientEditor>()
    }
}