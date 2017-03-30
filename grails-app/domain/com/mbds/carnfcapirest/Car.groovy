package com.mbds.carnfcapirest

import grails.rest.Resource

@Resource
class Car {

    String matricule
    String marque
    Date nextMaintenance
    Date endInsurance
    Place place

    static belongsTo = [person: Person]

    static constraints = {
        matricule           nullable: false, blank: false, unique: true
        marque              nullable: false, blank: false
        nextMaintenance     nullable: false, blank: false
        endInsurance        nullable: false, blank: false
    }
}
