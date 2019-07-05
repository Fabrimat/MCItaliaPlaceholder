package me.fabrimat.mcitaplaceholder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class McItaServer {
	private String serverName;
	
	private String status;
	private String message;
	private Integer id;
	private String serverid;
	private String title;
	private String address;
	private Integer position;
	private Integer votes;
	private Integer votes_today;
	private String description;
	private String version;
	private Boolean online;
	private Integer players;
	private Integer max_players;
	private Integer slot;
	
	public McItaServer(String name) {
		this.serverName = name;
		this.check();
	}
	
	private void check() {
		JsonObject obj = new JsonObject();
		try {
			obj = (JsonObject) new JsonParser().parse(httpsRequest());
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		try {
			this.status = obj.getAsJsonPrimitive("status").getAsString();
		} catch (Exception  e) {
			this.status = "";
		}
		try {
			this.message = obj.getAsJsonPrimitive("message").getAsString();
		} catch (Exception  e) {
			this.message = "";
		}
		try {
			this.id = obj.getAsJsonPrimitive("id").getAsInt();
		} catch (Exception  e) {
			this.id = 0;
		}
		try {
			this.serverid = obj.getAsJsonPrimitive("serverid").getAsString();
		} catch (Exception  e) {
			this.serverid = "";
		}
		try {
			this.title = obj.getAsJsonPrimitive("title").getAsString();
		} catch (Exception  e) {
			this.title = "";
		}
		try {
			this.address = obj.getAsJsonPrimitive("address").getAsString();
		} catch (Exception  e) {
			this.address = "";
		}
		try {
			this.position = obj.getAsJsonPrimitive("position").getAsInt();
		} catch (Exception  e) {
			this.position = 0;
		}
		try {
			this.votes = obj.getAsJsonPrimitive("votes").getAsInt();
		} catch (Exception  e) {
			this.votes = 0;
		}
		try {
			this.votes_today = obj.getAsJsonPrimitive("votes_today").getAsInt();
		} catch (Exception  e) {
			this.votes_today = 0;
		}
		try {
			this.description = obj.getAsJsonPrimitive("description").getAsString();
		} catch (Exception  e) {
			this.description = "";
		}
		try {
			this.version = obj.getAsJsonPrimitive("version").getAsString();
		} catch (Exception  e) {
			this.version = "";
		}
		try {
			this.online = obj.getAsJsonPrimitive("online").getAsBoolean();
		} catch (Exception  e) {
			this.online = false;
		}
		try {
			this.players = obj.getAsJsonPrimitive("players").getAsInt();
		} catch (Exception  e) {
			this.players = 0;
		}
		try {
			this.max_players = obj.getAsJsonPrimitive("max_players").getAsInt();
		} catch (Exception  e) {
			this.max_players = 0;
		}
		try {
			this.slot = obj.getAsJsonPrimitive("slot").getAsInt();
		} catch (Exception  e) {
			this.slot = 0;
		}
	}
	
	public String getStatus() {
		return this.status;
	}
	public String getMessage() {
		return this.message;
	}
	public Integer getId() {
		return this.id;
	}
	public String getServerid() {
		return this.serverid;
	}
	public String getTitle() {
		return this.title;
	}
	public String getAddress() {
		return this.address;
	}
	public Integer getPosition() {
		return this.position;
	}
	public Integer getVotes() {
		return this.votes;
	}
	public Integer getVotes_today() {
		return this.votes_today;
	}
	public String getDescription() {
		return this.description;
	}
	public String getVersion() {
		return this.version;
	}
	public Boolean getOnline() {
		return this.online;
	}
	public Integer getPlayers() {
		return this.players;
	}
	public Integer getMax_players() {
		return this.max_players;
	}
	public Integer getSlot() {
		return this.slot;
	}
	
	private String httpsRequest() {
		try {
			URL url = new URL("https://www.minecraft-italia.it/api/server-info/" + serverName);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output = "";
			String out;
			while ((out = br.readLine()) != null) {
				output += out;
			};
			br.close();
			return output;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {}
		return "{}";
	}
}
