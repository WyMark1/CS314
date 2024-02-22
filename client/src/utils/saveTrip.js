export function SaveTrip(tripName) {
    // Hardcoded JSON for testing
    const jsonText = `{"places":[{"name":"Test Place","latitude":"40.689247","longitude":"-74.044502","defaultDisplayName":"Statue of Liberty National Monument"}]}`;

    // Convert JSON text to KML format
    const jsonObj = JSON.parse(jsonText);
    let kmlText = '<?xml version="1.0" encoding="UTF-8"?>\n<kml xmlns="http://www.opengis.net/kml/2.2">\n<Document>\n';
    jsonObj.places.forEach(place => {
        kmlText += '    <Placemark>\n';
        kmlText += `        <name>${place.defaultDisplayName || place.name}</name>\n`;
        kmlText += '        <Point>\n';
        kmlText += `            <coordinates>${place.longitude},${place.latitude}</coordinates>\n`;
        kmlText += '        </Point>\n';
        kmlText += '    </Placemark>\n';
    });
    kmlText += '</Document>\n</kml>';

    // Log the KML to verify its correctness
    console.log(kmlText);

    // Proceed with the Blob and download
    const file = new Blob([kmlText], { type: "application/vnd.google-earth.kml+xml" });
    const link = document.createElement("a");
    const url = URL.createObjectURL(file);
    link.href = url;
    link.download = tripName + ".kml";
    document.body.appendChild(link);
    link.click();

    setTimeout(() => {
        document.body.removeChild(link);
        window.URL.revokeObjectURL(url);
    }, 0);
}
