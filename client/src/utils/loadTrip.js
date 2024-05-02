import * as tripFileSchema from '../../schemas/TripFile.json';
import { isJsonResponseValid } from './restfulAPI';
import { Place } from '@models/place.model';


export async function LoadPlaces(props, foundTrip) {
    console.log("Loading places for received data");
    if (isValidJsonFile(foundTrip)) {
        console.log("Valid JSON file detected");
        LoadJsonFile(props, foundTrip);
    } else if (isValidKmlFile(foundTrip)) { // Assumes a new function to validate KML before parsing
        console.log("Valid KML file detected");
        LoadKmlFile(props, foundTrip);
    } else {
        console.log("File is neither valid JSON nor KML");
        TripLoadErrorMessage(props, "Unsupported file type or corrupt file.");
    }
}


function isValidKmlFile(kmlString) {
    try {
        const result = new DOMParser().parseFromString(kmlString, "text/xml");
        const isValid = result && result.querySelector("kml Document Placemark");
        console.log("KML Validation Result:", isValid);
        return isValid;
    } catch (error) {
        console.error("Error parsing KML:", error);
        return false;
    }
}


export function LoadKmlFile(props, kmlString) {
    console.log("Parsing KML Data");
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(kmlString, "text/xml");
    const errors = xmlDoc.getElementsByTagName("parsererror");

    if (errors.length > 0) {
        console.error("XML Parsing errors:", errors);
        props.setShowValidityIcon(true);
        props.setFileValidity(false);
        return;
    }

    const placemarks = xmlDoc.getElementsByTagName("Placemark");
    console.log("Total placemarks found:", placemarks.length);
    const places = Array.from(placemarks).map(placemark => {
        const nameNode = placemark.getElementsByTagName("name")[0];
        const name = nameNode ? nameNode.textContent : "Unknown Name";
        const coordinates = placemark.getElementsByTagName("coordinates")[0]?.textContent.split(',') || [];
        const latitude = parseFloat(coordinates[1]).toFixed(2);
        const longitude = parseFloat(coordinates[0]).toFixed(2);

        return {
            name: name,
            latitude: latitude,
            longitude: longitude,
            formatPlace: function() {
                return `${this.name},${this.latitude}°N, ${this.longitude}°W`;
            }
        };
    });

    console.log("Loaded Places from KML:", places);
    if (places.length > 0) {
        props.setLoadedPlace(places);
        props.setShowValidityIcon(true);
        props.setFileValidity(true);
        props.setDisallowLoad(false);
    } else {
        props.setShowValidityIcon(true);
        props.setFileValidity(false);
    }
}


function isValidJsonFile(tripString){
    try {
        const tripObject = JSON.parse(tripString);
        const isValid = isJsonResponseValid(tripObject, tripFileSchema);
        console.log("JSON Validation Result:", isValid);
        return isValid;
    } catch (error) {
        console.error("Error parsing JSON:", error);
        return false;
    }
}


export function LoadJsonFile(props, tripString){
    console.log("Parsing JSON Data");
    const tripObject = JSON.parse(tripString);
    const places = makeJsonPlacesList(tripObject);
    console.log("Loaded Places from JSON:", places);
    TripLoadValidMessage({places}, props);
}


function makeJsonPlacesList(tripObject){
    var places = [];
    for(let index in tripObject.places){
        var place = tripObject.places[index];
        places.push(new Place(place));
    }


    return places;
}


function TripLoadErrorMessage(props, message){
    console.error("Load Error:", message);
    props.setDisallowLoad(true);
    props.setShowValidityIcon(true);
    props.setFileValidity(false);
}


function TripLoadValidMessage({places}, props){
    console.log("Places loaded successfully:", places);
    props.setLoadedPlace(places);
    props.setShowValidityIcon(true);
    props.setFileValidity(true);
    props.setDisallowLoad(false);
}
