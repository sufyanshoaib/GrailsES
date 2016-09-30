package com.sufyan.demo


import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class Event {
    String title
    String description

    static searchable = {
        title index:'analyzed', boost:4
        description index:'analyzed', boost:3
    }
}