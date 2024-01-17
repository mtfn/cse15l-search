import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    ArrayList<String> strs;

    public String handleRequest(URI url) {
	    if(this.strs == null) this.strs = new ArrayList<>();

        if (url.getPath().equals("/")) {
	    String ret = "";
	    for(String s : strs) {
		ret += s + "\n";
	    }
            return ret;
	    
	} else if (url.getPath().contains("/add")) {
	    String[] parameters = url.getQuery().split("=");
            strs.add(parameters[1]);
            return "Added " + parameters[1];
        
	    } else if(url.getPath().contains("/search")) {
                 String[] parameters = url.getQuery().split("=");
		 String ret = "";
		 for(String s : strs) {
                      if(s.contains(parameters[1])) {
	                  ret += s + "\n";	      
		      }
		 }
		 return ret;
	    }
	return "404 not found";
        }
    }



class SearchServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
