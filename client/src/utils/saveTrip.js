export function SaveTrip(tripName, jsonText) {
    // Parse the JSON text to an object
    const jsonObj = JSON.parse(jsonText);

    // Convert the JSON object to KML format
    let kmlText = '<?xml version="1.0" encoding="UTF-8"?>\n<kml xmlns="http://www.opengis.net/kml/2.2">\n<Document>\n';
    jsonObj.places.forEach(place => {
        kmlText += '    <Placemark>\n';
        kmlText += `        <name>${place.defaultDisplayName || 'Unnamed Place'}</name>\n`;
        kmlText += '        <Point>\n';
        kmlText += `            <coordinates>${place.longitude},${place.latitude},0</coordinates>\n`;
        kmlText += '        </Point>\n';
        kmlText += '    </Placemark>\n';
    });
    kmlText += '</Document>\n</kml>';

    // Create a Blob from the KML text
    const file = new Blob([kmlText], { type: "application/vnd.google-earth.kml+xml" });
    const link = document.createElement("a");
    const url = URL.createObjectURL(file);
    link.href = url;
    link.download = tripName + ".kml";
    document.body.appendChild(link);
    link.click();

    setTimeout(function() {
        document.body.removeChild(link);
        window.URL.revokeObjectURL(url);
    }, 0);
}
