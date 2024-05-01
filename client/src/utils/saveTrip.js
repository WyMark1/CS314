export function SaveTrip(tripName, places, format) {
    let fileText;
    let fileType;

    if (format === 'json') {
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
}

function convertToKml(places) {
    // Convert places JSON to KML format. Simplified example:
    const kmlHeader = `<?xml version="1.0" encoding="UTF-8"?>
<kml xmlns="http://www.opengis.net/kml/2.2">
<Document>`;
    const kmlFooter = `</Document></kml>`;
    const kmlPlaces = places.map(place => `
<Placemark>
    <name>${place.name}</name>
    <Point>
        <coordinates>${place.longitude},${place.latitude}</coordinates>
    </Point>
</Placemark>`).join('');
    return kmlHeader + kmlPlaces + kmlFooter;
}