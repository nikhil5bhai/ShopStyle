/**
 * Using any language of your choice write a script that does the following:
 * Call http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=1
 * To go to next page of response you have to increment the page number in the above url. 
 * As long as the "more" field returns true, you have more data available.
 * The response is a JSON object which has a response key which is an array of more JSON objects. 
 * Each of them has a key called flags and within flags there is a key called hd.
 * Print out how many response objects have flags:hd set to true and how many have hd set to false.
 */

package com.ShopStype.tests;

import org.junit.Test;
import com.jayway.restassured.response.Response;
import org.json.JSONObject;
import static com.jayway.restassured.RestAssured.*;

public class ShopStyleGet {
	int jmax = 100; // jmax is maximum number of page starting from 1 which can be edited by user

	@Test
	public void hdTrue() {

		for (int j = 1; j <= jmax; j++) {
			String apiAdd = "http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=";
			String apiAddWithPage = apiAdd + j;
			Response response = given().when().get(apiAddWithPage);
			JSONObject jsonObject = new JSONObject(response.asString());

			int totalHDTrue = 0;
			for (int i = 0; i < jsonObject.getJSONArray("response").length(); i++) {
				JSONObject flagParent = jsonObject.getJSONArray("response").getJSONObject(i);
				if (flagParent.getJSONObject("flags").getBoolean("hd") == true)
					totalHDTrue++;
				// System.out.println("Flag HD for index " + i + " = "+
				// flagParent.getJSONObject("flags").getBoolean("hd"));
			}

			System.out.println(apiAddWithPage);
			System.out.println("Total HD True Count = " + totalHDTrue);

		}
	}

	@Test
	public void hdFalse() {

		for (int j = 1; j <= jmax; j++) {
			String apiAdd = "http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=";
			String apiAddWithPage = apiAdd + j;
			Response response = given().when().get(apiAddWithPage);
			JSONObject jsonObject = new JSONObject(response.asString());

			int totalHDFalse = 0;
			for (int i = 0; i < jsonObject.getJSONArray("response").length(); i++) {
				JSONObject flagParent = jsonObject.getJSONArray("response").getJSONObject(i);
				if (flagParent.getJSONObject("flags").getBoolean("hd") == false)
					totalHDFalse++;
				// System.out.println("Flag HD for index " + i + " = "+
				// flagParent.getJSONObject("flags").getBoolean("hd"));
			}
			System.out.println(apiAddWithPage);
			System.out.println("Total HD False Count = " + totalHDFalse);
		}
	}
}

// This part was developed with help of my friend further redeveloped by me as above

// for(int i=0; i<jsonObject.getJSONArray("response").length(); i++) {
// JSONObject flagParent = jsonObject.getJSONArray("response").getJSONObject(i);
// if(flagParent.getJSONObject("flags").getBoolean("hd"))
// totalHDTrue++;
// //System.out.println("Flag HD for index " + i + " = "+
// flagParent.getJSONObject("flags").getBoolean("hd"));
// }
