export function SaveTrip(tripName, fileText){
    const file = new Blob([fileText], { type: "application/vnd.google-earth.kml+xml" });
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