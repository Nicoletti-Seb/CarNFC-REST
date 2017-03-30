package com.mbds.carnfcapirest

import grails.converters.JSON
import grails.converters.XML

class CarController {

    def index() {
        render "CarController"
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
                def cars = Car.getAll();
                renderApplication(cars)

                break;
            default:
                render(status: 405, text: "Allow : GET")
                break;
        }
    }

    def get() {
        switch (request.getMethod()) {
            case "GET":
                def carInstance = Car.get(params.id)

                if (!carInstance) {
                    render(status: 404, text: "car not found")
                    return
                }
                renderApplication(carInstance)

                break;
            default:
                render(status: 405, text: "Allow : GET")
                break;
        }
    }

    def add() {
        switch (request.getMethod()) {
            case "POST":
                if (!request.getContentType().contains("json")) {
                    render(status: 400, text: "json only accepted")
                    return
                }

                System.out.println("params:" + request.JSON)

                def carInstance = new Car(request.JSON)
                carInstance.setProperty("place", new Place())

                if (carInstance.save(flush: true)) {
                    response.status = 201
                    renderApplication(carInstance)
                } else {
                    render(status: 400, text: "car has not been created")
                    carInstance.errors.allErrors.each { println it }
                }

                break;
            default:
                render(status: 405, text: "Allow : POST")
                break;
        }
    }

    def update() {
        switch (request.getMethod()) {
            case "PUT":
                if (!request.getContentType().contains("json")) {
                    render(status: 400, text: "json only accepted")
                    return
                }

                def carInstance = Car.get(params.id)

                if (!carInstance) {
                    render(status: 404, text: "car id not found")
                    return
                }

                System.out.println("params:" + request.JSON)
                carInstance.setProperties(request.JSON)

                if (carInstance.save(flush: true)) {
                    response.status = 200
                    renderApplication(carInstance)
                } else {
                    render(status: 400, text: "car has not been updated")
                    carInstance.errors.allErrors.each { println it }
                }

                break;
            default:
                render(status: 405, text: "Allow : PUT")
                break;
        }
    }

    def delete() {
        switch (request.getMethod()) {
            case "DELETE":
                def carInstance = Car.get(params.id)

                if (!carInstance) {
                    render(status: 404, text: "car id not found")
                    return
                }

                carInstance.delete()

                if(!Car.get(params.id))
                    response.status = 204
                else {
                    render(status: 400, text: "car has not been deleted")
                    carInstance.errors.allErrors.each { println it }
                }

                break;
            default:
                render(status: 405, text: "Allow : DELETE")
                break;
        }
    }
}
