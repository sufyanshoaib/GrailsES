package com.sufyan.demo

import grails.transaction.Transactional

@Transactional
class EventService {

    def addEvent(Event event) {
        event.save(flush:true)
        event.index()
    }

    def addEvent(String title, String description) {
        Event event = new Event(title:title, description:description)

        //event.startDate = Date.parse("dd/MM/yyyy", startDate)
        //event.endDate = Date.parse("dd/MM/yyyy", endDate)


        addEvent(event)
    }
}
