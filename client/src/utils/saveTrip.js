export function SaveTrip(tripName, jsonText) {
    // Function to convert JSON text to KML format
    function jsonToKML(jsonText) {
        const jsonObj = JSON.parse(jsonText);
        let kmlText = '<?xml version="1.0" encoding="UTF-8"?>\n<kml xmlns="http://www.opengis.net/kml/2.2">\n<Document>\n';
        
        jsonObj.places.forEach(place => {
            kmlText += '    <Placemark>\n';
            kmlText += `        <name>${place.defaultDisplayName || place.name || 'Unnamed Place'}</name>\n`;
            if (place.description) {
                kmlText += `        <description>${place.description}</description>\n`;
            }
            kmlText += '        <Point>\n';
            kmlText += `            <coordinates>${place.longitude},${place.latitude}</coordinates>\n`;
            kmlText += '        </Point>\n';
            kmlText += '    </Placemark>\n';
        });

        kmlText += '</Document>\n</kml>';
        return kmlText;
    }

    // Convert the JSON text to KML
    const kmlText = jsonToKML(jsonText);

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
