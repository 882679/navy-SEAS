package com.example.navyseas.database;

import com.example.navyseas.database.Model.Family;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TestFamilies {
	public static void main(String[] args) throws MongoException {
		MongoDatabase database = DatabaseUtility.createConnection();

		MongoCollection<Family> family = database.getCollection("Family", Family.class);

		Family f = family.find().first();
		System.out.println(f);
	}
}
