const main_button = document.getElementById("main_button");
const main_form = document.getElementById("main_form");
const main_form_text = document.getElementById("main_form");

let locations = []
get_initial_locations()

const original_coordinates = locations.length > 0 ? locations[0].coordinates : [0., 0.];

const map = L.map('map').setView(original_coordinates, 4);
const tileLayer = L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    zoom: 4,
    maxZoom: 18,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);

let interressant_locations = L.layerGroup().addTo(map);

async function get_initial_locations() {
    try {
        let initial_locations = await fetch("/locations");
        initial_locations = await initial_locations.json();
        initial_locations.locations.forEach(initial_location => {
            const marker = L.marker([initial_location.lat_coord, initial_location.lon_coord]).bindPopup(`<b>${initial_location.name}</b><br>${initial_location.description}`);
            interressant_locations.addLayer(marker);
            locations.push(initial_location)
        });
    } catch (e) {
        console.log("Problem fetching initial data");
    }
}

async function fetch_new_location(new_word) {
    try {
        let new_location = await fetch(`/new_location?new_word=${new_word}`);
        new_location = await new_location.json();
        return new_location.location;
    } catch (e) {
        console.log("Problem fetching initial data");
    }
}

async function get_new_location(new_word) {
    const new_location = await fetch_new_location(new_word);
    locations.push(new_location);
    const marker = L.marker([new_location.lat_coord, new_location.lon_coord]).bindPopup(`<b>${new_location.name}</b><br>${new_location.description}`);
    interressant_locations.addLayer(marker);
    map.panTo(new L.LatLng(new_location.lat_coord, new_location.lon_coord))
}

main_form.addEventListener("submit", (e) => {
    e.preventDefault();
    const form_data = new FormData(main_form);
    const new_word = form_data.get("new_word");
    main_form.reset();
    get_new_location(new_word);
})


