package com.sufyan.demo


import grails.rest.*
import grails.converters.*

class EventController extends RestfulController {


    def eventService
    def elasticSearchService

    static responseFormats = ['json', 'xml']

    EventController() {
        super(Event)
    }

    /**
     * Add And Event to index
     * @param title
     * @param description
     * @param latitude
     * @param longitude
     * @param startDate
     * @param endDate
     * @return response in JSON
     */
    def add(String title, String description) {

        eventService.addEvent(title, description)

        def resp = ['success':true]

        render resp as JSON
    }

    def index(String query){
        def queryClosure = query ?
                {
                    query_string(fields: ["title", "description"],
                            query: query)
                } : null

        def events = elasticSearchService.search(queryClosure)
        render(view: "/event/list", model: [events:events.searchResults])
    }

    /**
     * Perform  search on query.
     * @param query
     * @return
     */
    def search(String query) {

        def events = elasticSearchService.search( [:],
                {
                    query_string(fields: ["title", "description"],
                            query: query)
                }, null as Closure)
        render events as JSON
    }


    /**
     * Perform search along with distance search.
     * @param query
     * @param distance
     * @return
     */
    def searchWithDistance(String query, Integer distance) {

        Closure filter = {
            geo_distance(
                    'distance': (distance ?: '0') + 'km',
                    'location': [lat: 42.23, lon: 0.11]
            )
        }

        def events = elasticSearchService.search( [:],

                {
                    query_string(fields: ["title", "description"],
                            query: query)
                },
                distance ? filter : null as Closure)
        render events as JSON
    }


}