package com.mbds.carnfcapirest

import grails.rest.Resource

@Resource
class Car {

    String matricule
    String marque
    Date nextMaintenance
    Date endInsurance

    static hasOne = [place: Place]

    static belongsTo = [person: Person]

    /*static constraints = {
        matricule           unique: true
        marque              nullable: false, blank: false
        nextMaintenance     nullable: false, blank: false
        endInsurance        nullable: false, blank: false
    }*/
}
