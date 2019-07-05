package me.fabrimat.mcitaplaceholder;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class McItaPlaceholder extends PlaceholderExpansion  {
	
	enum PlaceholderType {
		NULL("null"),
		STATUS("status"),
		MESSAGE("message"),
		ID("id"),
		SERVERID("serverid"),
		TITLE("title"),
		ADDRESS("address"),
		POSITION("position"),
		VOTES_TODAY("votes_today"),
		VOTES("votes"),
		DESCRIPTION("description"),
		VERSION("version"),
		ONLINE("online"),
		PLAYERS("players"),
		MAX_PLAYERS("max_players"),
		SLOT("slot");
		
		private String identifier;
		
		private PlaceholderType(String identifier) {
			this.identifier = identifier;
		}
		
		public String getIdentifier() {
			return this.identifier;
		}
		
		public String getRawIdentifier() {
			return this.getIdentifier() + "_";
		}
	}
	
    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return "Fabrimat";
    }

    @Override
    public String getIdentifier(){
        return "mcita";
    }

    @Override
    public String getVersion(){
        return "1.0.0";
    }
    
    @Override
    public String onPlaceholderRequest(Player player, String identifier){
    	PlaceholderType type = PlaceholderType.NULL;
    	McItaServer conn = new McItaServer(type.getIdentifier());
    	
    	for (PlaceholderType placeholder : PlaceholderType.values()) {
    		if(identifier.toLowerCase().startsWith(placeholder.getRawIdentifier())) {
    			conn =  new McItaServer(identifier.replace(placeholder.getRawIdentifier(),""));
    			type = placeholder;
    			break;
    		}
    	}
    	
    	switch(type) {
	    	case STATUS:
	    		return conn.getStatus();
	    	case MESSAGE:
	    		return conn.getMessage();
	    	case ID:
	    		return conn.getId().toString();
	    	case SERVERID:
	    		return conn.getServerid();
	    	case TITLE:
	    		return conn.getTitle();
	    	case ADDRESS:
	    		return conn.getAddress();
	    	case POSITION:
	    		return conn.getPosition().toString();
	    	case VOTES:
	    		return conn.getVotes().toString();
	    	case VOTES_TODAY:
	    		return conn.getVotes_today().toString();
	    	case DESCRIPTION:
	    		return conn.getDescription();
	    	case VERSION:
	    		return conn.getVersion();
	    	case ONLINE:
	    		return conn.getOnline().toString();
	    	case PLAYERS:
	    		conn.getPlayers();
	    	case MAX_PLAYERS:
	    		return conn.getMax_players().toString();
	    	case SLOT:
	    		return conn.getSlot().toString();
	    	default:
	    		return "error";
    	}
    }
}
