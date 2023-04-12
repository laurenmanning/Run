function byRoomNumber() {
    let userInput = document.querySelector("#roomNumber").value;
    var modal = document.getElementById("directionsModal");
    const mapModal = bootstrap.Modal.getOrCreateInstance(modal);
    let img = document.getElementById("map-img");
    let mapTitle = document.getElementById("map-modal-title");
    let mapSubtitle = document.getElementById("map-modal-subtitle");

    var noDirectionsModal = document.getElementById("noDirectionsFoundModal");
    const noDirectionsMsgModal = bootstrap.Modal.getOrCreateInstance(noDirectionsModal);


    if (userInput == "0324") {
        img.src = "images/directionsTo0324.png";
        mapTitle.innerHTML = "Directions to 0324";
        mapSubtitle.innerHTML = "Remain on ground floor, follow path on map";   
        mapModal.show();      
    } else if (userInput == "0318") {
        img.src = "images/directionsTo0318.png";
        mapTitle.innerHTML = "Directions to 0318";
        mapSubtitle.innerHTML = "Remain on ground floor, follow path on map";     
        mapModal.show();       
    } else if (userInput == "1210") {
        img.src = "images/directionsTo1210.png";
        mapTitle.innerHTML = "Directions to 1210";
        mapSubtitle.innerHTML = "Take elevator to 1st floor, follow path on map";  
        mapModal.show();          
    } else if (userInput == "1207") {
        img.src = "images/directionsTo1207.png";
        mapTitle.innerHTML = "Directions to 1207";
        mapSubtitle.innerHTML = "Take elevator to 1st floor, follow path on map";  
        mapModal.show();          
    } else {
        noDirectionsMsgModal.show()
    }

    //modal.style.display = "block";
    // mapModal.show();
}

function closeMap () {
    var modal = document.getElementById("directionsModal");
    const mapModal = bootstrap.Modal.getOrCreateInstance(modal);
    //modal.style.display = "none";
    mapModal.hide();
}

function closeNoDirections () {
    var modal = document.getElementById("noDirectionsFoundModal");
    const noDirectionsModal = bootstrap.Modal.getOrCreateInstance(modal);
    //modal.style.display = "none";
    noDirectionsModal.hide();
}

function byFacultyName() {
    let firstName = document.querySelector("#facultyFirstName").value;
    let lastName = document.querySelector("#facultyLastName").value;
    var modal = document.getElementById("directionsModal");
    const mapModal = bootstrap.Modal.getOrCreateInstance(modal);
    let img = document.getElementById("map-img");
    let mapTitle = document.getElementById("map-modal-title");

    var noDirectionsModal = document.getElementById("noDirectionsFoundModal");
    const noDirectionsMsgModal = bootstrap.Modal.getOrCreateInstance(noDirectionsModal);

    if ((firstName == "Evan" && lastName == "Golub") || (firstName == "" && lastName == "Golub")) {
        img.src = "images/directionsTo1210.png"; 
        mapTitle.innerHTML = "Directions to 1210";
        mapModal.show();
    } else {
        noDirectionsMsgModal.show()
    }
    //modal.style.display = "block";
    
}

function byClass() {
    let course = document.querySelector("#course").value;
    let section = document.querySelector("#section").value;

    var modal = document.getElementById("directionsModal");
    const mapModal = bootstrap.Modal.getOrCreateInstance(modal);
    let img = document.getElementById("map-img");
    let mapTitle = document.getElementById("map-modal-title");
    var noDirectionsModal = document.getElementById("noDirectionsFoundModal");
    const noDirectionsMsgModal = bootstrap.Modal.getOrCreateInstance(noDirectionsModal);

    if (course == "CMSC434" && (section == "0101" || section == "0201")) {
        img.src = "images/directionsTo1207.png";
        mapTitle.innerHTML = "Directions to 1207"; 
        mapModal.show();
    } else {
        noDirectionsMsgModal.show();
    }

    //modal.style.display = "block";
    
}


function confirmReservation() {
    let date = document.getElementById("date").value;
    let time = document.getElementById("time").value;
    let firstName = document.getElementById("FirstName").value;
    let lastName = document.getElementById("LastName").value;
    let uid = document.getElementById("UID").value;
    let room = document.getElementById("room").value;

    document.getElementById("resName").value = firstName + " " + lastName;
    document.getElementById("resID").value = uid;
    document.getElementById("resDate").value = date;
    document.getElementById("resTime").value = time;
    document.getElementById("resRoom").value = room;

    var modal = document.getElementById("huddleroomModal");
    const mapModal = bootstrap.Modal.getOrCreateInstance(modal);
    mapModal.show();

}

function closeReservation () {
    var modal = document.getElementById("huddleroomModal");
    const huddleroomModal = bootstrap.Modal.getOrCreateInstance(modal);
    //modal.style.display = "none";
    huddleroomModal.hide();
}