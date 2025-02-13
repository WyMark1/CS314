export function SaveTrip(tripName, places, format) {
    let fileText;
    let fileType;

    if (format === 'json') {
        console.log("Places data being saved as JSON:", places);
        fileText = JSON.stringify({ places: places });
        fileType = "application/json";
    } else if (format === 'kml') {
        fileText = convertToKml(places);
        fileType = "application/vnd.google-earth.kml+xml";
    }

    const file = new Blob([fileText], { type: fileType });
    const link = document.createElement("a");
    const url = URL.createObjectURL(file);
    link.href = url;
    link.download = tripName + `.${format}`;
    document.body.appendChild(link);
    link.click();

    setTimeout(() => {
        document.body.removeChild(link);
        window.URL.revokeObjectURL(url);
    }, 0);

    console.log("Places data before conversion to KML:", places);
    fileText = convertToKml(places);
}

function convertToKml(places) {
    const kmlHeader = `<?xml version="1.0" encoding="UTF-8"?>
<kml xmlns="http://www.opengis.net/kml/2.2">
<Document>`;
    const kmlFooter = `</Document></kml>`;
    const kmlPlaces = places.map(place => {
        //const safeName = place.name || place.streetAddress || place.municipality;
        var safeName;
        var s = "";
        if(place.name != undefined &&  place.name !=null){
            s = place.name.toString();
        }
        if(s.includes("&")){
            safeName = place.municipality;
        }
        else{
        safeName = place.name || place.streetAddress || place.municipality;
        }
        return `
<Placemark>
    <name>${safeName}</name>
    <Point>
        <coordinates>${place.longitude},${place.latitude}</coordinates>
    </Point>
</Placemark>`;
    }).join('');
    return kmlHeader + kmlPlaces + kmlFooter;
}
