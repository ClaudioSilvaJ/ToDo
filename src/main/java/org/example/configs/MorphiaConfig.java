package org.example.configs;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.mongodb.MongoClient;

public class MorphiaConfig {
    final Datastore datastore;

    public MorphiaConfig(String databaseName) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        Morphia morphia = new Morphia();
        morphia.mapPackage("org.example.classes");
        datastore = morphia.createDatastore(mongoClient, databaseName);
        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        return datastore;
    }
}

