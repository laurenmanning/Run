const { MongoClient, ServerApiVersion } = require('mongodb');
const uri = "mongodb+srv://lemanning3:cmsc3351@cluster0.ttxn2hf.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
// Create a MongoClient with a MongoClientOptions object to set the Stable API version
const client = new MongoClient(uri, {
  serverApi: {
    version: ServerApiVersion.v1,
    strict: true,
    deprecationErrors: true,
  }
});

client.connect();

function x() {
    document.getElementById("lauren").innerHTML = "here";
    let run = {
        name: "first run",
        paces: ["base", "push", "all-out"],
        times: [30, 15, 10],
        secret: "lee",
    }
    insertRun(client, databaseAndCollection, run);
}

async function insertRun(client, databaseAndCollection, newRun) {
    //client.connect();
    await client.db(databaseAndCollection.db).collection(databaseAndCollection.collection).insertOne(newRun);
    //client.close();
}


async function findRun(client, databaseAndCollection, runName) {
    //client.connect();
    let filter = {name: runName};
    const result = await client.db(databaseAndCollection.db)
                          .collection(databaseAndCollection.collection)
                          .findOne(filter);
    //client.close();
    return result;
}

function y () {
    let run = findRun(client, databaseAndCollection, "first run");
    document.getElementById("lauren").innerHTML = run.secret;
}




const databaseAndCollection = {db: "Run", collection:"SavedRuns"};