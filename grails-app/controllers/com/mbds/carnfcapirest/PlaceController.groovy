package com.mbds.carnfcapirest

import grails.converters.JSON
import grails.converters.XML

class PlaceController {

    def index() {
        render "PlaceController"
    }

    void renderApplication(obj) {
        if(request.getHeader("Accept").contains("xml")) {
            render obj as XML
        } else if(request.getHeader("Accept").contains("json")) {
            render obj as JSON
        } else {
            render obj as JSON
        }
    }

    def list() {
        switch (request.getMethod()) {
            case "GET":
                def places = Place.getAll();
                renderApplication(places)

                break;
            default:
                render(status: 405, text: "Allow : GET")
                break;
        }
    }

    def get() {
        switch (request.getMethod()) {
            case "GET":
                def placeInstance = Place.get(params.id)

                if (!placeInstance) {
                    render(status: 404, text: "place not found")
                    return
                }
                renderApplication(placeInstance)

                break;
            default:
                render(status: 405, text: "Allow : GET")
                break;
        }
    }

    /*def add() {
        switch (request.getMethod()) {
            case "POST":
                if (!request.getContentType().contains("json")) {
                    render(status: 400, text: "json only accepted")
                    return
                }

                System.out.println("params:" + request.JSON)

                def placeInstance = new Place(request.JSON)

                if (placeInstance.save(flush: true)) {
                    response.status = 201
                    renderApplication(placeInstance)
                } else {
                    render(status: 400, text: "place has not been created")
                    placeInstance.errors.allErrors.each { println it }
                }

                break;
            default:
                render(status: 405, text: "Allow : POST")
                break;
        }
    }*/

    def update() {
        switch (request.getMethod()) {
            case "PUT":
                if (!request.getContentType().contains("json")) {
                    render(status: 400, text: "json only accepted")
                    return
                }

                def placeInstance = Place.get(params.id)

                if (!placeInstance) {
                    render(status: 404, text: "place id not found")
                    return
                }

                System.out.println("params:" + request.JSON)
                placeInstance.setProperties(request.JSON)

                if (placeInstance.save(flush: true)) {
                    response.status = 200
                    renderApplication(placeInstance)
                } else {
                    render(status: 400, text: "place has not been updated")
                    placeInstance.errors.allErrors.each { println it }
                }

                break;
            default:
                render(status: 405, text: "Allow : PUT")
                break;
        }
    }

    /*def delete() {
        switch (request.getMethod()) {
            case "DELETE":
                def placeInstance = Place.get(params.id)

                if (!placeInstance) {
                    render(status: 404, text: "place id not found")
                    return
                }

                placeInstance.delete()

                if(!Place.get(params.id))
                    response.status = 204
                else {
                    render(status: 400, text: "place has not been deleted")
                    placeInstance.errors.allErrors.each { println it }
                }

                break;
            default:
                render(status: 405, text: "Allow : DELETE")
                break;
        }
    }*/
}
