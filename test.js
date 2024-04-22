const { MongoClient, ServerApiVersion } = require('mongodb');
const uri = "mongodb+srv://user:pass@cluster0.ttxn2hf.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
// Create a MongoClient with a MongoClientOptions object to set the Stable API version

const databaseAndCollection = {db: "Run", collection:"SavedRuns"};

const client = new MongoClient(uri, {
  serverApi: {
    version: ServerApiVersion.v1,
    strict: true,
    deprecationErrors: true,
  }
});

const express = require('express');
const bodyParser = require("body-parser");
const app = express();
const path = require("path");

const portNumber = 8080;

app.set('views', 'templates');
app.set("view engine", "ejs");

app.listen(portNumber, (err) => {
  if (err) {
   console.log("Starting server failed.");
   } else {
       console.log(`Web server is running at http://localhost:${portNumber} \n`);
   }
});

app.get("/", (request, response) => {
  response.render('first.ejs');
});


async function run() {
  try {
    // Connect the client to the server	(optional starting in v4.7)
    await client.connect();
    // Send a ping to confirm a successful connection
    let run = {
        name: "Lauren",
        test: "one"
    }

    insertRun(client, databaseAndCollection, run);
    await client.db("admin").command({ ping: 1 });
    console.log("Pinged your deployment. You successfully connected to MongoDB!");

  } finally {
    // Ensures that the client will close when you finish/error
    await client.close();
  }
}
run().catch(console.dir);

async function insertRun(client, databaseAndCollection, newRun) {
    //client.connect();
    await client.db(databaseAndCollection.db).collection(databaseAndCollection.collection).insertOne(newRun);
    //client.close();
}

function displayRun() {
    let table = "<table> <table border=\"1\"> <tr> <th> Pace </th> <th> Duration </th> </tr>";

    for (let [pace, time] of runMap) {
        table += "<tr> <td>" + pace + "</td> <td>" + time + "</td> </tr>";
    }

    table += "</table>";
    return table;
}

function addInterval() {
    document.getElementById("indicator").innerHTML = "after";
    let time = document.getElementById("interval").value;
    
    let pace = document.getElementById("pace").value;
    
    //totalRunTime += parseInt(time);
    document.getElementById("indicator").innerHTML = "after3";
    try {
      x.set(time, pace);
    } catch (ex) {
      document.getElementById("indicator").innerHTML = ex.toString();
    }
    
    
    
    let displayRunString = displayRun();

    document.getElementById("displayRun").innerHTML = displayRunString;
}


class completeRun{
  constructor() {
    this.name = null;
    this.style = null;
    this.duration = null;
    this.breakdown = new Map();
  }
}


function addField() {
  let start = "<form onsubmit=\"getTotal()\">";
  let end = "</form>";
  let toAdd = document.createElement("p");
  toAdd.innerHTML = "<input tagName=\"intervals\" type=\"text\" id=\"interval\"><select name=\"optionlist\" id=\"pace\" required> <option value=\"\" disabled selected >Select a pace</option> <option>base</option> <option>base-push</option> <option>push</option> <option>push-all out</option> <option>all out</option> </select></br>";
  document.getElementById("formBody").appendChild(toAdd);

 // let newForm = start + existing + toAdd + end;
 // document.getElementById("formBody").innerHTML = newForm;

}

function addIntervalDynamic(){
  let newIntervalTime = document.createElement("input");
  newIntervalTime.type = "text";
  newIntervalTime.id="time"
  document.getElementById("container").appendChild(newIntervalTime);

  let newIntervalPace = document.createElement("select");
  newIntervalPace.id = "paces";
  document.getElementById("container").appendChild(newIntervalPace);

  let paces = ["Base", "Base-Push", "Push", "Push-All Out", "All Out"];

  for (let i = 0; i < paces.length; i++) {
    let option = document.createElement("option");
    option.value = paces[i];
    option.text = paces[i];
    newIntervalPace.appendChild(option);
  }

  let br = document.createElement("br");
  document.getElementById("container").appendChild(br);
  
}

function getTotal() {
  document.getElementById("indicator").innerHTML = "here";
  let times = document.querySelectorAll('#container input[id="time"]');
  let timesArray = [];

  let paces = document.querySelectorAll('#container select[id="paces"]');
  let pacesArray = [];

  times.forEach(i => {
    timesArray.push(i.value);
  });

  paces.forEach(i => {
    pacesArray.push(i.value);
  });

  localStorage.setItem("times", timesArray);
  localStorage.setItem("paces", pacesArray);

  let tableTotalString = tableTotal(timesArray, pacesArray);
  document.getElementById("runTable").innerHTML = tableTotalString;
}

function tableTotal(times, paces) {
  let table = "<table> <table border=\"1\"> <tr> <th> Pace </th> <th> Duration </th> </tr>";

    for (i = 0; i < times.length; i++) {
        table += "<tr> <td>" + paces[i] + "</td> <td>" + times[i] + "</td> </tr>";
    }

    table += "</table>";
    return table;
}

function createRunModal() {
  var modal = document.getElementById("myModal");
  modal.style.display = "block";
}

function submitRun() {
  getTotal();
  createRunModal();
}

function closeRunModal(){
  var modal = document.getElementById("myModal");
  modal.style.display = "none";
}


async function saveRun() {
  document.getElementById("modalIndicator").innerHTML = "ready to save";
  let times = localStorage.getItem("times");
  let paces = localStorage.getItem("paces");
  let name = document.getElementById("runName").value;

  document.getElementById("modalIndicator").innerHTML = client;
}